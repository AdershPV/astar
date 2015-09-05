package com.multichoice.astar;


import com.multichoice.utility.PathFinderConstants;
import java.util.ArrayList;


/**
 * Map is an object representation of a text input file containing map data
 */
public class Map
{
    private final Node[][] tiles;
    private final int numberOfRows;
    private final int numberOfColumns;
    private final Node userStartTile;
    private final Node goalTile;

    Map(Node[][] tiles, int numberOfRows, int numberOfColumns, Node userStartTile, Node goalTile)
    {
        this.tiles = tiles;
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.userStartTile = userStartTile;
        this.goalTile = goalTile;
    }

    public int getNumberOfRows()
    {
        return numberOfRows;
    }

    public int getNumberOfColumns()
    {
        return numberOfColumns;
    }

    public Node getNodeAtPosition(int row, int column)
    {
        if (row >= 0 && row < numberOfRows && column >= 0 && column < numberOfColumns)
        {
            return tiles[row][column];
        }
//        System.out.println("There is no tile at row = " + row + " and column = " + column);
        return null;
    }

    /**
     * @return the userStartNode
     */
    public Node getUserStartTile()
    {
        return userStartTile;
    }

    /**
     * @return the goalNode
     */
    public Node getGoalTile()
    {
        return goalTile;
    }
    
    /**
     * Returns a 'cloned' object for display, without touching the original object
     * @param path
     * @return 
     */
    public Map getMapWithPath(ArrayList<Node> path)
    {
        Map map = new Map(tiles, numberOfRows, numberOfColumns, userStartTile, goalTile);
        for (int i = 0; i < path.size(); i++)
        {
            Node pathNode = path.get(i);
            Node nodeInMap = map.getNodeAtPosition(pathNode.getLocation().getX(), pathNode.getLocation().getY());
            nodeInMap.setTileString(PathFinderConstants.PATH_IDENTIFIER);
        }
        return map;
    }
}
