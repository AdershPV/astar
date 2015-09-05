package com.multichoice.astar;

import com.multichoice.utility.PathFinderConstants;
import com.multichoice.utility.Vector2i;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * MapReader reads a map file and provides an object representation of the map
 */
public class MapReader
{
    public static Map createMapFromFile(String fileName)
    {
        ArrayList<ArrayList<Node>> tileList = readFileAndCreateTileList(fileName);
        Map map = validateTileListAndCreateMap(tileList);
        return map;
    }

    private static ArrayList<ArrayList<Node>> readFileAndCreateTileList(String fileName)
    {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
        {
            // Read the map file, populate tiles into a temp list
            String line;
            int row = 0, column = 0;
            ArrayList<ArrayList<Node>> tileList = new ArrayList<>();

            while ((line = br.readLine()) != null)
            {
                String[] tileValues = line.trim().split("(?!^)");
                ArrayList<Node> listOfTilesInRow = new ArrayList<>();
                for (String tileValue : tileValues)
                {
                    Node tile = new Node(tileValue, getMovementCost(tileValue), new Vector2i(row, column));
                    listOfTilesInRow.add(tile);
                    column++;
                }
                tileList.add(listOfTilesInRow);
                row++;
                column = 0;
            }
            br.close();
            return tileList;
        }
        catch (IOException e)
        {
            exitProgramOnError("Exiting since program could not load the map file. Make sure it is available in location : " + fileName);
            return null;
        }
    }

    private static Map validateTileListAndCreateMap(ArrayList<ArrayList<Node>> tileList)
    {
        // If valid tiles are found in map file, create a Map object
        Node userStartTile = null, goalTile = null;
        if (tileList.size() > 0)
        {
            int numberOfRows = tileList.size();
            int numberOfColumns = tileList.get(0).size();
            Node[][] tiles = new Node[tileList.size()][tileList.get(0).size()];
            for (int i = 0; i < tileList.size(); i++)
            {
                ArrayList<Node> listOfTilesInRow = tileList.get(i);
                for (int j = 0; j < listOfTilesInRow.size(); j++)
                {
                    Node tile = listOfTilesInRow.get(j);
                    
                    // Check for more than one user start tiles
                    if (tile.getTileString().equals(PathFinderConstants.USER_START))
                    {
                        if (userStartTile == null)
                        {
                            userStartTile = tile;
                        }
                        else
                        {
                            exitProgramOnError("There are more than one user start tiles available in map. Correct it and try again");
                        }
                    }
                    
                    // Check for more than one goal tiles
                    if (tile.getTileString().equals(PathFinderConstants.GOAL_TILE))
                    {
                        if (goalTile == null)
                        {
                            goalTile = tile;
                        }
                        else
                        {
                            exitProgramOnError("There are more than one goal tiles available in map. Correct it and try again");
                        }
                    }
                    tiles[i][j] = tile;
                }
            }
            return new Map(tiles, numberOfRows, numberOfColumns, userStartTile, goalTile);
        }
        else
        {
            exitProgramOnError("The map file is found, but there is no valid map available in the file. Please try again");
            return null;
        }
    }

    /**
     * Returns the cost of movement on a tile
     *
     * @param tileValue the string representation of tile
     * @return cost of movement within the tile
     */
    private static int getMovementCost(String tileValue)
    {
        int tileMovementCost = 0;
        switch (tileValue)
        {
            case PathFinderConstants.FLATLANDS:
            case PathFinderConstants.GOAL_TILE:
            case PathFinderConstants.USER_START:
                tileMovementCost = PathFinderConstants.MOVEMENT_COST_FLATLANDS;
                break;
            case PathFinderConstants.FOREST:
                tileMovementCost = PathFinderConstants.MOVEMENT_COST_FOREST;
                break;
            case PathFinderConstants.MOUNTAIN:
                tileMovementCost = PathFinderConstants.MOVEMENT_COST_MOUNTAIN;
                break;
            case PathFinderConstants.WATER:
                tileMovementCost = PathFinderConstants.MOVEMENT_COST_WATER;
                break;
            default:
                exitProgramOnError("The tile obtained from map is not defined. Please correct the map and try again. Tile = " + tileValue);
        }
        return tileMovementCost;
    }

    private static void exitProgramOnError(String message)
    {
        System.out.println(message);
        System.exit(0);
    }
}
