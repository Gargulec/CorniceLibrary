package lib.gui.chooser;

import com.badlogic.gdx.files.FileHandle;

import lib.gui.CorniceButton;

public class ButtonChoose extends CorniceButton{

	CorniceFileChooser fc;
	
	//Constructor
	public ButtonChoose(float x, float y, CorniceFileChooser fc) 
	{
		super(x, y, 100, 28, "Choose");
		
		this.fc = fc;
	}
	
	//Clicked
	public void clicked(int button) 
	{
		super.clicked(button);
		
		fc.setChoosed((FileHandle)fc.files.getSelected());
	}

}
