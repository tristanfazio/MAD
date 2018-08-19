package tfazio.prac02;

public class GameMap
{

    //Class variables
    Area[][] grid;

    //default constructor
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

    //take in grid dimensions, initialize grid 2D array, assign a new Area to each grid position
    private void createGrid(int x, int y)
    {
        //initialize grid
        grid = new Area[x][y];

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

        //assign Area's to grid
        //for every row
        grid[0][0] = new Area(false);
        grid[0][1] = new Area(true);
        grid[1][0] = new Area(true);
        grid[1][1] = new Area(false);
    }
}
