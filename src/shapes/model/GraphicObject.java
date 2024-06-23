package shapes.model;

import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;



public interface GraphicObject {
//definisce il comportamento di un graphic Object
	void addGraphicObjectListener(GraphicObjectListener l); //aggiunge un ascoltatore per gli eventi dell'oggetto grafico

	void removeGraphicObjectListener(GraphicObjectListener l); //rimuove un ascoltatore

	void moveTo(Point2D p); //sposta l'oggetto grafico in una nuova posizione

	default void moveTo(double x, double y){
		moveTo(new Point2D.Double(x,y));
	}

	Point2D getPosition(); //restituisce la posizione dell'oggetto grafico

	Dimension2D getDimension();

	void scale(double factor);

	boolean contains(Point2D p); //verifica se un punto è contenuto nell'oggetto grafico
	
	String getInfo();
	
	String getId();
	
	void setId(String id);
	
	double getPerimeter();
	
	double getArea();

	void notifyParentRemove(); //notifica il genitore dell'oggetto grafico
	
	void setParent(CompositeGraphicObject go);

	String getType(); //restituisce il tipo dell'oggetto grafico
	
}
