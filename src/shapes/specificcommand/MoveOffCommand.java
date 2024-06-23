package shapes.specificcommand;

import java.awt.geom.Point2D;

import command.Command;
import shapes.model.GraphicObject;

public class MoveOffCommand implements Command{
	private final Point2D oldPos;
	private final Point2D newPos;
	GraphicObject object;
	
	public MoveOffCommand(GraphicObject go, Point2D pos) {
		oldPos = go.getPosition();
		newPos = pos;
		this.object = go;
	}

	@Override
	public boolean doIt() {
		Point2D pos = new Point2D.Double(oldPos.getX(),oldPos.getY()); 
		object.moveTo(pos.getX()+newPos.getX(),pos.getY()+newPos.getY());
		return true;
	}

	@Override
	public boolean undoIt() {
		object.moveTo(oldPos);
		return true;
	}
	
	
}
