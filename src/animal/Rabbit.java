package animal;

import java.awt.Color;

public class Rabbit extends Animal{
	public Rabbit(){
		super(2, 10, new Color(255, 0, 0), 0.02, 0, 0.12);
	}
	
	@Override
	public Rabbit breed(){
		Rabbit rabbit = null;
		if(super.dobreed()){
			rabbit = new Rabbit();
		}
		return rabbit;
	}
}
