package tfazio.mad_assignment;

public class MapElement
{
    private boolean isExplored;
    private boolean isTown;
    private boolean isStarred;
    private boolean isPlayer;

    public MapElement(boolean inExplored, boolean inTown, boolean inStarred, boolean inPlayer)
    {
        isExplored = inExplored;
        isTown = inTown;
        isStarred = inStarred;
        isPlayer = inPlayer;
    }

    public boolean isExplored() {
        return isExplored;
    }

    public void setExplored(boolean explored) {
        isExplored = explored;
    }

    public boolean isTown() {
        return isTown;
    }

    public void setTown(boolean town) {
        isTown = town;
    }

    public boolean isStarred() {
        return isStarred;
    }

    public void setStarred(boolean starred) {
        isStarred = starred;
    }

    public boolean isPlayer() {
        return isPlayer;
    }

    public void setPlayer(boolean player) {
        isPlayer = player;
    }

}
