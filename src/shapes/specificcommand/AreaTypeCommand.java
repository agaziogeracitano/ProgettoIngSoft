package shapes.specificcommand;

import java.util.Set;

import command.Command;
import command.NoUndoAvailableException;
import shapes.controller.ObjectManager;

public class AreaTypeCommand implements Command {
	private final String type;
	private final ObjectManager objectManager;

	public AreaTypeCommand(ObjectManager objectManager, String t) {
		this.objectManager = objectManager;
		this.type=t;
	}
	
	public boolean doIt() {
		Set<String> allId = objectManager.getAllByType(type);
		double res = 0;
		for(String s : allId) {
			res += objectManager.getObjectByID(s).getArea();
		}
		System.out.println("La somma delle aree dei "+type+" è: "+res);
		return false;
	}

	public boolean undoIt() {
		throw new NoUndoAvailableException();
	}

}

