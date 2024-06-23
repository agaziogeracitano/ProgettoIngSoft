package shapes.specificcommand;

import command.Command;
import command.NoUndoAvailableException;
import shapes.model.GraphicObject;

public class LsIdCommand implements Command {

	private final GraphicObject go;
	
	public LsIdCommand(GraphicObject go) {
		this.go = go;
	}
	
	public boolean doIt() {
		System.out.println(go.getInfo());
		return false;
	}

	public boolean undoIt() {
		throw new NoUndoAvailableException();
	}
}
