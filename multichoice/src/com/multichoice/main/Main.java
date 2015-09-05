package com.multichoice.main;

import com.multichoice.utility.MapResultWriter;
import com.multichoice.astar.MapReader;
import com.multichoice.astar.Map;
import com.multichoice.astar.Node;
import com.multichoice.astar.AStar;
import com.multichoice.config.PathFinderConfiguration;
import com.multichoice.display.MapResultDisplay;
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        long timeAtStart = System.currentTimeMillis();
        
        // Read pathfinder configuration file and create configs
        PathFinderConfiguration configuration = new PathFinderConfiguration("resources/pathFinderConfig.xml");
        
        // Read and populate map from resources location
        Map map = MapReader.createMap(configuration);
        
        // Apply path finder algorithm
        AStar astar = new AStar();
        ArrayList<Node> path = astar.findPath(map);
        
        // Output the result - text file
        MapResultWriter.outputResultToFile(configuration, map, path);
        
        // Output the result - Display
        MapResultDisplay.displayOutput(map, path, configuration);
        System.out.println("\nTotal time taken for execution = " + (System.currentTimeMillis() - timeAtStart) + " mSec");
    }
}
