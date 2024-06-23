package shapes.model;


import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;
/*
Questa classe astratta fornisce un'implementazione di base per gli oggetti grafici e implementa l'interfaccia GraphicObject.

Gestisce gli ascoltatori degli eventi dell'oggetto grafico.
Implementa i metodi per la gestione degli ascoltatori, la clonazione dell'oggetto grafico e la generazione delle informazioni sull'oggetto.
Fornisce implementazioni di base per moveTo, scale, contains, getType, getInfo, getId, getPerimeter, getArea, notifyParent.
 */


public abstract class AbstractGraphicObject implements GraphicObject, Cloneable {

	private List<GraphicObjectListener> listeners = new LinkedList<>(); //lista degli ascoltatori

	private List<CompositeGraphicObject> parent = new LinkedList<>(); //lista di oggetti composite che contengono questo oggetto
	
	public void setParent(CompositeGraphicObject cgo ) {
		this.parent.add(cgo);
	}
	
	public void notifyParentRemove() {
		for(CompositeGraphicObject cgo : parent) {
			cgo.removeChild(this);
		}
	}

	@Override
	public void addGraphicObjectListener(GraphicObjectListener l) { //aggiungo un ascoltatore
		if (listeners.contains(l))
			return;
		listeners.add(l);

	}

	@Override
	public void removeGraphicObjectListener(GraphicObjectListener l) {
		listeners.remove(l);

	}

	protected void notifyListeners(GraphicEvent e) {
		for (GraphicObjectListener gol : listeners)
			gol.graphicChanged(e);
	}


	@Override
	public GraphicObject clone() {
		try {
			AbstractGraphicObject go = (AbstractGraphicObject) super.clone();
			go.listeners = new LinkedList<>();
			return go;
		} catch (CloneNotSupportedException e) {
			throw new Error(e);
		}
	}
	

	public String getInfo() {
		Dimension2D dim =this.getDimension();
		Point2D p = this.getPosition();
		String ret=this.getType()+" dim=("+dim.getWidth()+","+dim.getHeight()+")"+ " pos=("+p.getX()+","+p.getY()+")";
		return ret;
	}

}
