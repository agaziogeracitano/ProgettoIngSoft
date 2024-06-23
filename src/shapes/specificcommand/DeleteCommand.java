package shapes.specificcommand;

import command.Command;
import shapes.controller.ObjectManager;
import shapes.model.GraphicObject;
import shapes.model.GroupObject;

public class DeleteCommand implements Command {
	private final ObjectManager objectManager;
	private final GraphicObject go;
	
	public DeleteCommand(ObjectManager objectManager, GraphicObject go) {
		this.objectManager = objectManager;
		this.go = go;
	}
	
	public boolean doIt() {
		if(!(go instanceof GroupObject))
			objectManager.getPanel().remove(go);
		go.notifyParentRemove();
		String objId = go.getId();
		objectManager.removeObjectById(objId);
		return true;
	}
	
	public boolean undoIt() {
		objectManager.addObject(go);
		objectManager.getPanel().add(go);
		return true;
	}

}
