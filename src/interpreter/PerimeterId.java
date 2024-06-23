package interpreter;

import shapes.controller.ObjectManager;
import shapes.specificcommand.PerimeterIdCommand;
import command.CommandHandler;

public class PerimeterId implements Comando{
	private ObjectManager objectManager;
	private String objId;
	
	public PerimeterId(ObjectManager objectManager, String objId) {
		this.objectManager = objectManager;
		this.objId=objId;
	}
	
	@Override
	public void interpreta() {
		CommandHandler cmdH = objectManager.getCmdH();
		cmdH.handle(new PerimeterIdCommand(objectManager.getObjectByID(objId)));
	}

}
