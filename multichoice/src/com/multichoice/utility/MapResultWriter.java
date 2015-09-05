package com.multichoice.utility;

import com.multichoice.astar.Map;
import com.multichoice.astar.Node;
import com.multichoice.config.PathFinderConfiguration;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Outputs the result to a text file and to console
 */
public class MapResultWriter
{
    public static void outputResultToFile(PathFinderConfiguration configuration, Map map, ArrayList<Node> path)
    {
        String outputMapFilePath = configuration.getOutputMapFilePath();
        Map mapWithPath = map.getMapWithPath(path);
        try (PrintWriter writer = new PrintWriter(outputMapFilePath, "UTF-8"))
        {
            System.out.println("\n=========== RESULT IS DISPLAYED BELOW ==============\n");
            for (int i = 0; i < mapWithPath.getNumberOfRows(); i++)
            {
                StringBuilder buffer = new StringBuilder(map.getNumberOfColumns());
                for (int j = 0; j < mapWithPath.getNumberOfColumns(); j++)
                {
                    buffer.append(mapWithPath.getNodeAtPosition(i, j).getTileString());
                }
                writer.println(buffer.toString());
                System.out.println(buffer.toString());
            }
            writer.close();
            System.out.println("\n=========== [END] Result is also available in text file and UI. See config for output location ==============\n");
        }
        catch(Exception e)
        {
            System.out.println("Error occurred on trying to write output file. Please check");
            e.printStackTrace();
        }
    }
}
