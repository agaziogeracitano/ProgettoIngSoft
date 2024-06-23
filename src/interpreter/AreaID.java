package interpreter;

import command.CommandHandler;
import shapes.controller.ObjectManager;
import shapes.specificcommand.AreaIdCommand;

public class AreaID implements Comando {

	private String objId;
	private ObjectManager objectManager;

	public AreaID(ObjectManager objectManager, String objId){
		this.objectManager = objectManager;
		this.objId=objId;
	}

	
	@Override
	public void interpreta() {
		CommandHandler cmdH = objectManager.getCmdH();
		cmdH.handle(new AreaIdCommand(objectManager.getObjectByID(objId)));
	}

}
