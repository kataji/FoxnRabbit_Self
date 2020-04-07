package field;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import cell.Cell;

public class View extends JPanel{
	private static final long serialVersionUID = -5823387258984462085L;
	private Field field;
	private static final int GRID_SIZE = 16;
	
	public View(Field field){
		this.field = field;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.GRAY);
		for ( int row = 0; row <= field.getHeight(); row++ ){
			g.drawLine(0, row*GRID_SIZE, field.getWidth()*GRID_SIZE, row*GRID_SIZE);
		}
		for ( int col = 0; col <= field.getHeight(); col++ ){
			g.drawLine(col*GRID_SIZE, 0, col*GRID_SIZE, field.getHeight()*GRID_SIZE);
		}
		
		for ( int row = 0; row < field.getHeight(); row++ ){
			for ( int col = 0; col < field.getWidth(); col++){
				Cell cell = field.get(row, col);
				if ( cell != null){
					cell.draw(g, col*GRID_SIZE, row*GRID_SIZE, GRID_SIZE);
				}
			}
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(field.getWidth()*GRID_SIZE+1, field.getHeight()*GRID_SIZE+1);
	}
}
