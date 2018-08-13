package tfazio.prac02;
import java.util.List;

public class Area {

    //Class variables
    boolean town;
    List<Item> items;

    //default constructor
    public Area()
    {
        town = true;
    }

    //alternate constructor with town/wilderness indication
    public Area(Boolean isTown)
    {
        boolean town = isTown;
    }

    public void addItem(Item inItem)
    {
        items.add(inItem);
    }
}
