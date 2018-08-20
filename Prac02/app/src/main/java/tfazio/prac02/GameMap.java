package tfazio.prac02;

public class GameMap
{

    //Class variables
    Area[][] grid;
    int xMax;
    int yMax;

    //default constructor, creates a testgrid
    public GameMap()
    {
        //initialize a default 2x2 grid
        createTestGrid();
    }

    //alternate constructor with grid variables
    public GameMap(int x, int y)
    {
        //initialize a grid of x by y
        createGrid(x,y);
    }

    //GETTERS

    //get an Area object from the specified gamemap coordinates
    public Area getArea(int x,int y)
    {
        //get area from supplied coordinates of gameMap
        return grid[x][y];
    }
    //SETTERS

    //take in grid dimensions, initialize grid 2D array, assign a new Area to each grid position
    private void createGrid(int x, int y)
    {
        //initialize grid
        grid = new Area[x][y];
        xMax = x;
        yMax = y;

        //assign Areas to grid
        //for every row
        for(int row = 0;row < y; row++)
        {
            //for every column
            for(int col = 0;col<x;col++)
            {
                //assign a newly constructed area
                grid[x][y] = new Area();
            }
        }
    }

    //take in grid dimensions, initialize grid 2D array, assign a new Area to each grid position
    private void createTestGrid()
    {
        //initialize grid
        grid = new Area[2][2];

        //set dimensions
        xMax = 2;
        yMax = 2;

        /*
        _____________

        | 0,1 | 1,1 |
        | 0,0 | 1,0 |
        _____________
        */

        //assign Area's to grid
        grid[0][0] = new Area(true); //bottom left
        grid[0][1] = new Area(false);  //top left
        grid[1][0] = new Area(false); //bottom right
        grid[1][1] = new Area(true); //top right
    }
}
