package tfazio.mad_assignment.activities;

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

import tfazio.mad_assignment.fragments.AreaInfo;
import tfazio.mad_assignment.DataClasses.GameData;
import tfazio.mad_assignment.DataClasses.Player;
import tfazio.mad_assignment.R;
import tfazio.mad_assignment.fragments.StatusBar;

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
    AreaInfo areaInfoFrag;
    Button overviewButton;


    @Override
    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.activity_navigation);

        //setup game data and get instance
        gameData = gameData.getInstance();

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
        //create areaInfo fragment
        areaInfoFrag = (AreaInfo)fm.findFragmentById(R.id.f_areaInfo);
        if(areaInfoFrag==null)//frag doesnt exist
        {
            areaInfoFrag = new AreaInfo();
            fm.beginTransaction()
                    .add(R.id.f_areaInfo,areaInfoFrag)
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

        //set first area player starts in to explored
        player = gameData.getPlayer();
        int[] xy = {player.getPosition()[0],player.getPosition()[1]};
        gameData.getArea(xy).toggleExplored();



        //add listeners for movement presses
        northButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("DEBUG","N button press");

                newPlayerPos = player.getPosition();
                newPlayerPos[0]--;
                movePlayer(newPlayerPos[0],newPlayerPos[1]);
            }
        });

        southButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                newPlayerPos = player.getPosition();
                newPlayerPos[0]++;
                movePlayer(newPlayerPos[0],newPlayerPos[1]);
            }
        });
        eastButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                newPlayerPos = player.getPosition();
                newPlayerPos[1]++;
                movePlayer(newPlayerPos[0],newPlayerPos[1]);
            }
        });
        westButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                newPlayerPos = player.getPosition();
                newPlayerPos[1]--;
                movePlayer(newPlayerPos[0],newPlayerPos[1]);
            }
        });

        //add listeners for options press
        optionsButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("DEBUG","Options Pressed");
                if(gameData.getArea(gameData.getPlayer().getPosition()).isTown())
                {
                    Log.d("DEBUG","Launching Market");
                    startActivity(new Intent(Navigation.this,Market.class));
                }
                else if(!gameData.getArea(gameData.getPlayer().getPosition()).isTown())
                {
                    Log.d("DEBUG","Launching Wilderness");
                    startActivity(new Intent(Navigation.this,Wilderness.class));
                }
            }
        });
        overviewButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("DEBUG","Overview Pressed");
                startActivity(new Intent(Navigation.this,Overview.class));

            }
        });
    }

    @Override
    protected void onResume()
    {
        /* update all UI elements */
        super.onResume();
        areaInfoFrag.updateAreaInfo();
        statusBarFrag.updateStatusBar();

        checkConditions();
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
            areaInfoFrag.updateAreaInfo();
            statusBarFrag.updateStatusBar();
            int xy[] = {x,y};
            gameData.getArea(xy).toggleExplored();

            checkConditions();
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

    public void checkConditions()
    {
        gameData = gameData.getInstance();
        player=gameData.getPlayer();
        if(player.checkLose())
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("You've run out of health\n\n Play again...?")
                    .setTitle("GAME OVER!!!");
            builder.setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    gameData.restart();
                    recreate();
                }
            });
            builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    finish();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        if(player.checkWin())
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("You collected all of the treasures")
                    .setTitle("YOU WIN!!!!");
            builder.setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    gameData.restart();
                    recreate();
                }
            });
            builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    finish();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }


}
