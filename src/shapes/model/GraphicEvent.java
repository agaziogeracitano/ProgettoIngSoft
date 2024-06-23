package shapes.model;


public class GraphicEvent {
//rappresenta un evento relativo agli oggetti grafici
	private final GraphicObject source; //sorgente dell'evento

	public GraphicEvent(GraphicObject src){
		source=src;
	}

	public GraphicObject getSource() {
		return source;
	}
}
