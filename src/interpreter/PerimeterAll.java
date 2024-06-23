package interpreter;

import command.CommandHandler;
import shapes.controller.ObjectManager;
import shapes.specificcommand.PerimeterAllCommand;

public class PerimeterAll implements Comando{
	private ObjectManager objectManager;

	public PerimeterAll(ObjectManager objectManager) {
		this.objectManager = objectManager;
	}

	@Override
	public void interpreta() {
		CommandHandler cmdH = objectManager.getCmdH();
		cmdH.handle(new PerimeterAllCommand(objectManager));
	}

}
