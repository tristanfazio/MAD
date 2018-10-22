package tfazio.mad_assignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Wilderness extends AppCompatActivity {

    GameData gameData;
    private TextView titleTextView;
    private TextView flavourTextView;
    private Button closeButton;
    private StatusBar statusBarFrag;
    private RecyclerView recViewFrag;
    List<Item> items;
    private Player player;
    private Area area;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wilderness);

        gameData = gameData.getInstance();
        player = gameData.getPlayer();
        area = gameData.getArea(player.getPosition());

        //get UI elements

        titleTextView = (TextView)findViewById(R.id.titleTextView);
        flavourTextView = (TextView)findViewById(R.id.flavourTextView);
        closeButton = (Button)findViewById(R.id.closeButton);

        //Fragment manager
        FragmentManager fm = getSupportFragmentManager();
        //create statusbar fragment
        statusBarFrag = (StatusBar)fm.findFragmentById(R.id.f_statusBar);
        if(statusBarFrag == null)//frag doesn't exist
        {
            statusBarFrag = new StatusBar();
            fm.beginTransaction()
                    .add(R.id.f_statusBar,statusBarFrag)
                    .commit();
        }

        //get rec view
        recViewFrag = (RecyclerView)findViewById(R.id.f_itemRecView);

        //rec view layout
        recViewFrag.setLayoutManager(new LinearLayoutManager(Wilderness.this));

        //set UI elements
        titleTextView.setText("Wilderness");
        flavourTextView.setText("You search the current area for hidden treasures...");

        //fill list

        items = new ArrayList<>();
        items.clear();
        int[] xy = gameData.getPlayer().getPosition();
        items = gameData.getArea(xy).getItems();
        items.addAll(gameData.getPlayer().getEquipment());


        MyAdapter adapter = new MyAdapter(items);
        recViewFrag.setAdapter(adapter);

        //set button listeners
        closeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(Wilderness.this,Navigation.class));
            }
        });



    }

    @Override
    protected void onResume() {
        //fill statusbar with values
        super.onResume();
        statusBarFrag.setCash();
        statusBarFrag.setEquip();
        statusBarFrag.setHealth();
    }

    private class MyDataVHolder extends RecyclerView.ViewHolder
    {
        private final Button actionButton;
        private final TextView extraTextVeiw;
        private final TextView extraLabelTextView;
        private final TextView priceTextView;
        private final TextView nameTextView;
        private Button useButton;
        Item item;
        private TextView descriptionTextView;

        public MyDataVHolder(LayoutInflater li, ViewGroup parent)
        {
            super(li.inflate(R.layout.list_market,parent,false));

            //get UI elements
            nameTextView = (TextView)itemView.findViewById(R.id.nameTextView);
            descriptionTextView=(TextView)itemView.findViewById(R.id.descriptionTextView);
            priceTextView = (TextView)itemView.findViewById(R.id.priceTextView);
            extraLabelTextView = (TextView)itemView.findViewById(R.id.extraLabelTextView);
            extraTextVeiw = (TextView)itemView.findViewById(R.id.extraTextView);
            actionButton = (Button)itemView.findViewById(R.id.actionButton);
            useButton = (Button)itemView.findViewById(R.id.useButton);

            //Listeners
            actionButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    //set drop or pickup based on owned or not
                    if(item.isOwned())//owned by the player
                    {
                        //sell the item
                        Log.d("DEBUG", "Drop item " + nameTextView.getText().toString());
                        //deduct mass
                        player.updateMass(-((Equipment) item).getMass());
                        //remove from player
                        player.removeEquipment((Equipment) item);
                        //add to area
                        area.addItem(item);
                    }
                    else if(!item.isOwned())//not owned, area item
                    {
                        //buy the item
                        Log.d("DEBUG", "Pickup item " + nameTextView.getText().toString());
                        //if instance of food
                        if (item instanceof Food) {
                            //update health
                            player.updateHealth(((Food) item).getHealth());
                        } else if (item instanceof Equipment) {
                            //else if equip
                            //update mass
                            player.updateMass(((Equipment) item).getMass());
                            //add to player
                            player.addEquipment((Equipment) item);
                        }
                        //remove from area
                        area.removeItem(item);
                    }
                }
            });

            //set listener for use if useable
            useButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    //Use the item
                    if(item.isUseable())
                    {
                        Log.d("DEBUG","Using item "+ nameTextView.getText().toString());
                    }
                }
            });
        }

        public void bind(Item item)
        {
            //set UI elements
            this.item = item;

            nameTextView.setText(item.getName());
            descriptionTextView.setText(item.getDescription());
            priceTextView.setText(Integer.toString(item.getValue()));
            actionButton.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            useButton.setBackgroundColor(getResources().getColor(R.color.colorAccent));

            if(item instanceof Equipment)//switch label depending on needed info
            {
                extraLabelTextView.setText("Mass:");
                extraTextVeiw.setText(Double.toString(((Equipment) item).getMass()));
            }
            else if(item instanceof Food)
            {
                extraLabelTextView.setText("Health:");
                extraTextVeiw.setText(Double.toString(((Food) item).getHealth()));
            }

            if(item.isOwned())
            {
                actionButton.setText("Drop");
            }
            else if(!item.isOwned())
            {
                actionButton.setText("Pickup");
            }


            //hide USE button if item not useable
            if(!item.isUseable() && item.isOwned())
            {
                useButton.setVisibility(View.GONE);
            }
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<MyDataVHolder>
    {
        private List<Item>items;
        public MyAdapter(List<Item>items)
        {
            this.items = items;
        }

        @Override
        public int getItemCount()
        {
            return items.size();
        }

        @Override
        public MyDataVHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            LayoutInflater li = LayoutInflater.from(Wilderness.this);
            return new MyDataVHolder(li,parent);
        }

        @Override
        public void onBindViewHolder(MyDataVHolder vh, int index)
        {
            vh.bind(items.get(index));
        }
    }


}
