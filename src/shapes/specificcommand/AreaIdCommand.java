package shapes.specificcommand;

import command.Command;
import command.NoUndoAvailableException;
import shapes.model.GraphicObject;

public class AreaIdCommand implements Command {
	private final GraphicObject go;
	
	public AreaIdCommand(GraphicObject go) {
		this.go=go;
	}
	
	public boolean doIt() {
		System.out.println("L'area di "+go.getId()+"è : "+go.getArea());
		return false;
	}

	public boolean undoIt() {
		throw new NoUndoAvailableException();
	}
}

