package shapes.model;

import java.awt.Dimension;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;



public class RectangleObject extends AbstractGraphicObject {

	private Point2D position;

	private Dimension2D dim;
	
	private String id;

	public RectangleObject(Point2D pos, double w, double h) {
		if (w <= 0 || h <= 0)
			throw new IllegalArgumentException();
		dim = new Dimension();
		dim.setSize(w, h);
		position = new Point2D.Double(pos.getX(), pos.getY());
	}
	
	public String getId() { return id; }

	@Override
	public boolean contains(Point2D p) {
		double w = dim.getWidth() / 2;
		double h = dim.getHeight() / 2;
		double dx = Math.abs(p.getX() - position.getX());
		double dy = Math.abs(p.getY() - position.getY());
		return dx <= w && dy <= h;

	}

	@Override
	public void moveTo(Point2D p) {
		position.setLocation(p);
		notifyListeners(new GraphicEvent(this));
	}
	public double getArea() {
		return dim.getHeight()*dim.getWidth();
	}
	
	public double getPerimeter() {
		return dim.getHeight()*2+dim.getWidth()*2;
	}

	@Override
	public Point2D getPosition() {

		return new Point2D.Double(position.getX(), position.getY());
	}
	
	public void setId(String id) { this.id=id; }

	@Override
	public void scale(double factor) {
		if (factor <= 0)
			throw new IllegalArgumentException();
		dim.setSize(dim.getWidth() * factor, dim.getHeight() * factor);
		notifyListeners(new GraphicEvent(this));
	}
	public void scale(Point2D factor) {
		if (factor.getX() <= 0 || factor.getY() <= 0)
			throw new IllegalArgumentException();
		dim.setSize(dim.getWidth() * factor.getX(), dim.getHeight() * factor.getY());
		notifyListeners(new GraphicEvent(this));
	}

	@Override
	public Dimension2D getDimension() {
		Dimension2D d = new Dimension();
		d.setSize(dim);
		return d;
	}

	@Override
	public RectangleObject clone() {
		RectangleObject cloned = (RectangleObject) super.clone();
		cloned.position = (Point2D) position.clone();
		cloned.dim = (Dimension2D) dim.clone();
		return cloned;
	}

	@Override
	public String getType() {

		return "Rectangle";
	}
	
	
}
