package model;

public interface Directable {
	
	public static int WEST = 0;
    public static int SOUTH = 1;
    public static int NORTH = 2;
    public static int EAST = 3;
    
    public int getDirection();

}
