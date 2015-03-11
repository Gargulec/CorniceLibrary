package lib.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class CorniceLabeledField extends StudiumTextField{

	//Label
	private String labelText = "";
	//Label offset
	private Vector2 labelOffset = new Vector2();
	
	//Constructor
	public CorniceLabeledField(float x, float y, float width, float height) 
	{
		super(x, y, width, height);
	}
	
	//Render
	public void draw(SpriteBatch batch, ShapeRenderer renderer) 
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
			
			//Drawing lable
			getFont().draw(batch, getLabelText(), getX() + getLabelOffset().x, getY() + getHeight() + getFont().getBounds(getLabelText()).height + 3 - getLabelOffset().y);
			//Drawing text
			getFont().draw(batch, getText(), getX() + getTextOffset().x, getY() + getHeight() - getTextOffset().y);
		}
	}
	
	/**Getters & Setters**/
	public String getLabelText() 
	{
		return labelText;
	}
	public void setLabelText(String labelText) 
	{
		this.labelText = labelText;
	}

	public Vector2 getLabelOffset() 
	{
		return labelOffset;
	}
	
	public void setLabelOffset(Vector2 labelOffset)
	{
		this.labelOffset = labelOffset;
	}
	
}
