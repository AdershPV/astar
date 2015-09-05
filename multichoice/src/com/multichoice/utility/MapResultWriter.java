package com.multichoice.utility;

import com.multichoice.astar.Map;
import com.multichoice.astar.Node;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 */
public class MapResultWriter
{

    public static void outputResultToFile(String filename, Map map, ArrayList<Node> path)
    {
        Map mapWithPath = map.getMapWithPath(path);
        try (PrintWriter writer = new PrintWriter(filename, "UTF-8"))
        {
            System.out.println("\n=========== RESULT IS DISPLAYED BELOW ==============\n");
            for (int i = 0; i < mapWithPath.getNumberOfRows(); i++)
            {
                StringBuffer buffer = new StringBuffer(map.getNumberOfColumns());
                for (int j = 0; j < mapWithPath.getNumberOfColumns(); j++)
                {
                    buffer.append(mapWithPath.getNodeAtPosition(i, j).getTileString());
                }
                writer.println(buffer.toString());
                System.out.println(buffer.toString());
            }
            writer.close();
        }
        catch(Exception e)
        {
            System.out.println("Error occurred on trying to write output file. Please check");
            e.printStackTrace();
        }

    }
    
}
