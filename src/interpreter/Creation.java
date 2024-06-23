package interpreter;

import command.CommandHandler;
import shapes.controller.ObjectManager;
import shapes.model.GraphicObject;
import shapes.specificcommand.NewObjectCommand;

import java.awt.geom.Point2D;

public class Creation implements Comando{
    private GraphicObject go;
    private Point2D posizione;
    private ObjectManager objectManager;

    public Creation(ObjectManager objectManager, GraphicObject go, Point2D posizione){
        this.go=go;
        this.posizione=posizione;
        this.objectManager = objectManager;
    }
    @Override
    public void interpreta() {
        CommandHandler cmdH= objectManager.getCmdH();
        cmdH.handle(new NewObjectCommand(objectManager,go, posizione));
    }

    public GraphicObject getGraphicObject() {
        return go;
    }

    public Point2D getPosizione() {
        return posizione;
    }

}
