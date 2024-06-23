package interpreter;

import command.CommandHandler;
import shapes.controller.ObjectManager;
import shapes.specificcommand.DeleteCommand;

public class Delete implements Comando {

	private String objId;
	private ObjectManager objectManager;
	
	public Delete(ObjectManager objectManager, String objId) {
		this.objId = objId;
		this.objectManager = objectManager;
	}
	
	@Override
	public void interpreta() {
		CommandHandler cmdH = objectManager.getCmdH();
		cmdH.handle(new DeleteCommand(objectManager, objectManager.getObjectByID(objId)));
	}

	public String getObjId(){
		return objId;
	}

}
