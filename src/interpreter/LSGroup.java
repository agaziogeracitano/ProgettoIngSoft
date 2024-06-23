package interpreter;
import command.CommandHandler;
import shapes.controller.ObjectManager;
import shapes.specificcommand.LsTypeCommand;

public class LSGroup implements Comando {
	private final ObjectManager objectManager;

	public LSGroup(ObjectManager objectManager) {
		this.objectManager = objectManager;
	}
	@Override
	public void interpreta() {
		CommandHandler cmdH = objectManager.getCmdH();
		cmdH.handle(new LsTypeCommand(objectManager,"Group"));
	}

}
