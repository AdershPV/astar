package com.multichoice.display;

import com.multichoice.astar.Map;
import com.multichoice.astar.Node;
import com.multichoice.config.PathFinderConfiguration;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFrame;

public class MapResultDisplay
{
    public static void displayOutput(Map map, ArrayList<Node> path, PathFinderConfiguration configuration)
    {
        JFrame frame = new JFrame("Pathfinder Window");
        DrawPanel panel = new DrawPanel(map, path, configuration);
        frame.setSize(new Dimension(630, 650));
        frame.setLocationRelativeTo(null);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
