package tfazio.mad_assignment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Navigation extends AppCompatActivity {

    //game objects
    GameData gameData;
    Player player;
    int difficulty;
    int[] newPlayerPos;

    //UI objects
    private Button northButton;
    private Button southButton;
    private Button eastButton;
    private Button westButton;
    Button optionsButton;
    private TextView playerposTextView;
    StatusBar statusBarFrag;
    Button overviewButton;


    @Override
    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.activity_navigation);

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


        //identify UI elements
        northButton = (Button)findViewById(R.id.northButton);
        southButton = (Button)findViewById(R.id.southButton);
        eastButton = (Button)findViewById(R.id.eastButton);
        westButton = (Button)findViewById(R.id.westButton);
        playerposTextView = (TextView)findViewById(R.id.playerposTextView);
        optionsButton = (Button)findViewById(R.id.optionsButton);
        overviewButton = (Button)findViewById(R.id.overviewButton);

        // create grid, no args = 2x2 test map, x,y args for custom grid
        difficulty = 1;
        switch(difficulty)
        {
            case 1: gameData = new GameData(3,3);
                break;
            case 2: gameData = new GameData(4,4);
                break;
            case 3: gameData = new GameData(5,5);
                break;
            default: gameData = new GameData();
                break;
        }

        player = gameData.getPlayer();
        gameData.getArea(player.colLocation,player.rowLocation).toggleExplored();

        updateAreaText();

        //add listeners for movement presses
        northButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("DEBUG","N button press");

                newPlayerPos = player.getPosition();
                newPlayerPos[1]++;
                movePlayer(newPlayerPos[0],newPlayerPos[1]);
            }
        });
        southButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                newPlayerPos = player.getPosition();
                newPlayerPos[1]--;
                movePlayer(newPlayerPos[0],newPlayerPos[1]);
            }
        });
        eastButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                newPlayerPos = player.getPosition();
                newPlayerPos[0]++;
                movePlayer(newPlayerPos[0],newPlayerPos[1]);
            }
        });
        westButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                newPlayerPos = player.getPosition();
                newPlayerPos[0]--;
                movePlayer(newPlayerPos[0],newPlayerPos[1]);
            }
        });

        //add listeners for options press
        optionsButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
        overviewButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

    }


    private void movePlayer(int x, int y)
    {
        //validate position
        Log.d("DEBUG","Attempting to move player to position: " + x + ',' + y);

        if(gameData.validateCoords(x,y))
        {
            //if valid //move player
            Log.d("DEBUG","Coords are valid");

            player.updatePosition(x,y);
            //update the current area
            updateAreaText();
            statusBarFrag.updateHealth(player.getHealth());
            gameData.getArea(x,y).toggleExplored();
        }
        else
        {
            Log.d("DEBUG","Coords are invalid");

            //prompt invalid movement to player // 1. Instantiate an AlertDialog.Builder with its constructor
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            // 2. Chain together various setter methods to set the dialog characteristics
            builder.setMessage("You cannot move in that direction")
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

    private void updateAreaText()
    {
        Log.d("DEBUG","Checking for text update at:");
        int[] currentPos = player.getPosition();
        if(gameData.getArea(currentPos[0],currentPos[1]).isTown())
        {
            playerposTextView.setText("Town");
            Log.d("DEBUG","Set text to town");

        }
        else
        {
            playerposTextView.setText("Wilderness");
            Log.d("DEBUG","Set text to wilderness");
        }
    }


}
