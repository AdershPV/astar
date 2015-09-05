package com.multichoice.config;

import java.awt.image.BufferedImage;

public class TileConfig
{
    private final String tileName;
    private final String tileString;
    private final int costOfMovement;
    private final boolean isStartNode;
    private final boolean isEndNode;
    private final boolean isWalkable;
    private final BufferedImage tileImage;

    public TileConfig(String tileName, String tileString, int costOfMovement, boolean isStartNode, boolean isEndNode, boolean isWalkable, BufferedImage tileImage)
    {
        this.tileName = tileName;
        this.tileString = tileString;
        this.costOfMovement = costOfMovement;
        this.isStartNode = isStartNode;
        this.isEndNode = isEndNode;
        this.isWalkable = isWalkable;
        this.tileImage = tileImage;
    }

    @Override
    public String toString()
    {
        return "TileConfig tileName = " + getTileName() + " tileString = " + getTileString() + " costOfmovement = " + getCostOfMovement() 
                + " isStartNode = " + isStartNode() + " isEndNode = " + isEndNode() + " isWalkable = " + isWalkable();
    }

    /**
     * @return the tileName
     */
    public String getTileName()
    {
        return tileName;
    }

    /**
     * @return the tileString
     */
    public String getTileString()
    {
        return tileString;
    }

    /**
     * @return the costOfMovement
     */
    public int getCostOfMovement()
    {
        return costOfMovement;
    }

    /**
     * @return the isStartNode
     */
    public boolean isStartNode()
    {
        return isStartNode;
    }

    /**
     * @return the isEndNode
     */
    public boolean isEndNode()
    {
        return isEndNode;
    }

    /**
     * @return the isWalkable
     */
    public boolean isWalkable()
    {
        return isWalkable;
    }

    /**
     * @return the tileImage
     */
    public BufferedImage getTileImage()
    {
        return tileImage;
    }
}
