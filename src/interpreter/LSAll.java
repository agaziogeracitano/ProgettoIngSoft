package interpreter;

import command.CommandHandler;
import shapes.controller.ObjectManager;
import shapes.specificcommand.LsAllCommand;

public class LSAll implements Comando {
	private final ObjectManager objectManager;

	public LSAll(ObjectManager objectManager) {
		this.objectManager = objectManager;
	}

	@Override
	public void interpreta() {
		CommandHandler cmdH = objectManager.getCmdH();
		cmdH.handle(new LsAllCommand(objectManager));
	}

}
