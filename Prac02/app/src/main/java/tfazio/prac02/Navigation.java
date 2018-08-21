package tfazio.prac02;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Navigation extends AppCompatActivity {

    //game objects
    GameMap gameMap;
    Player player;
    int[] playerPos;
    int[] newPlayerPos;

    //UI objects
    private Button northButton;
    private Button southButton;
    private Button eastButton;
    private Button westButton;
    private Button resetButton;
    private Button optionsButton;
    private TextView playerposTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        //identify UI elements
        northButton = (Button)findViewById(R.id.northButton);
        southButton = (Button)findViewById(R.id.southButton);
        eastButton = (Button)findViewById(R.id.eastButton);
        westButton = (Button)findViewById(R.id.westButton);
        resetButton = (Button)findViewById(R.id.resetButton);
        optionsButton = (Button)findViewById(R.id.optionsButton);
        playerposTextView = (TextView)findViewById(R.id.playerposTextView);

        // create grid, no args = 2x2 test map, x,y args for custom grid
        gameMap = new GameMap();
        //create player, defaults to (0,0)
        player = new Player();

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
        //add listener for reset press
        resetButton.setOnClickListener(new View.OnClickListener()
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

        if(gameMap.validateCoords(x,y))
        {
            //if valid //move player
            Log.d("DEBUG","Coords are valid");

            player.updatePosition(x,y);
            //update the current area
            updateAreaText();
        }
        else
        {
            Log.d("DEBUG","Coords are invalid");

            //prompt invalid movement to player // 1. Instantiate an AlertDialog.Builder with its constructor
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            // 2. Chain together various setter methods to set the dialog characteristics
            builder.setMessage("You cannot move in that direction")
                    .setTitle("Uh oh");
            // Add the buttons
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                }
            });
            // 3. Get the AlertDialog from create()
            AlertDialog dialog = builder.create();
        }
    }

    private void updateAreaText()
    {
        Log.d("DEBUG","Checking for text update at:");
        int[] currentPos = player.getPosition();
        if(gameMap.getArea(currentPos[0],currentPos[1]).isTown())
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
