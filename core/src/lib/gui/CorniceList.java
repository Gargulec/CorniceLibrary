package lib.gui;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class CorniceList extends StudiumComponent{

	//Selected element
	private Object selected;
	
	//List
	private ArrayList<Object> elements = new ArrayList<Object>();
	//Font
	protected BitmapFont font = new BitmapFont();
	
	//Offset
	protected Vector2 offset = new Vector2();
	//Element size
	protected float eWidth, eHeight;
	//Max elements
	protected int maxElements = 100;
	//Resize
	protected boolean resize = false;
	//Element background
	protected Color eColor = new Color(Color.WHITE);
	protected Texture eTexture;
	//Gap between elements
	protected float gapSize = 2;
	//Wrap
	protected boolean wrap = false;
	protected int wrapGap = 5;
	protected int maxColumns = 1;
	
	//Constructor
	public CorniceList(float x, float y, float width, float height)
	{
		super(x, y, width, height);
		
		setBgColor(new Color(1, 0, 1, 1));
		
		eWidth = this.width;
		eHeight = font.getBounds("A").height;
	}
	
	//Draw
	public void draw(SpriteBatch batch, ShapeRenderer renderer) 
	{
		super.draw(batch, renderer);
		//Drawing elements
		int id = 0;
		float offset = 0;
		float offsetX = 0;
		for(Object o : elements)
		{
			drawElement(o, batch, renderer, new Vector2(getX() + this.offset.x + offsetX, getY() + getHeight() + offset + this.offset.y));
			id++;
			offset = -(id%getMaxElements() * (eHeight + gapSize));
			
			if(isWrap())
				offsetX = ((int)id/getMaxElements())*eWidth;
			if(offsetX > 0)
				offsetX += getWrapGap();
			
			if(id >= getMaxElements() && !isWrap() || ((int)id/getMaxElements()) >= getMaxColumns())
				break;
		}
	}
	
	//Clicked
	public void clicked(int button)
	{
		float mX = Gdx.input.getX();
		float mY = Gdx.graphics.getHeight() - Gdx.input.getY();
		
		float x, y;
		setSelected(null);
		
		for(int i = 0; i < elements.size(); i++)
		{
			x = getX() + ((int)i/getMaxElements())*eWidth;
			if(x > getX())
				x += getWrapGap();
			
			if(mX >= x + offset.x && mX <= x + offset.x + eWidth)
			{
				y = getY() + getHeight() - (i%getMaxElements()+1) * (eHeight + gapSize);
	
				if(mY >= y && mY <= y + eHeight)
				{
					elementClicked(elements.get(i), button);
					setSelected(elements.get(i));
				}
			}
		}
	}
	
	//Element clicked
	public void elementClicked(Object element, int button)
	{
		setSelected(element);
	}
	
	//Drawing element of list
	public void drawElement(Object element, SpriteBatch batch, ShapeRenderer renderer, Vector2 position)
	{
		//Selection
		if(getSelected() == element)
		{
			batch.end();
			renderer.begin(ShapeType.Filled);
			renderer.setColor(new Color(.7f,.7f,.7f,1));
			renderer.rect(position.x, position.y + getGapSize(), eWidth, eHeight);
			renderer.end();
			batch.begin();
		}
		//Drawing background
		if(eTexture != null)
			batch.draw(eTexture, position.x, position.y);
			
	}

	//Getters & Setters
	public ArrayList<Object> getElements() 
	{
		return elements;
	}
	public void setElements(ArrayList elements)
	{
		this.elements = elements;
	}

	public float getElementWidth() 
	{
		return eWidth;
	}

	public float getElementHeight()
	{
		return eHeight;
	}

	public BitmapFont getFont()
	{
		return font;
	}
	public void setFont(BitmapFont font) 
	{
		this.font = font;
	}

	public Color geteColor()
	{
		return eColor;
	}
	public void seteColor(Color eColor) 
	{
		this.eColor = eColor;
	}

	public Vector2 getOffset()
	{
		return offset;
	}
	public void setOffset(Vector2 offset) 
	{
		this.offset = offset;
	}

	public float getGapSize() 
	{
		return gapSize;
	}
	public void setGapSize(float gapSize)
	{
		this.gapSize = gapSize;
	}

	public int getMaxElements() 
	{
		return maxElements;
	}
	public void setMaxElements(int maxElements) 
	{
		this.maxElements = maxElements;
	}

	public boolean isResize()
	{
		return resize;
	}
	public void setResize(boolean resize) 
	{
		this.resize = resize;
	}

	public Texture getElementTexture()
	{
		return eTexture;
	}
	public void setElementTexture(Texture eTexture)
	{
		this.eTexture = eTexture;
	}

	public boolean isWrap() 
	{
		return wrap;
	}
	public void setWrap(boolean wrap)
	{
		this.wrap = wrap;
	}

	public int getWrapGap() 
	{
		return wrapGap;
	}
	public void setWrapGap(int wrapGap)
	{
		this.wrapGap = wrapGap;
	}

	public int getMaxColumns() 
	{
		return maxColumns;
	}
	public void setMaxColumns(int maxColumns)
	{
		this.maxColumns = maxColumns;
	}

	public Object getSelected() 
	{
		return selected;
	}
	public void setSelected(Object selected) 
	{
		this.selected = selected;
	}

}
