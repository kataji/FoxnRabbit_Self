package animal;

import java.awt.Color;
import java.awt.Graphics;

import cell.Cell;
import field.Location;

public abstract class Animal implements Cell{
	private int age;
	private int breedableAge;
	private int ageLimit;
	private boolean alive = true;
	
	private Color color;
	
	private double moveRate;
	private double feedRate; 
	private double breedRate;
	
	public Animal(int breedableAge, int ageLimit, Color color, 
			double moveRate, double feedRate, double breedRate){
		this.breedableAge = breedableAge;
		this.ageLimit = ageLimit;
		this.color = color;
		this.moveRate = moveRate;
		this.feedRate = feedRate;
		this.breedRate = breedRate;
	}
	
	public int getAge(){
		return age;
	}
	
	public double getAgePercent(){
		return age/(double)ageLimit;
	}
	
	public boolean isBreedable(){
		return age>=breedableAge;
	}
	
	public boolean isAlive(){
		return alive;
	}
	
	public void grow(){
		age++;
		if(age>ageLimit){
			die();
		}
	}
	
	public void die(){
		alive = false;
	}
	
	
	public Location operation(Location[] neighbors, double rate){
		Location ret = null;
		int len = neighbors.length;
		if ( len > 0 && Math.random() < rate){
			ret = neighbors[ (int)(Math.random() * len)];
		}
		return ret;
	}
	
//	public Location move(Location[] freeneighbors){
//		return operation(freeneighbors, moveRate);
//	}
//	
//	public Location feed(Location[] neighborfoods){
//		Location ret = operation(neighborfoods, feedRate);
//		if(ret != null){
//			longerLife(2);
//		}
//		return ret;
//	}
//	
//	public Location breed(Location[] freeneighbors){
//		Location ret = null;
//		if(this.isBreedable()){
//			ret = operation(freeneighbors, breedRate);
//		}
//		return ret;
//	}
	
	public boolean move(){
		return Math.random() < moveRate;
	}
	
	public boolean feed(){
		boolean fed = Math.random() <feedRate;
		if(fed){
			longerLife(2);
		}
		return fed;
	}
	
	public boolean dobreed(){
		return isBreedable() && Math.random() <breedRate;
	}
	
	public abstract Animal breed();
	
	protected void longerLife(int inc){
		ageLimit += inc;
	}
	
	@Override
	public void draw(Graphics g, int x, int y, int size) {
		int alpha = (int)((1-getAgePercent()) * 255);
		g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha));
		g.fillRect(x, y, size, size);	
	}

}
