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
import android.widget.TextView;

import java.util.List;

public class Market extends AppCompatActivity {

    GameData gameData;
    private TextView titleTextView;
    private TextView flavourTextView;
    private Button closeButton;
    private StatusBar statusBarFrag;
    private RecyclerView recViewFrag;
    List<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        gameData = gameData.getInstance();

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

        if (items == null)//only make list once
        {
            int[] xy = gameData.getPlayer().getPosition();
            items = gameData.getArea(xy).getItems();
            items.addAll(gameData.getPlayer().getEquipment());
        }

        MyAdapter adapter = new MyAdapter(items);
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

    private class MyDataVHolder extends RecyclerView.ViewHolder
    {
        private final Button actionButton;
        private final TextView extraTextVeiw;
        private final TextView extraLabelTextView;
        private final TextView priceTextView;
        private final TextView nameTextView;

        public MyDataVHolder(LayoutInflater li, ViewGroup parent)
        {
            super(li.inflate(R.layout.list_market,parent,false));

            //get UI elements
            nameTextView = (TextView)itemView.findViewById(R.id.nameTextView);
            priceTextView = (TextView)itemView.findViewById(R.id.priceTextView);
            extraLabelTextView = (TextView)itemView.findViewById(R.id.extraLabelTextView);
            extraTextVeiw = (TextView)itemView.findViewById(R.id.extraTextView);
            actionButton = (Button)itemView.findViewById(R.id.actionButton);
        }

        public void bind(Item item)
        {
            //set UI elements
            nameTextView.setText(item.getName());
            priceTextView.setText(Double.toString(item.getValue()));
            if(item instanceof Equipment)
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
                actionButton.setText("Sell");
                actionButton.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        //sell the item
                        Log.d("DEBUG","Sell item "+ nameTextView.getText().toString());
                    }
                });
            }
            else if(!item.isOwned())
            {
                actionButton.setText("Buy");
                actionButton.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        //sell the item
                        Log.d("DEBUG","Buy item "+ nameTextView.getText().toString());
                    }
                });
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
