package tfazio.mad_assignment.DataClasses;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameData
{

    //Class variables
    private Area[][] grid;
    private Player player;
    private static GameData instance;
    private int xMax;
    private int yMax;
    private Random random = new Random();
    List<Equipment> equipmentList;
    List<Food> foodList;
    List<Equipment> questList;

    //default constructor, creates a testgrid
    private GameData()
    {

    }

    private GameData(int x, int y)
    {
        //initialize a x*y grid
        player = new Player(x,y);
        generateItems();
        createGrid(x,y);
        player.addEquipment(new Equipment("Smell O'Scope","A device used to sniff out hidden treasures",25,5.0,false,true));

        instance = this;

    }

    //GETTERS

    //get an Area object from the specified gamemap coordinates
    public Area getArea(int[] xy)
    {
        //get area from supplied coordinates of gameData
        return grid[xy[0]][xy[1]];
    }

    public int getXMax()
    {
        return xMax;
    }
    public int getYMax()
    {
        return yMax;
    }

    public static GameData getInstance()
    {
        if(instance == null)
        {
            instance = new GameData(7,7);
        }
        return instance;
    }

    public Player getPlayer(){return player;}

    //SETTERS

    //take in grid dimensions, initialize grid 2D array, assign a new Area to each grid position
    private void createGrid(int x, int y)
    {
        //initialize grid
        Log.d("DEBUG","\nCreating map grid of size ("+ x +","+y+")");

        grid = new Area[x][y];
        xMax = x-1;
        yMax = y-1;
        boolean oneTown;
        boolean isTown;
        //item creation triggers
        boolean smell,drive,kenobi;

        //assign Areas to grid
        //for every row
        oneTown = false;
        smell = false;
        drive = false;
        kenobi = false;
        for(int row = 0;row < y; row++)
        {
            //for every column
            for(int col = 0;col<x;col++)
            {
                //assign a newly constructed area
                Log.d("DEBUG","\n   Targeting: "+ col+","+row);
                isTown = randomArea();//generate if area is town or wild
                if(isTown)
                {
                    oneTown=true;//if there is atleast one town generated
                }

                if((row==xMax&&col==yMax)&&(!oneTown))//if it's the final row and col position, and no towns generated
                {
                    isTown = true; //next area is town
                }
                grid[row][col] = new Area(isTown,"",row,col);
                //place 0-3 random assortment of items in the area

                for(int i = random.nextInt(4);i<3;i++)
                {
                    boolean bool = random.nextBoolean();//random food or equip
                    if(bool)
                    {
                        Equipment newEquip = equipmentList.get(random.nextInt(10));
                        if(newEquip.getName().equals("Smell O'Scope"))
                        {
                            smell=true;
                        }else
                        if(newEquip.getName().equals("Improbability Drive"))
                        {
                            drive=true;
                        }else
                        if(newEquip.getName().equals("Ben Kenobi"))
                        {
                            kenobi=true;
                        }
                        grid[row][col].addItem(newEquip);
                    }
                    else
                    {
                        Food newFood = foodList.get(random.nextInt(6));
                        grid[row][col].addItem(newFood);
                    }
                }
            }
        }
        //ensure atleast one of each type of useable on the map
        if(!smell)
        {
            grid[random.nextInt(yMax+1)][random.nextInt(xMax+1)].addItem(new Equipment("Smell O'Scope","A device used to sniff out hidden treasures",25,5.0,false,true));
        }
        if(!drive)
        {
            grid[random.nextInt(yMax+1)][random.nextInt(xMax+1)].addItem(new Equipment("Improbability Drive","Crosses interstellar distances in a mere nothingth of a second...\nCaution advised",30,1.0,false,true));
        }
        if(!kenobi)
        {
            grid[random.nextInt(yMax+1)][random.nextInt(xMax+1)].addItem(new Equipment("Ben Kenobi","A Mysterious old man...prone to stalking",25,0.0,false,true));
        }

        //place quest items somewhere on the map if not in player backpack(helps for resetting, wont be in backpack at creation)

        if(!player.isJade())
        {
            grid[random.nextInt(yMax+1)][random.nextInt(xMax+1)].addItem(questList.get(0));
        }
        if(!player.isScrap())
        {
            grid[random.nextInt(yMax+1)][random.nextInt(xMax+1)].addItem(questList.get(1));
        }
        if(!player.isRoadmap())
        {
            grid[random.nextInt(yMax+1)][random.nextInt(xMax+1)].addItem(questList.get(2));
        }
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

    //randomize the next area being a town or wilderness, keeps the gap from getting too large
    private boolean randomArea()
    {
        boolean nextArea =false;

        int x = random.nextInt(10);
        if(x<4)
        {
            nextArea = true;
        }

        return nextArea;
    }

    public void restart()
    {
        instance = null;
    }

    public void rebuildGrid()
    {
        int x = xMax +1;
        int y = yMax +1;
        createGrid(x,y);
    }

    public void generateItems()
    {
         //Equipment(String inTitle, String inDescription, int inValue, double inMass, boolean inQuest,boolean inUseable)
         //Food(String inTitle, String inDescription, int inValue,double inHealth)

         //lists of items
          equipmentList = new ArrayList<>();
          foodList = new ArrayList<>();
          questList = new ArrayList<>();


         //create list of equipment (10)
         equipmentList.add(new Equipment("Smell O'Scope","A device used to sniff out hidden treasures",25,5.0,false,true));
         equipmentList.add(new Equipment("Ben Kenobi","A Mysterious old man...prone to stalking",25,0.0,false,true));
         equipmentList.add(new Equipment("Improbability Drive","Crosses interstellar distances in a mere nothingth of a second...\nCaution advised",30,1.0,false,true));
         equipmentList.add(new Equipment("Rusted Sword","Once a proud weapon, this blade is now...useless",2,2.0,false,false));
         equipmentList.add(new Equipment("Long Sword","A weapon used by the honourable knights",10,2.0,false,false));
         equipmentList.add(new Equipment("Worn Boots","These boots were made for walkin",4,1.0,false,false));
         equipmentList.add(new Equipment("Shiny Gemstone","Sparkle Sparkle",15,1.5,false,false));
         equipmentList.add(new Equipment("Cape of Invisibility","It works I swear",10,2.0,false,false));
         equipmentList.add(new Equipment("Rock","It's a rock",1,5.0,false,false));
         equipmentList.add(new Equipment("Party Hat","Surprisingly valuable.",15,2.0,false,false));

         //create list of food (6)
         foodList.add(new Food("Apple","A crispy crunch",2,5));
         foodList.add(new Food("Poisoned Apple","A crispy cru...urgh",2,-5));
         foodList.add(new Food("Health Potion","Refreshing",5,10));
         foodList.add(new Food("Cooked Meat","Juicy",4,8));
         foodList.add(new Food("Loaf of Bread","Fresh and Delicious",3,6));
         foodList.add(new Food("Stale Bread","You might chip a tooth",1,1));

         //create quest list(3)
        questList.add(new Equipment("Jade Monkey","You are compelled to acquire this",100,5.0,true,false));
        questList.add(new Equipment("Ice Scrapper","You are compelled to acquire this",50,2.0,true,false));
        questList.add(new Equipment("Road Map","You are compelled to acquire this",25,1.0,true,false));
     }
}
