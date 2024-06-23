package shapes.specificcommand;

import java.util.Set;

import command.Command;
import command.NoUndoAvailableException;
import shapes.controller.ObjectManager;

public class LsAllCommand implements Command {
	private final ObjectManager objectManager;

	public LsAllCommand(ObjectManager objectManager) {
		this.objectManager = objectManager;
	}

	
	public boolean doIt() {
		Set<String> allId = objectManager.getAll();
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
