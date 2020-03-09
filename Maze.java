package Maze;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Class that solves maze problems with backtracking.
 * 
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors
{

	/** The maze */
	private TwoDimGrid maze;

	public Maze(TwoDimGrid m)
	{
		maze = m;
	}

	/** Wrapper method. */
	public boolean findMazePath()
	{
		return findMazePath(0, 0); // (0, 0) is the start point.
	}

	/**
	 * Attempts to find a path through point (x, y).
	 * 
	 * @pre Possible path cells are in BACKGROUND color; barrier cells are in
	 *      ABNORMAL color.
	 * @post If a path is found, all cells on it are set to the PATH color; all
	 *       cells that were visited but are not on the path are in the TEMPORARY
	 *       color.
	 * @param x The x-coordinate of current point
	 * @param y The y-coordinate of current point
	 * @return If a path through (x, y) is found, true; otherwise, false
	 */
	public boolean findMazePath(int x, int y)
	{
		// COMPLETE HERE FOR PROBLEM 1
		if (x < 0 || y < 0 || y > maze.getNRows() - 1 || x > maze.getNCols() - 1)
		{
			return false;
			// non_background can be path, return false
		} else if (!maze.getColor(x, y).equals(NON_BACKGROUND))
		{
			return false;
			// find the path, so return true
		} else if (y == maze.getNRows() - 1 && x == maze.getNCols() - 1)
		{
			// re-color current point as path
			maze.recolor(x, y, PATH);
			return true;
		} else
		{
			// re-color current point as path
			maze.recolor(x, y, PATH);
			if (findMazePath(x + 1, y) || findMazePath(x, y + 1) || findMazePath(x - 1, y) || findMazePath(x, y - 1))
			{
				return true;
			} else
			{
				// re-color current point as TEMPORARY
				maze.recolor(x, y, TEMPORARY);
				return false;
			}
		}

	}

	// ADD METHOD FOR PROBLEM 2 HERE
	public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y)
	{
		// res is to record final result to return
		ArrayList<ArrayList<PairInt>> result = new ArrayList<ArrayList<PairInt>>();
		// record every one path PairInt
		ArrayDeque<PairInt> stack = new ArrayDeque<PairInt>();
		// user helper function to find all path
		findMazePathStackBased(x, y, result, stack);
		if (result.size() == 0) {
			result.add(new ArrayList<PairInt>());
		}
		return result;
	}

	// dfs helper function
	public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, ArrayDeque<PairInt> stack)
	{
		// base case, when to exit
		if (x < 0 || y < 0 || x > maze.getNCols() - 1 || y > maze.getNRows() - 1
				|| !maze.getColor(x, y).equals(NON_BACKGROUND) || maze.getColor(x, y).equals(PATH))
		{
			return;
		}

		if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1)
		{
			stack.offerLast(new PairInt(x, y));
			ArrayList<PairInt> temp = new ArrayList<PairInt>();
			temp.addAll(stack);
			result.add(temp);
			stack.pollLast();
			return;
		}

		// recursion rule
		stack.offerLast(new PairInt(x, y));
		maze.recolor(x, y, PATH);

		findMazePathStackBased(x + 1, y, result, stack);

		findMazePathStackBased(x - 1, y, result, stack);

		findMazePathStackBased(x, y + 1, result, stack);

		findMazePathStackBased(x, y - 1, result, stack);
		maze.recolor(x, y, NON_BACKGROUND);
		stack.pollLast();
		return;
	}

	// ADD METHOD FOR PROBLEM 3 HERE
	// find the shortest path in all paths
	public ArrayList<PairInt> findMazePathMin(int x, int y)
	{
		// all paths
		ArrayList<ArrayList<PairInt>> allPath = findAllMazePaths(x, y);
		// record the shortest path
		int min = allPath.get(0).size();
		ArrayList<PairInt> res = allPath.get(0);
		// linear scan all the path to find the shortest one
		for (int i = 1; i < allPath.size(); i++)
		{
			ArrayList<PairInt> curPath = allPath.get(i);
			if (curPath.size() < min) // current path is shortest
			{
				res = curPath;
				min = curPath.size();
			}
		}
		return res;
	}

	/* <exercise chapter="5" section="6" type="programming" number="2"> */
	public void resetTemp()
	{
		maze.recolor(TEMPORARY, BACKGROUND);
	}
	/* </exercise> */

	/* <exercise chapter="5" section="6" type="programming" number="3"> */
	public void restore()
	{
		resetTemp();
		maze.recolor(PATH, BACKGROUND);
		maze.recolor(NON_BACKGROUND, BACKGROUND);
	}
	/* </exercise> */

	// test
//	public static void main(String[] args)
//	{
//		TwoDimGrid m = new TwoDimGrid(5, 5);
//		Maze maze = new Maze(m);
//		m.recolor(0, 0, NON_BACKGROUND);
//		m.recolor(0, 1, NON_BACKGROUND);
//		m.recolor(0, 2, NON_BACKGROUND);
//		m.recolor(1, 1, NON_BACKGROUND);
//		m.recolor(2, 1, NON_BACKGROUND);
//		m.recolor(2, 2, NON_BACKGROUND);
//		m.recolor(2, 3, NON_BACKGROUND);
//		m.recolor(3, 1, NON_BACKGROUND);
//		m.recolor(3, 3, NON_BACKGROUND);
//		m.recolor(4, 1, NON_BACKGROUND);
//		m.recolor(4, 2, NON_BACKGROUND);
//		m.recolor(4, 3, NON_BACKGROUND);
//		m.recolor(4, 4, NON_BACKGROUND);
//		System.out.println(maze.findAllMazePaths(0, 0));
//		System.out.println(maze.findMazePathMin(0, 0));
//
//	}
}
/* </listing> */
