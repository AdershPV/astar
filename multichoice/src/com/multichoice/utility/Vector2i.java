package com.multichoice.utility;

public final class Vector2i
{
    private int x, y;

    public Vector2i()
    {
        set(0, 0);
    }

    public Vector2i(Vector2i vector)
    {
        set(vector.x, vector.y);
    }

    public Vector2i(int x, int y)
    {
        set(x, y);
    }

    public void set(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public boolean equals(Object object)
    {
        if (!(object instanceof Vector2i))
        {
            return false;
        }
        Vector2i vec = (Vector2i) object;
        if (vec.getX() == this.getX() && vec.getY() == this.getY())
        {
            return true;
        }
        return false;
    }

    @Override
    public String toString()
    {
        return "Vector2i x = " + x + " y = " + y;
    }

}
