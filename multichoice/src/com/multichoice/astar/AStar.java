/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.multichoice.astar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Core class performing the path finding on a map.
 */
public class AStar
{
    private final Comparator<Node> nodeSorter = new Comparator<Node>()
    {
        @Override
        public int compare(Node n0, Node n1)
        {
            if (n1.getfCost() < n0.getfCost())
            {
                return +1;
            }
            if (n1.getfCost() > n0.getfCost())
            {
                return -1;
            }
            return 0;
        }
    };

    public ArrayList<Node> findPath(Map map)
    {
        List<Node> openList = new ArrayList<>();
        List<Node> closedList = new ArrayList<>();
        Node goalNode = map.getGoalTile();
        Node current = map.getUserStartTile();
        current.setgCost(0);
        current.sethCost(getDistance(current, goalNode));
        openList.add(current);

        while (openList.size() > 0)
        {
            Collections.sort(openList, nodeSorter);
            current = openList.get(0);

            if (current.equals(goalNode))
            {
                ArrayList<Node> path = new ArrayList<>();
                while (current.getParent() != null)
                {
                    path.add(current);
                    current = current.getParent();
                }
                openList.clear();
                closedList.clear();
                return path;
            }
            openList.remove(current);
            closedList.add(current);

            List<Node> currentNeighbourNodes = current.getWalkableNeighbourNodes(map);
            for (int i = 0; i < currentNeighbourNodes.size(); i++)
            {
                Node neighbourNode = currentNeighbourNodes.get(i);
                double gCost = neighbourNode.getMovementCost();
                double hCost = getDistance(neighbourNode, goalNode);
                neighbourNode.setgCost(gCost);
                neighbourNode.sethCost(hCost);

                if (closedList.contains(neighbourNode) && gCost >= neighbourNode.getgCost())
                {
                    continue;
                }
                if (!openList.contains(neighbourNode) || gCost < neighbourNode.getgCost())
                {
                    openList.add(neighbourNode);
                    neighbourNode.setParent(current);
                }
            }
        }
        closedList.clear();
        System.out.println("Returning null");
        return null;
    }

    public double getDistance(Node tile, Node goal)
    {
        double horizontalDistance = tile.getLocation().getX() - goal.getLocation().getX();
        double verticalDistance = tile.getLocation().getY() - goal.getLocation().getY();
        return Math.sqrt(horizontalDistance * horizontalDistance + verticalDistance * verticalDistance);
    }

}
