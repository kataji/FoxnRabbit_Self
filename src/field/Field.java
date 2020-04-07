package field;

import java.util.ArrayList;

import cell.Cell;

public class Field {
	private int height;
	private int width;
	private Cell[][] field;
	
	public Field(int height, int width){
		this.height = height;
		this.width = width;
		field = new Cell[height][width];
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getWidth(){
		return width;
	}
	
	public Cell place(int row, int col, Cell cell){
		field[row][col] = cell;
		return cell;
	}
	
	public Cell place(Location loc, Cell cell){
		field[loc.getRow()][loc.getCol()] = cell;
		return cell;
	}
	
	public Cell get(int row, int col){
		return field[row][col];
	}
	
	public Cell get(Location loc){
		return field[loc.getRow()][loc.getCol()];
	}
	
	public void remove(Location loc){
		field[loc.getRow()][loc.getCol()] = null;
	}
	
	public void move(Location src, Location dest){		
		field[dest.getRow()][dest.getCol()] = field[src.getRow()][src.getCol()];
		field[src.getRow()][src.getCol()] = null;
	}
	
	public Location[] getNeighbors(Location loc){
		ArrayList<Location> neighbors = new ArrayList<Location>();
		for ( int i = -1; i < 2; i++){
			for ( int j = -1; j < 2; j++){
				int r = loc.getRow() + i;
				int c = loc.getCol() + j;
				if ( r>-1 && r<height && c>-1 && c<width && !(i==0 && j==0)){
					neighbors.add(new Location(r, c));
				}
			}
		}
		
		return neighbors.toArray(new Location[neighbors.size()]);
	}
	
	public Location[] getFreeNeighbors(Location loc){
		Location[] neighbors = getNeighbors(loc);
		ArrayList<Location> freeneighbors = new ArrayList<Location>();
		for(Location l: neighbors){
			if ( get(l) == null){
				freeneighbors.add(l);
			}
		}
		return freeneighbors.toArray(new Location[freeneighbors.size()]);
	}
}
