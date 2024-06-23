package interpreter;

import java.awt.geom.Point2D;

import command.CommandHandler;
import shapes.controller.ObjectManager;
import shapes.specificcommand.MoveOffCommand;

public class MVOff implements Comando {
	private final ObjectManager objectManager;
	private String objId;
	private Point2D posizione;
	
	public MVOff(ObjectManager objectManager, String objId, Point2D posizione) {
		this.objectManager = objectManager;
		this.objId = objId;
		this.posizione = posizione;
	}
	
	@Override
	public void interpreta() {
		CommandHandler cmdH = objectManager.getCmdH();
		cmdH.handle(new MoveOffCommand(objectManager.getObjectByID(objId), posizione));
	}
}
