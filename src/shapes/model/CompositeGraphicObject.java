package shapes.model;


import java.util.List;

public interface CompositeGraphicObject extends GraphicObject {
	void addObject(List<GraphicObject> list);
	void removeChild(GraphicObject object);
}
