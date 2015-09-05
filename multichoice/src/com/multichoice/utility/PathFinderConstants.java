package com.multichoice.utility;

/**
 * Constants used in application. Configuration can also be made here to alter the output.
 */
public interface PathFinderConstants
{
    public static final String USER_START = "@";
    public static final String GOAL_TILE = "X";
    public static final String WATER = "~";
    public static final String FLATLANDS = ".";
    public static final String FOREST = "*";
    public static final String MOUNTAIN = "^";
    public static final String PATH_IDENTIFIER = "#";
    
    public static final int MOVEMENT_COST_NON_WALKABLE = -1;
    
    public static final int MOVEMENT_COST_FLATLANDS = 1;
    public static final int MOVEMENT_COST_FOREST = 2;
    public static final int MOVEMENT_COST_MOUNTAIN = 3;
    public static final int MOVEMENT_COST_WATER = MOVEMENT_COST_NON_WALKABLE;

    public static final int TILE_DISPLAY_WIDTH = 12;
    public static final int TILE_DISPLAY_HEIGHT = 12;
}
