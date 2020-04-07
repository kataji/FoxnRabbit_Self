package animal;

import java.awt.Color;

public class Fox extends Animal{
	
	public Fox(){
		super(4, 20, new Color(0, 0, 0), 0.02, 0.2, 0.05);
	}

	@Override
	public Animal breed(){
		Fox fox = null;
		if(super.dobreed()){
			fox = new Fox();
		}
		return fox;
	}
}
