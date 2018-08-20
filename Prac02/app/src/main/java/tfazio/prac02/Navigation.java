package tfazio.prac02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        //add listeners for movement presses
        northButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //validate movement
                if(gameMap.validateCoords(playerPos[0],playerPos[1]))
                {
                    //if valid
                    //move player
                }
                else
                {
                    //prompt invalid movement to player
                }

            }
        });
        southButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {


                //validate movement
                if(gameMap.validateCoords(playerPos[0],playerPos[1]))
                {
                    //if valid
                    //move player
                }
                else
                {
                    //prompt invalid movement to player
                }
            }
        });
        eastButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //validate movement
                if(gameMap.validateCoords(playerPos[0],playerPos[1]))
                {
                    //if valid
                    //move player
                }
                else
                {
                    //prompt invalid movement to player
                }
            }
        });
        westButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //validate movement
                if(gameMap.validateCoords(playerPos[0],playerPos[1]))
                {
                    //if valid
                    //move player
                }
                else
                {
                    //prompt invalid movement to player
                }
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
}
