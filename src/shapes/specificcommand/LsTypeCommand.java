package shapes.specificcommand;

import java.util.Set;

import command.Command;
import command.NoUndoAvailableException;
import shapes.controller.ObjectManager;

public class LsTypeCommand implements Command {
	private final ObjectManager objectManager;
	private final String type;
	
	public LsTypeCommand(ObjectManager objectManager, String type) {
		this.objectManager = objectManager;
		this.type = type;
	}
	
	public boolean doIt() {
		Set<String> allId = objectManager.getAllByType(type);
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(String s : allId) {
			sb.append(s+",");
		}
		if(sb.length()>1)
			sb.deleteCharAt(sb.length()-1);
		sb.append("]");
		System.out.println(sb);
		return false;
	}

	public boolean undoIt() {
		throw new NoUndoAvailableException();
	}
	
}
