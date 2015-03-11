package lib.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class CorniceButton extends StudiumComponent{

	//Font
	private BitmapFont font = new BitmapFont();
	//Text
	private String text =  "";
	
	//Constructor
	public CorniceButton(float x, float y, float width, float height, String text) 
	{
		super(x, y, width, height);
		
		setText(text);
	}
	
	//Draw
	public void draw(SpriteBatch batch, ShapeRenderer renderer)
	{
		super.draw(batch, renderer);
		
		//Drawing text
		font.setColor(Color.WHITE);
		float tWidth = font.getBounds(getText()).width;
		float tHeight = font.getBounds(getText()).height;
		font.draw(batch, getText(), getX() + width/2 - tWidth/2, getY() + getHeight() - tHeight/2 - 2);
	}
	
	//Getters & Setters
	public BitmapFont getFont() 
	{
		return font;
	}
	public void setFont(BitmapFont font)
	{
		this.font = font;
	}

	public String getText()
	{
		return text;
	}
	public void setText(String text)
	{
		this.text = text;
	}

}
