package tfazio.mad_assignment;

import android.util.Log;

public class GameData
{

    //Class variables
    Area[][] grid;
    Player player;
    static GameData instance;
    int xMax;
    int yMax;

    //default constructor, creates a testgrid
    public GameData()
    {
        //initialize a default 2x2 grid
        createTestGrid();
        player = new Player();
    }

    //alternate constructor with grid variables
    public GameData(int x, int y)
    {
        //initialize a grid of x by y
        createGrid(x,y);
        instance = this;
    }

    //GETTERS

    //get an Area object from the specified gamemap coordinates
    public Area getArea(int x,int y)
    {
        //get area from supplied coordinates of gameData
        return grid[x][y];
    }
    public int getxMax()
    {
        return xMax;
    }
    public int getyMax()
    {
        return yMax;
    }
    public static GameData getInstance(){return instance;}
    public Player getPlayer(){return player;}

    //SETTERS

    //take in grid dimensions, initialize grid 2D array, assign a new Area to each grid position
    private void createGrid(int x, int y)
    {
        //initialize grid
        Log.d("DEBUG","Creating map grid of size ("+ x +","+y+")");

        grid = new Area[x][y];
        xMax = x-1;
        yMax = y-1;
        boolean toggle = true;

        //assign Areas to grid
        //for every row
        for(int row = 0;row < y; row++)
        {
            //for every column
            for(int col = 0;col<x;col++)
            {
                //assign a newly constructed area
                Log.d("DEBUG","Targeting: "+ col+","+row);

                grid[col][row] = new Area(toggle);
                if(toggle)
                {
                    toggle = false;
                }
                else
                {
                    toggle = true;
                }
            }
        }
    }

    //take in grid dimensions, initialize grid 2D array, assign a new Area to each grid position
    private void createTestGrid()
    {
        Log.d("DEBUG","Creating Test Grid of size (2,2)");
        //initialize grid
        grid = new Area[2][2];

        //set dimensions
        xMax = 1;
        yMax = 1;

        /*
        _____________

        | 0,1 | 1,1 |
        | 0,0 | 1,0 |
        _____________
        */

        //assign Area's to grid
        Log.d("DEBUG","Targeting: 0,0");
        grid[0][0] = new Area(true); //bottom left
        grid[0][0].addItem(new Equipment("Sword","A sharp blade",10,5));
        grid[0][0].addItem(new Equipment("Helmet","Protect yo head",8,3));
        grid[0][0].addItem(new Food("Cake","A Delicious snack",10,15));

        Log.d("DEBUG","Targeting: 0,1");
        grid[0][1] = new Area(false);  //top left
        grid[0][1].addItem(new Equipment("Jade Necklace","A gleaming necklace, that radiates a magical essence",50,3));
        grid[0][1].addItem(new Equipment("Torn Leather Boots","Once a sturdy pair of foot wear, these tattered shoes have been tossed aside",1,2));
        grid[0][1].addItem(new Food("Apple","Red and crisp",3,5));

        Log.d("DEBUG","Targeting: 1,0");
        grid[1][0] = new Area(false); //bottom right
        grid[1][0].addItem(new Equipment("Gem","A gleaming stone",40,4));
        grid[1][0].addItem(new Equipment("Jade Transcript","A book describing the legend of the Jade Idol. May contain instructions on activating its power",20,4));
        grid[0][1].addItem(new Food("Apple","Red and crisp",3,5));

        Log.d("DEBUG","Targeting: 1,1");
        grid[1][1] = new Area(true); //top right
        grid[1][1].addItem(new Equipment("Jade Idol","A mystical statue",50,10));
        grid[1][1].addItem(new Equipment("Sleeping Gear","For resting in the wilderness",15,5));
        grid[1][1].addItem(new Food("Cake","A Delicious snack",5,10));
    }

    //take a set of coords and check if they are a valid grid position
    public boolean validateCoords(int x,int y)
    {
        //check if coords exist in game map
        //if yes return true, if no return false
        Log.d("DEBUG","validating coords: " + x + "," + y);

        boolean check = false;
        if((x<=xMax && x>=0) && (yMax>=y && y>=0))
        {
            check = true;
        }

        return check;
    }
}
