package com.multichoice.display;

import com.multichoice.astar.Map;
import com.multichoice.astar.Node;
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
	private static final long serialVersionUID = 1L;
	// Tile images used for output display
    private BufferedImage flatlandImage;
    private BufferedImage forestImage;
    private BufferedImage mountainImage;
    private BufferedImage waterImage;
    private BufferedImage userStartImage;
    private BufferedImage goalTileImage;
    private BufferedImage pathImage;
    private final Map mapWithPath;

    public DrawPanel(Map map, ArrayList<Node> path)
    {
        mapWithPath = map.getMapWithPath(path);
        loadImages();
    }

    private void loadImages()
    {
        flatlandImage = LoadImage("resources/flatland.jpg");
        forestImage = LoadImage("resources/forest.jpg");
        mountainImage = LoadImage("resources/mountain.jpg");
        waterImage = LoadImage("resources/water.jpg");
        userStartImage = LoadImage("resources/userStart.jpg");
        goalTileImage = LoadImage("resources/goalTile.jpg");
        pathImage = LoadImage("resources/path.jpg");
    }

    public BufferedImage LoadImage(String filename)
    {
        BufferedImage img = null;
        try
        {
            img = ImageIO.read(new File(filename));
        }
        catch (IOException e)
        {
            System.out.print("Could not load the tile images!");
            System.exit(0);
        }
        return img;
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
                g.drawImage(getImageForTile(node.getTileString()),
                        x * PathFinderConstants.TILE_DISPLAY_WIDTH, y * PathFinderConstants.TILE_DISPLAY_HEIGHT,
                        PathFinderConstants.TILE_DISPLAY_WIDTH, PathFinderConstants.TILE_DISPLAY_HEIGHT, this);
            }
        }
    }

    private BufferedImage getImageForTile(String tileValue)
    {
        BufferedImage tileImage = flatlandImage;
        switch (tileValue)
        {
            case PathFinderConstants.FLATLANDS:
                tileImage = flatlandImage;
                break;
            case PathFinderConstants.FOREST:
                tileImage = forestImage;
                break;
            case PathFinderConstants.MOUNTAIN:
                tileImage = mountainImage;
                break;
            case PathFinderConstants.WATER:
                tileImage = waterImage;
                break;
            case PathFinderConstants.PATH_IDENTIFIER:
                tileImage = pathImage;
                break;
            case PathFinderConstants.GOAL_TILE:
                tileImage = goalTileImage;
                break;
            case PathFinderConstants.USER_START:
                tileImage = userStartImage;
                break;
            default:
        }
        return tileImage;
    }
}
