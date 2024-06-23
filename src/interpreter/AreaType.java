package interpreter;

import command.CommandHandler;
import shapes.controller.ObjectManager;
import shapes.specificcommand.AreaTypeCommand;

public class AreaType implements Comando {

	private String tipo;
	private ObjectManager objectManager;

	
	public AreaType(ObjectManager objectManager, Simboli simbolo) {
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
		cmdH.handle(new AreaTypeCommand(objectManager,tipo));
	}

}
