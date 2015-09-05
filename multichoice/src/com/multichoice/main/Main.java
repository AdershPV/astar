package com.multichoice.main;

import com.multichoice.utility.MapResultWriter;
import com.multichoice.astar.MapReader;
import com.multichoice.astar.Map;
import com.multichoice.astar.Node;
import com.multichoice.astar.AStar;
import com.multichoice.display.MapResultDisplay;
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        long timeAtStart = System.currentTimeMillis();
        
        // Read and populate map from resources location
        Map map = MapReader.createMapFromFile("resources/large_map.txt");
        
        // Apply path finder algorithm
        AStar astar = new AStar();
        ArrayList<Node> path = astar.findPath(map);
        
        // Output the result - text file
        MapResultWriter.outputResultToFile("resources/large_map_output.txt", map, path);
	System.out.println("\nNew map showing the path is saved in resources folder as a text file (large_map_output.txt)");
        
        // Output the result - Display
        MapResultDisplay.displayOutput(map, path);
        System.out.println("\nTotal time taken for execution = " + (System.currentTimeMillis() - timeAtStart) + " mSec");
    }
}
