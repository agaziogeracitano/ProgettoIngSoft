package shapes.model;

import java.awt.Dimension;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;




public class CircleObject extends AbstractGraphicObject {

	private Point2D position;

	private double radius;
	
	private String id; //id univoco del cerchio

	public CircleObject(Point2D pos, double r) {
		if (r <= 0)
			throw new IllegalArgumentException();
		position = new Point2D.Double(pos.getX(), pos.getY());
		radius = r;
	}

	public void setId(String id) { this.id=id; }
	
	public String getId() { return id; }	
	
	public double getArea() {
		return Math.PI*radius*radius;
	}
	
	public double getPerimeter() {
		return 2*Math.PI*radius;
	}

	@Override
	public void moveTo(Point2D p) {
		position.setLocation(p);
		notifyListeners(new GraphicEvent(this)); //notifica gli ascoltatori di un cambiamento
	}

	@Override
	public Point2D getPosition() {
		return new Point2D.Double(position.getX(), position.getY());
	}

	@Override
	public void scale(double factor) {
		if (factor <= 0)
			throw new IllegalArgumentException();
		radius *= factor;
		notifyListeners(new GraphicEvent(this));
	}

	@Override
	public Dimension2D getDimension() {
		Dimension d = new Dimension();
		d.setSize(2 * radius, 2 * radius);
		return d;
	}

	@Override
	public boolean contains(Point2D p) {
		return (position.distance(p) <= radius);

	}

	@Override
	public CircleObject clone() {
		CircleObject cloned = (CircleObject) super.clone();
		cloned.position = (Point2D) position.clone();
		return cloned;
	}

	@Override
	public String getType() {

		return "Circle";
	}

	public double getRadius() {
		return radius;
	}
	
	
	
}
