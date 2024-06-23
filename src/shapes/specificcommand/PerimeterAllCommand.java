package shapes.specificcommand;

import command.Command;
import command.NoUndoAvailableException;
import shapes.controller.ObjectManager;
import shapes.model.GraphicObject;
import shapes.model.GroupObject;

public class PerimeterAllCommand implements Command {
	private ObjectManager objectManager;

	public PerimeterAllCommand(ObjectManager objectManager) {
		this.objectManager = objectManager;
	}

	public boolean doIt() {
		double ris = 0;
		for( String s : objectManager.getAll()) {
			GraphicObject obj = objectManager.getObjectByID(s);
			if(!(obj instanceof GroupObject))
				ris += obj.getPerimeter();
		}
		System.out.println("La somma di tutti i perimetri è: "+ris);
		return false;
	}

	public boolean undoIt() {
		throw new NoUndoAvailableException();
	}

}
