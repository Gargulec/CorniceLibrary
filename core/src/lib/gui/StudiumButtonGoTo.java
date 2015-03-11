package lib.gui;

import com.badlogic.gdx.Game;

public class StudiumButtonGoTo extends StudiumComponent{

	//Game
	private Game game;
	//Purpose
	private StudiumScreen purpose;
	
	//Constructor
	public StudiumButtonGoTo(float x, float y, float width, float height, Game game, StudiumScreen purpose) 
	{
		super(x, y, width, height);
		
		this.game = game;
		System.out.println(purpose);
		this.purpose = purpose;
	}
	
	//Clicked
	public void clicked(int button)
	{
		if(this.purpose != null)
			StudiumScreen.change(this.game, this.purpose);
	}

}
