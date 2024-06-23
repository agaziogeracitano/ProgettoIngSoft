package shapes.specificcommand;

import java.util.Set;

import command.Command;
import command.NoUndoAvailableException;
import shapes.controller.ObjectManager;

public class PerimeterTypeCommand implements Command {
	private final ObjectManager objectManager;
	private final String type;
	
	public PerimeterTypeCommand(ObjectManager objectManager, String t) {
		this.objectManager = objectManager;
		this.type=t;
	}
	
	public boolean doIt() {
		Set<String> allId = objectManager.getAllByType(type);
		double ris = 0;
		for(String s : allId) {
			ris += objectManager.getObjectByID(s).getPerimeter();
		}
		System.out.println("La somma dei perimetri dei tipi "+type+" è:"+ris);
		return false;
	}

	public boolean undoIt() {
		throw new NoUndoAvailableException();
	}

}
