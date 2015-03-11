package lib.gui.chooser;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import lib.gui.CorniceLabeledField;

public class DiskField extends CorniceLabeledField{

	CorniceFileChooser fc;
	
	//Constructor
	public DiskField(float x, float y, CorniceFileChooser fc) 
	{
		super(x, y, 30, 20);
		
		this.fc = fc;
		
		setText("");
		
		setTextOffset(new Vector2(2,2));
	
		setLabelText("Disc");
	}
	
	//Draw
	public void draw(SpriteBatch batch, ShapeRenderer renderer) 
	{
		setText(fc.getDisc());
		
		super.draw(batch, renderer);
	}

}
