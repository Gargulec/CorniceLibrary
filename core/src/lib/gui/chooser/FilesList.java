package lib.gui.chooser;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

import lib.gui.CorniceList;

public class FilesList extends CorniceList{

	CorniceFileChooser fc;
	
	//Constructor
	public FilesList(float x, float y, float width, float height, CorniceFileChooser fc)
	{
		super(x, y, width, height);
		
		this.fc = fc;
		
		setMaxElements(8);
		setWrap(true);
		setMaxColumns(2);
		
		this.eWidth = 140;
		this.eHeight = 16;
		this.offset.x = 10;
		this.offset.y = -20;
	}
	
	//Draw element
	Texture folderIcon = new Texture(Gdx.files.classpath("folder.png"));
	Texture fileIcon = new Texture(Gdx.files.classpath("file.png"));
	Texture imageIcon = new Texture(Gdx.files.classpath("image.png"));
	Texture musicIcon = new Texture(Gdx.files.classpath("music.png"));
	Texture execIcon = new Texture(Gdx.files.classpath("executable.png"));
	Texture shortcutIcon = new Texture(Gdx.files.classpath("shortcut.png"));
	public void drawElement(Object element, SpriteBatch batch, ShapeRenderer renderer, Vector2 position) 
	{
		
		FileHandle f = (FileHandle)element;

		//Drawing selection
		if(getSelected() != null && getSelected().equals(element))
		{
			batch.end();
			renderer.begin(ShapeType.Filled);
			renderer.setColor(new Color(.7f,.7f,.7f,1));
			renderer.rect(position.x, position.y + getGapSize() - 3, eWidth, eHeight);
			renderer.end();
			batch.begin();
		}
		
		if(f.isDirectory())
			setElementTexture(folderIcon);
		else if(f.extension().equals("jpg") || f.extension().equals("png"))
			setElementTexture(imageIcon);
		else if(f.extension().equals("vaw") || f.extension().equals("mp3"))
			setElementTexture(musicIcon);
		else if(f.extension().equals("exe") || f.extension().equals("cmd"))
			setElementTexture(execIcon);
		else if(f.extension().equalsIgnoreCase("lnk"))
			setElementTexture(shortcutIcon);
		else
			setElementTexture(fileIcon);
		
		super.drawElement(element, batch, renderer, position);
		
		//File name
		String name = ((FileHandle)element).name();
		//Cutting name to 20 letters
		if(name.length() > 16)
			name = name.substring(0, 15) + "...";
		getFont().draw(batch, name, position.x + 20, position.y - offset.y - 6);
	}
	
	public void elementClicked(Object element, int button) 
	{
		super.elementClicked(element, button);
		select((FileHandle)element);
		
		//Choosing this file
		fc.setChoosed((FileHandle)element);
	}
	
	//Selecting file
	public void select(FileHandle file)
	{
		if(file.isDirectory())
		{
			if(!fc.getPath().equals("") && fc.getPath().charAt(fc.getPath().length()-1) != '\\')
				fc.setPath(fc.getPath() + "\\");
			
			fc.setPath(fc.getPath() + file.name() + "\\");
		}
	}
	//Back
	public void back()
	{
		if(fc.getPath().contains("\\"))
		{
			String newPath = fc.getPath().substring(0, fc.getPath().length() - 1);
			fc.setPath(newPath.substring(0, newPath.lastIndexOf("\\")));
		}
		else
			fc.setPath("");
	}
	
}
