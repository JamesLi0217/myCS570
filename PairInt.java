package Maze;

public class PairInt
{
	// fields
	private int x;
	private int y;

	// constructor
	public PairInt(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	// return x coordinate
	public int getX()
	{
		return this.x;
	}

	// return y coordinate
	public int getY()
	{
		return this.y;
	}

	// set the x coordinate
	public void setX(int x)
	{
		this.x = x;
	}

	// set the y coordinate
	public void setY(int y)
	{
		this.y = y;
	}

	// judge the current pair equals the target?
	public boolean equals(Object p)
	{
		// avoid NPE
		if (p == null)
		{
			return false;
		}
		PairInt target = (PairInt) p;
		// Assumption x <= 127 and y <=127
		if (this.x == target.x && this.y == target.y)
		{
			return true;
		}
		return false;
	}

	// return string
	public String toString()
	{
		return "(" + x + "," + y + ")";
	}

	// make a new copy of pair
	public PairInt copy()
	{
		PairInt result = new PairInt(this.x, this.y);
		return result;
	}

}
