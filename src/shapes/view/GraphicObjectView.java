package shapes.view;

import java.awt.Graphics2D;

import shapes.model.GraphicObject;
//  flyweight, il GraphicObject è lo stato estrinseco
public interface GraphicObjectView {
	void drawGraphicObject(GraphicObject go, Graphics2D g);
}
