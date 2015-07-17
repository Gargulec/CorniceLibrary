package lib.gui;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap.Filter;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class StudiumLabel extends StudiumComponent{

	//Text
	private ArrayList<String> text = new ArrayList<String>();
	//Text color
	private Color textColor = new Color(1, 1, 1, 1);
	//Font
	private BitmapFont font = new BitmapFont();
	
	//Constructor
	public StudiumLabel(float x, float y, String text) 
	{
		super(x, y, 0, 0);
	
		setBgColor(new Color(0, 0, 0, 0));
		
		this.text.add(text);
	}
	public StudiumLabel(float x, float y, String text, BitmapFont font) 
	{
		super(x, y, 0, 0);
	
		setBgColor(new Color(0, 0, 0, 0));
		
		this.text.add(text);
		
		if(font != null)
		{
			setWidth(font.getBounds(text).width);
			setHeight(font.getBounds(text).height);
			this.font = font;
		}
		else
		{
			setWidth(this.font.getBounds(text).width);
			setHeight(this.font.getBounds(text).height);
		}
	}
	public StudiumLabel(float x, float y, String text, Color textColor, BitmapFont font) 
	{
		super(x, y, 0, 0);
	
		setBgColor(new Color(1, 1, 1, 0));
		
		this.text.add(text);
		this.textColor = textColor;
		
		if(font != null)
		{
			setWidth(font.getBounds(text).width);
			setHeight(font.getBounds(text).height);
			this.font = font;
		}
		else
		{
			setWidth(this.font.getBounds(text).width);
			setHeight(this.font.getBounds(text).height);
		}
	}
	public StudiumLabel(float x, float y, ArrayList<String> text, Color textColor, BitmapFont font) 
	{
		super(x, y, 0, 0);
	
		setBgColor(new Color(1, 1, 1, 0));
		
		this.text = text;
		this.textColor = textColor;
		
		if(font != null)
		{
			setWidth(font.getBounds(text.get(0)).width);
			setHeight(font.getBounds(text.get(0)).height * text.size());
			this.font = font;
		}
		else
		{
			setWidth(this.font.getBounds(text.get(0)).width);
			setHeight(this.font.getBounds(text.get(0)).height * text.size());
		}
	}
	
	//Creating font fromTTF
	public static BitmapFont createFontFromTTF(FileHandle ttfFile, int size)
	{
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(ttfFile);
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = size;
		BitmapFont f = generator.generateFont(parameter);
		generator.dispose();
		return f;
	}
	
	//Drawing label
	public void draw(SpriteBatch batch, ShapeRenderer renderer) 
	{
		if(isActive())
		{
			if(getBgImg() != null)
				batch.draw(getBgImg(), getX(), getY());
			
			//Drawing text
			font.setColor(this.textColor);
			if(this.text.size() < 2)
				font.draw(batch, getText(), getX(), getY() + getHeight() - font.getBounds(getText()).height/2);
			else
			{
				for(int i = 0; i < this.text.size(); i++)
					font.draw(batch, this.text.get(i), getX(), getY() - font.getBounds(this.text.get(i)).height/2 - (font.getBounds(this.text.get(i)).height + 10) * i);
			}
			font.setColor(Color.WHITE);
		}
	}
	
	/**Getters & Setters**/
	public String getText() 
	{
		if(this.text.size() == 0)
			return "";
		else
			return this.text.get(0);
	}
	public ArrayList<String> getTextList() 
	{
		return this.text;
	}
	public void setText(String text)
	{
		if(this.text.size() == 0)
			this.text.add(text);
		else
			this.text.set(0, text);
	}
	public void setTextList(ArrayList<String> text)
	{
		this.text = text;
	}
	
	public Color getTextColor() 
	{
		return textColor;
	}
	public void setTextColor(Color textColor) 
	{
		this.textColor = textColor;
	}
	
	public BitmapFont getFont() 
	{
		return font;
	}
	public void setFont(BitmapFont font)
	{
		this.font = font;
	}
	
}
