package foxnrabbit;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

import animal.Animal;
import animal.Fox;
import animal.Rabbit;
import field.Field;
import field.Location;
import field.View;

public class FoxAndRabbit {
	private Field field;
	private View view;
	
	public FoxAndRabbit( int size ){
		field = new Field(size, size);
		for ( int row = 0; row < field.getHeight(); row++){
			for ( int col = 0; col < field.getWidth(); col++){
				double rand = Math.random();
				if ( rand < 0.05 ){
					field.place(row, col, new Fox());
				}
				else if ( rand < 0.15){
					field.place(row, col, new Rabbit());
				}
			}
		}
		
		view = new View(field);
		JFrame frame = new JFrame("Fox and Rabbit");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(view);
		JButton btnStep = new JButton("单步");
		btnStep.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				step();
				view.repaint();
			}
		});
		frame.add(btnStep, BorderLayout.NORTH);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void start ( int steps ){
		for( int i = 0; i < steps; i++){
			step();
			view.repaint();
			try{
				Thread.sleep(200);
			} catch ( InterruptedException e ) {
				e.printStackTrace();
			}
		}
	}
	
	public void step(){
		for ( int row = 0; row < field.getHeight(); row++){
			for ( int col = 0; col < field.getWidth(); col++){
				Location loc = new Location(row, col);
				Animal animal = (Animal) field.get(loc);
				if ( animal != null){
					animal.grow();
					if(!animal.isAlive()){
						field.remove(loc);
					}
					else{
						//move
						if(animal.move()){
							Location dest = randomChoice(field.getFreeNeighbors(loc));
							if(dest != null ) 
								field.move(loc, dest);
						}
						
						//feed
						{
							Location[] neighbors = field.getNeighbors(loc);
							ArrayList<Location> rabbits = new ArrayList<Location>();
							for ( Location l : neighbors){
								if ( field.get(l) instanceof Rabbit){
									rabbits.add(l);
								}
							}
							Location Loc = randomChoice(rabbits.toArray(new Location[rabbits.size()]));
							if (Loc != null){
								if(animal.feed())
									field.remove(Loc);
							}									
						}
						
						//breed
						Animal baby = animal.breed();
						if(baby != null){
							Location Loc = randomChoice(field.getFreeNeighbors(loc));
							if(Loc != null)
								field.place(Loc, baby);
						}
					}
				}
			}
		}
	}
	
	public Location randomChoice(Location[] locations){
		Location loc = null;
		int len = locations.length;
		if(len > 0){
			loc = locations[(int)(Math.random()*locations.length)];
		}
		return loc;
	}
	
	public static void main(String[] args) {
		new FoxAndRabbit(50);
	}

}
