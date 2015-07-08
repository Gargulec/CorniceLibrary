package lib.gui;

public class StudiumWindow extends StudiumComponent{

	//Constructor
	public StudiumWindow(float x, float y, float width, float height)
	{
		super(x, y, width, height);
		
		setActive(false);
		setVisible(false);
	}
	
	//Adding new component
	public void add(StudiumComponent component)
	{
		component.setParent(this);
		getChildren().add(component);
	}
	
	//Window closed
	public void closed()
	{
		for(StudiumComponent c : getChildren())
		{
			if (c instanceof StudiumTextField)
				((StudiumTextField) c).setFocused(false);
		}
	}

}
