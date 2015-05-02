package lib.gui;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class StudiumComponent {

	//Coordinates
	protected float x, y;
	//Size
	protected float width;
	protected float height;
	//Active
	private boolean isActive = true;
	//Visible
	private boolean visible = true;
	//Background
	private Color bgColor = new Color(.8f, .8f, .8f, 1);
	private Texture bgImg;
	//Parent
	private StudiumComponent parent;
	//Child componenets
	private ArrayList<StudiumComponent> children = new ArrayList<StudiumComponent>();

	//Constructor
	public StudiumComponent(float x, float y, float width, float height)
	{
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
	}
	
	//Adding new component
	public void add(StudiumComponent component)
	{
		component.setParent(this);
		children.add(component);
	}
	
	//Drawing component
	protected Texture t = new Texture(Gdx.files.classpath("images/component.png"));
	public void draw(SpriteBatch batch, ShapeRenderer renderer)
	{
		if(isVisible())
		{
			float dX = getX(), dY = getY();

			if(getBgImg() != null)
				batch.draw(getBgImg(), dX, dY);
			else
			{
				batch.setColor(getBgColor());
				batch.draw(t, dX, dY, getWidth(), getHeight());
				batch.setColor(1, 1, 1, 1);
			}
			
			//Drawing children
			for(StudiumComponent c : this.children)
				c.draw(batch, renderer);
		}
	}
	
	//Component clicked
	public void clicked(int button){}
	//Left click
	public void leftClick(){}
	//Right click
	public void rightClick(){}
	
	//Key down
	public void keyTyped(char letter){}
	
	//Toggling
	public void toggle()
	{
		setVisible(!isVisible());
		
		if(isVisible())
			opened();
	}
	
	//Show
	public void opened(){}
	
	/**Getters & Setters**/
	public float getX() 
	{
		if(getParent() != null)
			return x + this.parent.x;
		
		return x;
	}
	public void setX(float x) 
	{
		this.x = x;
	}
	
	public float getY() 
	{
		if(getParent() != null)
			return y + this.parent.y;
		
		return y;
	}
	public void setY(float y)
	{
		this.y = y;
	}
	
	public float getWidth() 
	{
		return width;
	}
	public void setWidth(float width) 
	{
		this.width = width;
	}
	
	public float getHeight()
	{
		return height;
	}
	public void setHeight(float height) 
	{
		this.height = height;
	}
	
	public Color getBgColor()
	{
		return bgColor;
	}
	public void setBgColor(Color bgColor)
	{
		this.bgColor = bgColor;
	}
	
	public Texture getBgImg() 
	{
		return bgImg;
	}
	public void setBgImg(Texture bgImg)
	{
		this.bgImg = bgImg;
	}
	
	public ArrayList<StudiumComponent> getChildren() 
	{
		return children;
	}
	public void setChildren(ArrayList<StudiumComponent> children) 
	{
		this.children = children;
	}

	public StudiumComponent getParent() 
	{
		return parent;
	}
	public void setParent(StudiumComponent parent) 
	{
		this.parent = parent;
	}
	
	public boolean isActive()
	{
		return isActive;
	}
	public void setActive(boolean isActive) 
	{
		this.isActive = isActive;
	}

	public boolean isVisible()
	{
		return visible;
	}
	public void setVisible(boolean visible) 
	{
		this.visible = visible;
	}
	
}
