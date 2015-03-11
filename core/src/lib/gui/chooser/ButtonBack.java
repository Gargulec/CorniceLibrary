package lib.gui.chooser;

import lib.gui.CorniceButton;

public class ButtonBack extends CorniceButton{

	CorniceFileChooser fc;
	
	//Constructor
	public ButtonBack(float x, float y, CorniceFileChooser fc) 
	{
		super(x, y, 50, 28, "<--");
		
		this.fc = fc;
	}
	
	//Clicked
	public void clicked(int button) 
	{
		super.clicked(button);
		
		fc.files.back();
	}

}
