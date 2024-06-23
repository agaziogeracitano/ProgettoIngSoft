package interpreter;

import command.CommandHandler;
import shapes.controller.ObjectManager;
import shapes.specificcommand.ZoomCommand;

public class Scale implements Comando {
	private final ObjectManager objectManager;
	private String objId;
	private double f;
	
	public Scale(ObjectManager objectManager, String objId, double f ) {
		this.objectManager = objectManager;
		this.objId = objId;
		this.f=f;
	}
	
	@Override
	public void interpreta() {
		CommandHandler cmdH = objectManager.getCmdH();
		cmdH.handle(new ZoomCommand(objectManager.getObjectByID(objId), f));
	}

	public String getObjId() {
		return objId;
	}

	public double getF() {
		return f;
	}
}
