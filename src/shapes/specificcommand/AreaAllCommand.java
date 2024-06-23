package shapes.specificcommand;

import command.Command;
import command.NoUndoAvailableException;
import shapes.controller.ObjectManager;
import shapes.model.GraphicObject;
import shapes.model.GroupObject;

public class AreaAllCommand implements Command {
	private final ObjectManager objectManager;

	public AreaAllCommand(ObjectManager objectManager) {
		this.objectManager = objectManager;
	}

	public boolean doIt() {
		double res = 0;
		for(String s : objectManager.getAll()) {
			GraphicObject obj = objectManager.getObjectByID(s);
			if(!(obj instanceof GroupObject))
				res += obj.getArea();
		}
		System.out.println("La somma di tutte le aree è: "+res);
		return false;
	}
	
	public boolean undoIt() {
		throw new NoUndoAvailableException();
	}

}

