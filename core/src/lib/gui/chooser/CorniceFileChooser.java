package lib.gui.chooser;

import java.io.File;
import java.util.ArrayList;

import lib.gui.CorniceButton;
import lib.gui.CorniceLabeledField;
import lib.gui.CorniceList;
import lib.gui.StudiumWindow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class CorniceFileChooser extends StudiumWindow{

	//Selected
	private FileHandle choosed;
	//Path
	private String path = "";
	
	//Files list
	FilesList files;
	//Filter
	private String disc = "C:\\";
	private String filter = "png";
	//Path field
	CorniceLabeledField pathField;
	//Select button
	ButtonChoose selectButton;
	//Back button
	ButtonBack buttonBack;
	//DiskField
	DiskField diskField;
	
	//Constructor
	public CorniceFileChooser(float x, float y)
	{
		super(x, y, 320, 250);
	
		path = Gdx.files.getLocalStoragePath().substring(3, Gdx.files.getLocalStoragePath().length());
		
		setBgColor(new Color(1f, 1f, 1f, 1));
		
		//Files list
		files = new FilesList(10, 80, 300, 160, this);
		
		//Disc
		diskField = new DiskField(10, 40, this);
		//Path
		pathField = new CorniceLabeledField(60, 40, 300, 20);
		pathField.setTextOffset(new Vector2(5, 3));
		pathField.setLabelText("Path");
		//Select button
		selectButton = new ButtonChoose(200, 6, this);
		buttonBack = new ButtonBack(10, 6, this);
		
		add(files);
		add(diskField);
		add(pathField);
		add(selectButton);
		add(buttonBack);
	}
	
	//Draw
	public void draw(SpriteBatch batch, ShapeRenderer renderer) 
	{
		files.setElements(getFiles());
		
		pathField.setText(disc + path);
		
		super.draw(batch, renderer);
	}
	
	//Getting files
	ArrayList getFiles()
	{
		ArrayList<Object> files = new ArrayList<Object>();
		
		FileHandle file = new FileHandle(new File(disc + path));
		//Filtering
		for(FileHandle f : file.list())
		{
			if(!getFilter().equals(""))
			{
				if(f.isDirectory())
					files.add(f);
				else
				{
					for(String s : getFilter().split(","))
					{
						System.err.println();
						if(f.extension().equalsIgnoreCase(s.trim()))
						{
							files.add(f);
							break;
						}
					}
				}
			}
			else
				files.add(f);
		}
		
		return files;
	}

	//Getters & Setters
	public String getPath() 
	{
		return path;
	}
	public void setPath(String path)
	{
		this.path = path;
	}

	public String getDisc() 
	{
		return disc;
	}
	public void setDisc(String disc) 
	{
		this.disc = disc;
	}

	public FileHandle getChoosed()
	{
		return choosed;
	}
	public void setChoosed(FileHandle choosed) 
	{
		this.choosed = choosed;
	}

	public String getFilter()
	{
		return filter;
	}
	public void setFilter(String filter) 
	{
		this.filter = filter;
	}
	
}
