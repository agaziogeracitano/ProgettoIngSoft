package interpreter;

import java.awt.geom.Point2D;

import command.CommandHandler;
import shapes.controller.ObjectManager;
import shapes.specificcommand.MoveCommand;
;

public class MV implements Comando {
	private final ObjectManager objectManager;
	private String objId;
	private Point2D posizione;
	
	public MV(ObjectManager objectManager, String objId, Point2D posizione) {
		this.objId = objId;
		this.posizione = posizione;
		this.objectManager = objectManager;
	}
	
	@Override
	public void interpreta() {
		CommandHandler cmdH = objectManager.getCmdH();
		cmdH.handle(new MoveCommand(objectManager.getObjectByID(objId), posizione));
	}

	public String getObjId() {
		return objId;
	}

	public Point2D getPosizione() {
		return posizione;
	}
}
