package lib.gui;

import java.nio.channels.ClosedByInterruptException;
import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class StudiumScreen extends InputAdapter implements Screen{

	//SpriteBatch
	protected SpriteBatch batch;
	//ShapeRenderer
	protected ShapeRenderer shapeRenderer;
	//Orthographic camera
	protected OrthographicCamera camera;
	
	//Static components
	private boolean staticComponentsEndabled = true;
	public static ArrayList<StudiumWindow> staticWindows = new ArrayList<StudiumWindow>();
	
	//Background image
	protected Texture bgImg;
	
	//Screen componenets
	private ArrayList<StudiumComponent> components = new ArrayList<StudiumComponent>();
	private ArrayList<StudiumWindow> windows = new ArrayList<StudiumWindow>();
	
	//Constructor
	public StudiumScreen()
	{
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.translate(new Vector2(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2));
	}
	
	public void render(float delta) 
	{
		//Clearing screen
		Gdx.gl20.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//Game update
		update();
		//Updating camera
		camera.update();
		//Updating ShapeRenderer
		shapeRenderer.setProjectionMatrix(camera.combined);
		//Updating SpriteBatch
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		/**Rendering screen**/
		draw();
		/**End of rendering**/
		batch.end();
	}

	//Screen update
	public void update(){}
	
	//Drawing screen
	public void draw()
	{
		//Drawing background
		if(bgImg != null)
			batch.draw(bgImg, 0, 0);
		//Drawing components
		for(StudiumComponent c : components)
			c.draw(batch, shapeRenderer);
		//Drawing windows
		for(StudiumWindow w : windows)
			w.draw(batch, shapeRenderer);
		//Drawing static windows
		if(isStaticComponentsEndabled())
		{
			for(StudiumWindow w : staticWindows)
				w.draw(batch, shapeRenderer);
		}
	}
	
	//Adding new component
	public void add(StudiumComponent component)
	{
		if(component instanceof StudiumWindow)
			windows.add((StudiumWindow)component);
		else
		{
			if(component instanceof StudiumTextField)
				((StudiumTextField)component).setScreenParent(this);
			components.add(component);
		}
	}
	public static void addStatic(StudiumWindow component)
	{
		if(component instanceof StudiumWindow)
			staticWindows.add((StudiumWindow)component);
	}
	
	public void removeAllComponents()
	{
		components.clear();
	}
	
	//Changing screen
	public static void change(Game game, StudiumScreen to)
	{
		System.err.println("changing screen");
		
		try
		{
//			((StudiumScreen)game.getScreen()).closed();
			game.setScreen(to);
			Gdx.input.setInputProcessor(to);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//Resize of screen
	public void resize(int width, int height) 
	{
		camera = new OrthographicCamera(width, height);
		camera.translate(new Vector2(width/2, height/2));
	}
	
	//Screen closed
	public void closed()
	{
		for(StudiumWindow w : windows)
		{
			for(StudiumComponent c : w.getChildren())
			{
				if (c instanceof StudiumTextField)
					((StudiumTextField) c).setFocused(false);
			}
		}
	}

	public void show(){}
	public void hide(){}
	public void pause(){}
	public void resume(){}

	public void dispose() 
	{
		batch.dispose();
		shapeRenderer.dispose();
	}
	
	/**Input**/
	boolean cont = true;
	public boolean touchDown(int screenX, int screenY, int pointer, int button) 
	{
		//Unfocusing text fields
		for(StudiumComponent t : components)
		{
			if (t instanceof StudiumTextField)
			{
				StudiumTextField tf = (StudiumTextField) t;
				tf.setFocused(false);
			}
			else
			{
				for(StudiumComponent tt : t.getChildren())
				{
					if (tt instanceof StudiumTextField)
					{
						StudiumTextField tf = (StudiumTextField) tt;
						tf.setFocused(false);
					}
				}
			}
		}
		for(StudiumWindow w : windows)
		{
			for(StudiumComponent t : w.getChildren())
			{
				if (t instanceof StudiumTextField)
				{
					StudiumTextField tf = (StudiumTextField) t;
					tf.setFocused(false);
				}
			}
		}
		if(isStaticComponentsEndabled())
		{
			for(StudiumWindow w : staticWindows)
			{
				for(StudiumComponent t : w.getChildren())
				{
					if (t instanceof StudiumTextField)
					{
						StudiumTextField tf = (StudiumTextField) t;
						tf.setFocused(false);
					}
				}
			}
		}
		
		//Clicking components
		for(StudiumWindow w : windows)
		{
			if(w.isVisible())
			{
				for(StudiumComponent t : w.getChildren())
				{
					if(screenX >= t.getX() && screenX <= t.getX() + t.getWidth())
					{
						if((Gdx.graphics.getHeight() - screenY) >= t.getY() && (Gdx.graphics.getHeight() - screenY) <= t.getY() + t.getHeight())
						{
							if(t.isVisible() && t.isActive())
							{
								t.clicked(button);
								
								if(button == 0)
									t.leftClick();
								else
									t.rightClick();
								
								return true;
							}
						}
					}
				}
			}
		}
		if(isStaticComponentsEndabled())
		{
			for(StudiumWindow w : staticWindows)
			{
				for(StudiumComponent t : w.getChildren())
				{
					if(screenX >= t.getX() && screenX <= t.getX() + t.getWidth())
					{
						if((Gdx.graphics.getHeight() - screenY) >= t.getY() && (Gdx.graphics.getHeight() - screenY) <= t.getY() + t.getHeight())
						{
							if(t.isVisible() && t.isActive())
							{
								t.clicked(button);
								
								if(button == 0)
									t.leftClick();
								else
									t.rightClick();
								
								return true;
							}
						}
					}
				}
			}
		}
		
		for(StudiumComponent c : components)
		{
			if(c.isVisible())
			{
				//Component children
				for(StudiumComponent cc : c.getChildren())
				{
					if(screenX >= cc.getX() && screenX <= cc.getX() + cc.getWidth())
					{
						if((Gdx.graphics.getHeight() - screenY) >= cc.getY() && (Gdx.graphics.getHeight() - screenY) <= cc.getY() + cc.getHeight())
						{
							if(cc.isVisible() && cc.isActive())
							{
								cc.clicked(button);
								
								if(button == 0)
									cc.leftClick();
								else
									cc.rightClick();
							}
						}
					}
				}
			}
			
			if(screenX >= c.getX() && screenX <= c.getX() + c.getWidth())
			{
				if((Gdx.graphics.getHeight() - screenY) >= c.getY() && (Gdx.graphics.getHeight() - screenY) <= c.getY() + c.getHeight())
				{
					if(c.isVisible() && c.isActive())
					{
						c.clicked(button);
						if(button == 0)
							c.leftClick();
						else
							c.rightClick();
					
					}
				}
			}
			
		}
		
		//Screen clicked
		screenClicked(button);
		
		return super.touchDown(screenX, screenY, pointer, button);
	}
	
	//Screen clicked
	public void screenClicked(int button){}
	
	//Key down
	public boolean keyTyped(char character) 
	{
		boolean keyTypedToTextField = false;
		for(StudiumWindow w : windows)
		{
			//Window children
			for(StudiumComponent c : w.getChildren())
			{
				if(c.isActive())
				{
					if(c instanceof StudiumTextField )
					{
						if(((StudiumTextField)c).isFocused())
						{
							if(!keyTypedToTextField)
							{
								c.keyTyped(character);
								keyTypedToTextField = true;
							}
						}
					}
					else
						c.keyTyped(character);
				}
			}
		}
		
		if(isStaticComponentsEndabled())
		{
			for(StudiumWindow w : staticWindows)
			{
				//Window children
				for(StudiumComponent c : w.getChildren())
				{
					if(c.isActive())
						c.keyTyped(character);
				}
			}
		}
		
		for(StudiumComponent c : components)
		{
			if(c.isActive())
				c.keyTyped(character);

			//Component children
			for(StudiumComponent cc : c.getChildren())
			{
				if(cc.isActive())
					cc.keyTyped(character);
			}
		}
		
		return super.keyTyped(character);
	}

	/**Getters & Setters**/
	public ArrayList<StudiumComponent> getComponents()
	{
		return this.components;
	}

	public ArrayList<StudiumWindow> getWindows()
	{
		return windows;
	}
	public void setWindows(ArrayList<StudiumWindow> windows) 
	{
		this.windows = windows;
	}

	public boolean isStaticComponentsEndabled() 
	{
		return staticComponentsEndabled;
	}
	public void setStaticComponentsEndabled(boolean staticComponentsEndabled) 
	{
		this.staticComponentsEndabled = staticComponentsEndabled;
	}

}
