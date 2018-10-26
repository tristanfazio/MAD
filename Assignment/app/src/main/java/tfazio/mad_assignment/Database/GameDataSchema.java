package tfazio.mad_assignment.Database;

public class GameDataSchema
{
    //tables
    public static class PlayerTable
    {
        //table name
        public static final String NAME = "player";
        //columns
        public static class Cols
        {
            public static final String ID = "id";
            public static final String ROWLOC = "rowloc";
            public static final String COLLOC = "colloc";
            public static final String CASH = "cash";
            public static final String HEALTH = "health";
            public static final String MASS = "mass";
        }
    }

    public static class ItemTable
    {
        //table name
        public static final String NAME = "item";
        //columns
        public static class Cols
        {
            public static final String ID = "id";
            public static final String NAME = "name";
            public static final String DESCRIPTION = "description";
            public static final String VALUE = "value";
            public static final String USEABLE = "useable";
            public static final String NUMBER = "number";
            public static final String QUEST = "quest";
            public static final String OWNER = "owner";
        }
    }

    public static class AreaTable
    {
        //table name
        public static final String NAME = "area";
        //columns
        public static class Cols
        {
            public static final String ID = "id";
            public static final String ISTOWN = "istown";
            public static final String DESCRIPTION = "description";
            public static final String STARRED = "starred";
            public static final String EXPLORED = "explored";
            public static final String X = "x";
            public static final String Y = "y";

        }
    }
}
