package com.multichoice.display;

import com.multichoice.astar.Map;
import com.multichoice.astar.Node;
import com.multichoice.config.PathFinderConfiguration;
import com.multichoice.utility.PathFinderConstants;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 */
public class DrawPanel extends JPanel
{
    private final Map mapWithPath;
    private PathFinderConfiguration configuration;

    public DrawPanel(Map map, ArrayList<Node> path, PathFinderConfiguration configuration)
    {
        this.mapWithPath = map.getMapWithPath(path);
        this.configuration = configuration;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for (int y = 0; y < mapWithPath.getNumberOfRows(); y++)
        {
            for (int x = 0; x < mapWithPath.getNumberOfColumns(); x++)
            {
                Node node = mapWithPath.getNodeAtPosition(y, x);
                BufferedImage tileImage = configuration.getTileImage(node.getTileString());
                g.drawImage(tileImage,
                        x * PathFinderConstants.TILE_DISPLAY_WIDTH, y * PathFinderConstants.TILE_DISPLAY_HEIGHT,
                        PathFinderConstants.TILE_DISPLAY_WIDTH, PathFinderConstants.TILE_DISPLAY_HEIGHT, this);
            }
        }
    }
}
