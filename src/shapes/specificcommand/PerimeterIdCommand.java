package shapes.specificcommand;

import command.Command;
import command.NoUndoAvailableException;
import shapes.model.GraphicObject;

public class PerimeterIdCommand implements Command {
	
	private final GraphicObject go;
	
	public PerimeterIdCommand(GraphicObject go) {
		this.go=go;
	}
	
	public boolean doIt() {
		System.out.println("Il perimetro di "+go.getId()+"è: "+go.getPerimeter());
		return false;
	}

	public boolean undoIt() {
		throw new NoUndoAvailableException();
	}
}

