package shapes.specificcommand;

import java.awt.geom.Point2D;

import command.Command;
import shapes.controller.ObjectManager;
import shapes.model.GraphicObject;
import shapes.model.GroupObject;

public class NewObjectCommand implements Command {
	private ObjectManager objectManager;
	private final GraphicObject go;
	private final Point2D posizione;

	public NewObjectCommand(ObjectManager objectManager, GraphicObject go, Point2D posizione) {
		this.objectManager = objectManager;
		this.go = go;
		this.posizione = posizione;
		
	}

	@Override
	public boolean doIt() {
		if(!(go instanceof GroupObject)) {
			go.moveTo(posizione);
			objectManager.getPanel().add(go);
		}
		String objId = objectManager.addObject(go);
		go.setId(objId);
		System.out.println("Creato "+objId);
		return true;
	}

	@Override
	public boolean undoIt() {
		objectManager.getPanel().remove(go);
		objectManager.removeObjectById(go.getId());
		return true;
	}

}
