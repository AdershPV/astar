package com.multichoice.config;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

/**
 * Reads config file and creates configs for app
 */
public class PathFinderConfiguration
{
    private static final String INPUT_MAP_FILE_PATH_NODE_NAME = "inputMapFilePath";
    private static final String OUTPUT_MAP_FILE_PATH_NODE_NAME = "outputMapFilePath";
    private static final String TILES_NODE_NAME = "tiles";

    private String inputMapFilePath;
    private String outputMapFilePath;
    private List<TileConfig> tileConfigList;
    
    private String startTileNodeString;
    private String goalTileNodeString;

    public PathFinderConfiguration(String configFilePath)
    {
        if (null != configFilePath && !configFilePath.trim().isEmpty())
        {
            File configFile = new File(configFilePath);
            if (configFile.exists())
            {
                tileConfigList = new ArrayList<>();
                readAndPopulateConfiguration(configFile);
            }
            else
            {
                exitProgramOnError("Config file doesn't exist in the location provided. Make sure of it and try again. Path provided = " + configFilePath);
            }
        }
        else
        {
            exitProgramOnError("Config file location provided is either empty or invalid. Correct it and try again");
        }
    }

    private void readAndPopulateConfiguration(File configFile)
    {
        try
        {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(configFile);
            NodeList nodeList = doc.getDocumentElement().getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++)
            {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    
                    switch (node.getNodeName())
                    {
                        case INPUT_MAP_FILE_PATH_NODE_NAME:
                            setInputMapFilePath(node);
                            break;
                        case OUTPUT_MAP_FILE_PATH_NODE_NAME:
                            setOutputMapFilePath(node);
                            break;
                        case TILES_NODE_NAME:
                            PopulateTileConfigs(node);
                            break;
                    }
                }
            }
            System.out.println("Successfully read the config file");
        }
        catch (Exception e)
        {
            System.out.println("Exception occurred on reading and populating config file.");
            e.printStackTrace();
        }

    }

    private void exitProgramOnError(String message)
    {
        System.out.println(message);
        System.exit(0);
    }

    private void setInputMapFilePath(Node node)
    {
        if (null == inputMapFilePath)
        {
            inputMapFilePath = node.getTextContent();
        }
        else
        {
            System.out.println("More than one inputMapFilePath nodes are present in config. Only the first value is taken");
        }
    }

    private void setOutputMapFilePath(Node node)
    {
        if (null == outputMapFilePath)
        {
            outputMapFilePath = node.getTextContent();
        }
        else
        {
            System.out.println("More than one outputMapFilePath nodes are present in config. Only the first value is taken");
        }
    }

    private void PopulateTileConfigs(Node tilesNode)
    {
        NodeList nodeList = tilesNode.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++)
        {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element element = (Element) node;
                String tileName = null;
                String tileString = null;
                int costOfMovement = 0;
                boolean isStartNode = false;
                boolean isEndNode = false;
                boolean isWalkable = false;
                BufferedImage tileImage = null;
                if (element.getElementsByTagName("tileName").getLength() > 0)
                {
                    tileName = element.getElementsByTagName("tileName").item(0).getTextContent();
                }
                if (element.getElementsByTagName("tileString").getLength() > 0)
                {
                    tileString = element.getElementsByTagName("tileString").item(0).getTextContent();
                }
                if (element.getElementsByTagName("costOfMovement").getLength() > 0)
                {
                    try
                    {
                        costOfMovement = Integer.parseInt(element.getElementsByTagName("costOfMovement").item(0).getTextContent());
                    }
                    catch (NumberFormatException e)
                    {
                        System.out.println("NumberFormatException: Cost of movement entered is not a number in config file");
                    }
                }
                if (element.getElementsByTagName("isStartNode").getLength() > 0)
                {
                    isStartNode = Boolean.parseBoolean(element.getElementsByTagName("isStartNode").item(0).getTextContent());
                }
                if (element.getElementsByTagName("isEndNode").getLength() > 0)
                {
                    isEndNode = Boolean.parseBoolean(element.getElementsByTagName("isEndNode").item(0).getTextContent());
                }
                if (element.getElementsByTagName("isWalkable").getLength() > 0)
                {
                    isWalkable = Boolean.parseBoolean(element.getElementsByTagName("isWalkable").item(0).getTextContent());
                }
                if (element.getElementsByTagName("tileImage").getLength() > 0)
                {
                    String tileImagePath = element.getElementsByTagName("tileImage").item(0).getTextContent();
                    tileImage = loadImage(tileImagePath);
                }
                if (isStartNode && isEndNode)
                {
                    exitProgramOnError("In configuration, a tile is configured to be both end and start node. Correc it and run again");
                }
                if (isStartNode)
                {
                    startTileNodeString = tileString;
                }
                if (isEndNode)
                {
                    goalTileNodeString = tileString;
                }
                if (tileImage == null)
                {
                    tileImage = loadImage("resources/flatland.jpg");
                }
                TileConfig tileConfig = new TileConfig(tileName, tileString, costOfMovement, isStartNode, isEndNode, isWalkable, tileImage);
                tileConfigList.add(tileConfig);
                System.out.println("tileConfig = " + tileConfig);
            }
        }
    }

    private BufferedImage loadImage(String filename)
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
    
    /**
     * @return the inputMapFilePath
     */
    public String getInputMapFilePath()
    {
        return inputMapFilePath;
    }

    /**
     * @return the outputMapFilePath
     */
    public String getOutputMapFilePath()
    {
        return outputMapFilePath;
    }

    /**
     * Returns the cost of movement on a tile
     *
     * @param tileValue the string representation of tile
     * @return cost of movement within the tile
     */
    public int getMovementCost(String tileValue)
    {
        int tileMovementCost = 0;
        Iterator<TileConfig> iterator = tileConfigList.iterator();
        while (iterator.hasNext())
        {
            TileConfig tileConfig = iterator.next();
            if (tileConfig.getTileString().equalsIgnoreCase(tileValue))
            {
                return tileConfig.getCostOfMovement();
            }
        }
        System.out.println("ERROR: An invalid tile is present in the map file. Correct it. tileValue = " + tileValue);
        return tileMovementCost;
    }

    public boolean isStartTile(String tileString)
    {
        return startTileNodeString.equalsIgnoreCase(tileString);
    }
    
    public boolean isGoalTile(String tileString)
    {
        return goalTileNodeString.equalsIgnoreCase(tileString);
    }
    
    public boolean isWalkableTile(String tileString)
    {
        Iterator<TileConfig> iterator = tileConfigList.iterator();
        while (iterator.hasNext())
        {
            TileConfig tileConfig = iterator.next();
            if (tileConfig.getTileString().equalsIgnoreCase(tileString))
            {
                return tileConfig.isWalkable();
            }
        }
        return false;
    }

    public BufferedImage getTileImage(String tileString)
    {
        Iterator<TileConfig> iterator = tileConfigList.iterator();
        while (iterator.hasNext())
        {
            TileConfig tileConfig = iterator.next();
            if (tileConfig.getTileString().equalsIgnoreCase(tileString))
            {
                return tileConfig.getTileImage();
            }
        }
        return null;
    }
}
