package tfazio.mad_assignment;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Market extends AppCompatActivity
{

    GameData gameData;
    Player player;
    private TextView titleTextView;
    private TextView flavourTextView;
    private Button closeButton;
    private StatusBar statusBarFrag;
    private RecyclerView recViewFrag;
    List<Item> items;
    private Area area;
    MyAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

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
        recViewFrag.setLayoutManager(new LinearLayoutManager(Market.this));

        //set UI elements
        titleTextView.setText("Market");
        flavourTextView.setText("What are you buyin?");

        //fill list
        buildItemsList();

        //set the adapter
        adapter = new MyAdapter();
        recViewFrag.setAdapter(adapter);

        //set button listeners
        closeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(Market.this,Navigation.class));
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

    private void updateStatusBar()
    {
        statusBarFrag.setHealth();
        statusBarFrag.setEquip();
        statusBarFrag.setCash();
    }

    private void buildItemsList()
    {
        items = new ArrayList<>();
        items.clear();
        int[] xy = gameData.getPlayer().getPosition();//get player position info
        items .addAll(gameData.getArea(xy).getItems());//get item list from area
        items.addAll(gameData.getPlayer().getEquipment());//get item list from player
    }

    private class MyDataVHolder extends RecyclerView.ViewHolder
    {
        private  Button actionButton;
        private  TextView extraTextView;
        private  TextView extraLabelTextView;
        private  TextView priceTextView;
        private  TextView nameTextView;
        private  Button useButton;
        private  TextView descriptionTextView;
        Item item;

        public MyDataVHolder(LayoutInflater li, ViewGroup parent)
        {
            super(li.inflate(R.layout.list_market,parent,false));

            //get UI elements
            nameTextView = (TextView)itemView.findViewById(R.id.nameTextView);
            descriptionTextView = (TextView)itemView.findViewById(R.id.descriptionTextView);
            priceTextView = (TextView)itemView.findViewById(R.id.priceTextView);
            extraLabelTextView = (TextView)itemView.findViewById(R.id.extraLabelTextView);
            extraTextView = (TextView)itemView.findViewById(R.id.extraTextView);
            actionButton = (Button)itemView.findViewById(R.id.actionButton);
            useButton = (Button)itemView.findViewById(R.id.useButton);

            //Listeners
            //set buy or sell based on owned or not
            actionButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if(item.isOwned())//owned by the player
                    {
                        //sell the item
                        Log.d("DEBUG", "Sell item " + nameTextView.getText().toString());
                        //add cash
                        player.updateCash(item.getValue());
                        //remove from player
                        player.removeEquipment((Equipment) item);
                        //add to area
                        area.addItem(item);
                    }
                    else if(!item.isOwned())//not owned, area item
                    {
                        //buy the item
                        Log.d("DEBUG","Buy item "+ nameTextView.getText().toString());
                        if(player.getCash()>=item.getValue())//check player has money
                        {
                            //deduct cash
                            player.updateCash(-item.getValue());
                            //if instance of food
                            if(item instanceof Food)
                            {
                                //update health
                                player.updateHealth(((Food)item).getHealth());
                            }
                            else if(item instanceof Equipment)
                            {
                                //else if equip
                                //add to player
                                player.addEquipment((Equipment)item);
                            }
                            //remove from area
                            area.removeItem(item);
                        }
                        else
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Market.this);
                            // 2. Chain together various setter methods to set the dialog characteristics
                            builder.setMessage("You do not have enough cash for that!")
                                    .setTitle("Uh Oh!");
                            // Add the buttons
                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // User clicked OK button
                                }
                            });
                            // 3. Get the AlertDialog from create()
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    }
                    updateStatusBar();
                    buildItemsList();
                    adapter.notifyDataSetChanged();
                }
            });

            //set listener for use if useable
            useButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if(item.isUseable()) {
                        //Use the item

                        Log.d("DEBUG", "Using item " + nameTextView.getText().toString());
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
                extraTextView.setText(Double.toString(((Equipment) item).getMass()));
            }
            else if(item instanceof Food)
            {
                extraLabelTextView.setText("Health:");
                extraTextView.setText(Double.toString(((Food) item).getHealth()));
            }

            if(item.isOwned())
            {
                actionButton.setText("Sell");
            }
            else if(!item.isOwned())
            {
                actionButton.setText("Buy");
            }


            if(!item.isOwned())//not owned, so in area
            {
                useButton.setVisibility(View.GONE);
            }
            if(item.isOwned() && !item.isUseable())//owned but not useable
            {
                useButton.setVisibility(View.GONE);
            }
            if(item.isOwned() && item.isUseable())//newly aquired owned and useable
            {
                useButton.setVisibility(View.VISIBLE);
            }
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<MyDataVHolder>
    {

        @Override
        public int getItemCount()
        {
            return items.size();
        }

        @Override
        public MyDataVHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            LayoutInflater li = LayoutInflater.from(Market.this);
            return new MyDataVHolder(li,parent);
        }

        @Override
        public void onBindViewHolder(MyDataVHolder vh, int index)
        {
            vh.bind(items.get(index));
        }
    }
}
