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

public class SmellOScope extends AppCompatActivity
{

    GameData gameData;
    Player player;
    private TextView titleTextView;
    private TextView flavourTextView;
    private Button closeButton;
    private StatusBar statusBarFrag;
    private RecyclerView recViewFrag;
    List<Item> items;
    List<int[]> coords;
    private Area area;
    MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smell_oscope);

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
        recViewFrag.setLayoutManager(new LinearLayoutManager(SmellOScope.this));

        //set UI elements
        titleTextView.setText("Smell O'Scope");
        flavourTextView.setText("I'll sniff them out...");

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
                finish();
            }
        });


    }

    @Override
    protected void onResume() {
        //fill statusbar with values
        super.onResume();
        statusBarFrag.updateStatusBar();
    }

    private void buildItemsList()
    {
        items = new ArrayList<>();
        items.clear();
        coords = new ArrayList<>();
        coords.clear();
        int row1,row2,col1,col2;
        int[] playerPosition = player.getPosition();//get player position info

        //get items from area and coordinates from area and assign to lists
        System.out.println("Creating SmelloScope item list\nPlayer Pos: " +playerPosition[0] + "," + playerPosition[1]);

        //get ranges, but keep withing map bounds
        row1=playerPosition[0]-2;
        if(row1<0)
        {
            row1=0;
        }
        col1=playerPosition[1]-2;
        if(col1<0)
        {
            col1=0;
        }

        row2=playerPosition[0]+2;
        if(row2>gameData.getYMax())
        {
            row2=gameData.getYMax();
        }
        col2=playerPosition[1]+2;
        if(col2>gameData.getXMax())
        {
            col2=gameData.getXMax();
        }

        System.out.println("\nX1Y1: " +col1 + "," + row1 + "\nX2Y2: " +col2 + "," + row2);

        for(int i = row1;i<=row2;i++)
        {
            for(int j = col1;j<=col2;j++)
            {
                area = gameData.getArea(new int[]{i,j});
                List<Item> newItems = area.getItems();
                for(Item newItem: newItems)
                {
                    System.out.println("\nItem: " +newItem.getName() + "XY: " +i + "," + j);

                    items.add(newItem);
                    coords.add(new int[]{i,j});
                }

            }
        }
    }

    private class MyDataVHolder extends RecyclerView.ViewHolder
    {
        private  TextView nameTextView;
        private  TextView xTextView;
        private  TextView yTextView;
        private  TextView xLabelTextView;
        private  TextView yLabelTextView;
        Item item;
        int[] coords;

        public MyDataVHolder(LayoutInflater li, ViewGroup parent)
        {
            super(li.inflate(R.layout.list_smell,parent,false));

            //get UI elements
            nameTextView = (TextView)itemView.findViewById(R.id.nameTextView);
            xLabelTextView = (TextView)itemView.findViewById(R.id.xLabelTextView);
            yLabelTextView = (TextView)itemView.findViewById(R.id.yLabelTextView);
            xTextView = (TextView)itemView.findViewById(R.id.xTextView);
            yTextView = (TextView)itemView.findViewById(R.id.yTextView);

            //Listeners
        }

        public void bind(Item item, int[] coords)
        {
            //set UI elements
            this.item = item;
            this.coords = coords;

            nameTextView.setText(item.getName());

            int x = player.getPosition()[1] - coords[1];
            int y = player.getPosition()[0] - coords[0];

            System.out.println("\nSmellOScope List Item: " +item.getName() + "XY: " +x + "," + y);


            yTextView.setText(Integer.toString(Math.abs(y)));
            xTextView.setText(Integer.toString(Math.abs(x)));

            if(x>0)
            {
                xLabelTextView.setText("West");
            }
            else
            {
                xLabelTextView.setText("East");
            }

            if(y<0)
            {
                yLabelTextView.setText("South");
            }
            else
            {
                yLabelTextView.setText("North");
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
            LayoutInflater li = LayoutInflater.from(SmellOScope.this);
            return new MyDataVHolder(li,parent);
        }

        @Override
        public void onBindViewHolder(MyDataVHolder vh, int index)
        {
            System.out.println("\n\n\nERROR: \nindex: " + index + " items size: " + items.size() + " coords size: " + coords.size());
            vh.bind(items.get(index),coords.get(index));
        }
    }
}
