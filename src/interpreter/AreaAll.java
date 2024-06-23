package interpreter;

import command.CommandHandler;
import shapes.controller.ObjectManager;
import shapes.specificcommand.AreaAllCommand;

public class AreaAll implements Comando {
	private ObjectManager objectManager;

	public AreaAll(ObjectManager objectManager){
		this.objectManager = objectManager;
	}

	@Override
	public void interpreta() {
		CommandHandler cmdH = objectManager.getCmdH();
		cmdH.handle(new AreaAllCommand(objectManager));
	}

}
