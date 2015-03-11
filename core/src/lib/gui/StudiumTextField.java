package lib.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class StudiumTextField extends StudiumComponent{

	//Screen - parent
	private StudiumScreen screenParent;
	//Active
	protected boolean isFocused;
	//Active texture
	protected Texture activeTexture;
	//Unactive texture
	protected Texture unactiveTexture;
	//Text
	protected String text = "Test";
	//Font
	protected BitmapFont font = new BitmapFont();
	//Text offset
	protected Vector2 textOffset = new Vector2();
	//Char limit
	protected int charLimit = 20;
	
	//Number only
	protected boolean numberOnly;
	
	//Constructor
	public StudiumTextField(float x, float y, float width, float height) 
	{
		super(x, y, width, height);
	}
	
	//Clicked
	public void leftClick() 
	{
		if(getScreenParent() != null)
		{
			for(StudiumComponent t : getScreenParent().getComponents())
			{
				if (t instanceof StudiumTextField)
				{
					StudiumTextField tf = (StudiumTextField) t;
					tf.setFocused(false);
				}
			}
		}
		
		setFocused(true);
	}
	
	//KeyDown
	public void keyTyped(char letter) 
	{
		if(isFocused())
		{
			//Text typed (Enter pressed)
			if((int)letter == 13)
			{
				setFocused(false);
				textTyped();
				setFocused(true);
			}

			//If number only - checking if letter is number/minus/delete
			if(numberOnly)
				if(!(letter == 45 || letter == 8 || (letter >= 48 && letter <= 57)))
					return;
			
			//Typing text
			addLetter(letter);
		}
	}	
	
	//Adding char
	void addLetter(char letter)
	{
		System.out.println((int)letter);
		
		if((int)letter == 8)
			removeLetter();
		else
		{
			if(getText().length() < this.charLimit)
			{
				this.text += letter;
				this.text = getText().replaceAll("\\p{C}", "");
			}
		}
	}
	
	//Removing char
	void removeLetter()
	{
		if(getText().length() >= 1)
		{
			String newString = "";
			for(int i = 0; i < getText().length() - 1; i++)
			{
				newString += getText().charAt(i);
			}
			this.text = newString;
		}
	}
	
	//Text typed
	public void textTyped(){}
	
	//Render
	public void draw(SpriteBatch batch) 
	{
		if(isVisible())
		{
			if(getBgImg() == null)
			{
				if(isFocused() && getActiveTexture() == null)
					setBgColor(new Color(.8f, .8f, .8f, 1));
				else if(!isFocused() && getUnactiveTexture() == null)
					setBgColor(new Color(.5f, .5f, .5f, 1));
			
				batch.setColor(getBgColor());
				batch.draw(t, getX(), getY(), getWidth(), getHeight());
				batch.setColor(1, 1, 1, 1);
			}
			else
			{
				batch.draw(getBgImg(), getX(), getY());
			}
			
			//Drawing text
			getFont().draw(batch, getText(), getX() + getTextOffset().x, getY() + getHeight() - getTextOffset().y);
		}
	}
	
	/**Getters & Setters**/
	public boolean isFocused() 
	{
		return isFocused;
	}
	public void setFocused(boolean isFocused) 
	{
		if(this.isFocused == true && isFocused == false)
		{
			this.isFocused = false;
			textTyped();
			this.isFocused = true;
		}
		
		this.isFocused = isFocused;
	}
	
	public StudiumScreen getScreenParent() 
	{
		return screenParent;
	}
	public void setScreenParent(StudiumScreen screenParent) 
	{
		this.screenParent = screenParent;
	}
	
	public Texture getActiveTexture() 
	{
		return activeTexture;
	}
	public void setActiveTexture(Texture activeTexture)
	{
		this.activeTexture = activeTexture;
	}
	
	public Texture getUnactiveTexture() 
	{
		return unactiveTexture;
	}
	public void setUnactiveTexture(Texture unactiveTexture)
	{
		this.unactiveTexture = unactiveTexture;
	}
	
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
	public float getFloat()
	{
		try
		{
			float var = Float.parseFloat(getText());
			System.err.println("Parsed: " + var);
			return var;
		}
		catch(NumberFormatException nfe)
		{
			if(numberOnly && !getText().equals("-"))
			{
				if(isFocused)
					setText("");
				else
					setText("0");
			}
			
			return 0;
		}
	}
	public void setText(String text) 
	{
		this.text = text;
	}
	
	public Vector2 getTextOffset() 
	{
		return textOffset;
	}
	public void setTextOffset(Vector2 textOffset)
	{
		this.textOffset = textOffset;
	}
	
	public int getCharLimit()
	{
		return charLimit;
	}
	public void setCharLimit(int charLimit)
	{
		this.charLimit = charLimit;
	}
	
	public void setNumberOnly(boolean numberOnly)
	{
		this.numberOnly = numberOnly;
	}
	
}
