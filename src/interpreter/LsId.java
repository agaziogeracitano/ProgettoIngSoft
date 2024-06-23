package interpreter;

import command.CommandHandler;
import shapes.controller.ObjectManager;
import shapes.specificcommand.LsIdCommand;

public class LsId implements Comando {
	private ObjectManager objectManager;
	private String objId;
	
	public LsId(ObjectManager objectManager, String objId) {
		this.objId=objId;
		this.objectManager=objectManager;
	}
	
	@Override
	public void interpreta() {
		CommandHandler cmdH = objectManager.getCmdH();
		cmdH.handle(new LsIdCommand(objectManager.getObjectByID(objId)));

	}

}
