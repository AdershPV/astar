package com.multichoice.astar;

import com.multichoice.utility.Vector2i;
import java.util.ArrayList;
import java.util.List;

/**
 * Node represents each tile in the map
 */
public class Node
{
    private String tileString;
    private final int movementCost;
    private final Vector2i location;
    private Node parent;
    private final boolean walkable;
    private double fCost, gCost, hCost;

    public Node(String tileString, int movementCost, Vector2i location, boolean walkable)
    {
        this.tileString = tileString;
        this.movementCost = movementCost;
        this.location = location;
        this.walkable = walkable;
    }

    /**
     * @return the fCost
     */
    public double getfCost()
    {
        return fCost;
    }

    /**
     * @return the gCost
     */
    public double getgCost()
    {
        return gCost;
    }

    /**
     * @param gCost the gCost to set
     */
    public void setgCost(double gCost)
    {
        this.gCost = gCost;
        this.fCost = this.gCost + this.hCost;
    }

    /**
     * @return the hCost
     */
    public double gethCost()
    {
        return hCost;
    }

    /**
     * @param hCost the hCost to set
     */
    public void sethCost(double hCost)
    {
        this.hCost = hCost;
        this.fCost = this.gCost + this.hCost;
    }

    @Override
    public String toString()
    {
        return "Tile " + getTileString() + " location = " + getLocation();
    }

    /**
     * @return the tileString
     */
    public String getTileString()
    {
        return tileString;
    }

    /**
     * @return the location
     */
    public Vector2i getLocation()
    {
        return location;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj != null && obj instanceof Node)
        {
            Node otherNode = (Node) obj;
            if (this.getLocation().equals(otherNode.getLocation()))
            {
                return true;
            }
        }
        return false;
    }

    public List<Node> getWalkableNeighbourNodes(Map map)
    {
        List<Node> neighbourNodeList = new ArrayList<>();
        for (int x = -1; x <= 1; x++)
        {
            for (int y = -1; y <= 1; y++)
            {
                if (x == 0 && y == 0)
                {
                    continue;
                }
                Node node = map.getNodeAtPosition(x + getLocation().getX(), y + getLocation().getY());
                if (null != node)
                {
                    if (node.walkable)
                    {
                        neighbourNodeList.add(node);
                    }
                }
            }
        }
        return neighbourNodeList;
    }

    /**
     * @return the movementCost
     */
    public int getMovementCost()
    {
        return movementCost;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(Node parent)
    {
        this.parent = parent;
    }

    /**
     * @return the parent
     */
    public Node getParent()
    {
        return parent;
    }

    /**
     * @param tileString the tileString to set
     */
    public void setTileString(String tileString)
    {
        this.tileString = tileString;
    }
}
