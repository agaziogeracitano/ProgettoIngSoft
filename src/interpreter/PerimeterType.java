package interpreter;

import command.CommandHandler;
import shapes.controller.ObjectManager;
import shapes.specificcommand.PerimeterTypeCommand;

public class PerimeterType implements Comando{
	private final ObjectManager objectManager;
	private String tipo;
	
	public PerimeterType(ObjectManager objectManager, Simboli simbolo) {
		this.objectManager = objectManager;
		if(simbolo==Simboli.CIRCLE) {
			tipo = "Circle";
		}
		else if(simbolo==Simboli.RECTANGLE) {
			tipo = "Rectangle";
		}
		else {
			tipo = "Image";
		}
	}
	
	@Override
	public void interpreta() {
		CommandHandler cmdH = objectManager.getCmdH();
		cmdH.handle(new PerimeterTypeCommand(objectManager,tipo));
	}

}
