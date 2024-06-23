package interpreter;

import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

import command.CommandHandler;
import shapes.controller.ObjectManager;
import shapes.model.GraphicObject;
import shapes.model.GroupObject;
import shapes.specificcommand.NewObjectCommand;

public class Gruop implements Comando {
	private ObjectManager objectManager;
	private List<String> idList; //passo una lista e crea un gruppo a partire dagli elementi della lista
	
	public Gruop(ObjectManager objectManager, List<String> idList) {
		this.objectManager = objectManager;
		this.idList = idList;
	}
	
	@Override
	public void interpreta() {
		LinkedList<GraphicObject> list = new LinkedList<>();
		for(String id : idList) {
			GraphicObject obj = objectManager.getObjectByID(id); //recupero l'oggetto grafico asseganto a questo id
			if( list.contains(obj) ) throw new IllegalArgumentException(); //controllo se è gia presente nella lista
			if( obj instanceof GroupObject ) { //se un gruppo vedo se è già all'interno
				if(Ispresente((GroupObject)obj, idList)){
					throw new IllegalArgumentException();
				}
			}
			list.add(obj);
		}
		GroupObject grp = (GroupObject) objectManager.getPrototype("Group");
		grp.addObject(list);
		Point2D pos = grp.getPosition();
		CommandHandler cmdH = objectManager.getCmdH();
		cmdH.handle(new NewObjectCommand(objectManager,grp, pos));
	}
	
	private static boolean Ispresente(GroupObject obj, List<String> idList ) {
	    for (GraphicObject child : obj.getChilds()) {
	        if (idList.contains(child.getId())) {
	            return true;
	        }
	        if (child instanceof GroupObject) {
	            Ispresente((GroupObject)child, idList);
	        }
	    }
		return false;
	}

	public List<String> getIdList() {
		return idList;
	}
}
