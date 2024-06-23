package interpreter;

import command.CommandHandler;
import shapes.controller.ObjectManager;
import shapes.specificcommand.DeleteCommand;

public class Degroup implements Comando {

	private String objId;
	private ObjectManager objectManager;
	
	public Degroup(ObjectManager objectManager, String objId) {
		this.objId=objId;
		this.objectManager = objectManager;
	}
	
	@Override
	public void interpreta() {
		CommandHandler cmdH = objectManager.getCmdH();
		cmdH.handle(new DeleteCommand(objectManager, objectManager.getObjectByID(objId)));
	}

}
