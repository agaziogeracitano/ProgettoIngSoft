package interpreter;

import command.CommandHandler;
import shapes.controller.ObjectManager;
import shapes.specificcommand.LsTypeCommand;

public class LsType implements Comando {
	private final ObjectManager objectManager;
	private String tipo;
	
	public LsType(ObjectManager objectManager, Simboli simbolo) {
		if(simbolo==Simboli.CIRCLE) {
			tipo = "Circle";
		}
		else if(simbolo==Simboli.RECTANGLE) {
			tipo = "Rectangle";
		}
		else {
			tipo = "Image";
		}
		this.objectManager = objectManager;
	}
	
	@Override
	public void interpreta() {
		CommandHandler cmdH = objectManager.getCmdH();
		cmdH.handle(new LsTypeCommand(objectManager,tipo));
	}

}
