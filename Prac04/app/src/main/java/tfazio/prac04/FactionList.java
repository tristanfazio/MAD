package tfazio.prac04;

import java.util.ArrayList;
import java.util.List;

import tfazio.prac04.Faction;

/**
 * Maintains the overall dataset; specifically of course all the different factions.
 */
public class FactionList
{
    private List<Faction> factions = new ArrayList<>();

    public FactionList() {}

    public void load()
    {
        // ...
    }

    public int size()
    {
        return factions.size();
    }

    public Faction get(int i)
    {
        return factions.get(i);
    }

    public int add(Faction newFaction)
    {
        factions.add(newFaction);
        // ...

        return factions.size() - 1; // Return insertion point
    }

    public void edit(Faction newFaction)
    {
        // ...
    }

    public void remove(Faction rmFaction)
    {
        factions.remove(rmFaction);
        // ...
    }
}
