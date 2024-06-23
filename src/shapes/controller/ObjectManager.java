package shapes.controller;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javax.swing.ImageIcon;
import command.HistoryCommandHandler;
import shapes.model.AbstractGraphicObject;
import shapes.model.CircleObject;
import shapes.model.GraphicObject;
import shapes.model.GroupObject;
import shapes.model.ImageObject;
import shapes.model.RectangleObject;
import shapes.view.GraphicObjectPanel;

public class ObjectManager {

    private HashMap<String, GraphicObject> object; //idObject->GraphicObject
    private HashMap<String, AbstractGraphicObject> prototypes;
    private int objectCount = 0; //contatore per l'id degli oggetti
    private GraphicObjectPanel panel;
    private HistoryCommandHandler cmdH;

    public ObjectManager() {
        prototypes = new HashMap<>();
        prototypes.put("Circle", new CircleObject(new Point2D.Double(0,0),1));
        prototypes.put("Rectangle", new RectangleObject(new Point2D.Double(0,0),1,1));
        prototypes.put("Image", new ImageObject(new ImageIcon(""), new Point2D.Double(0,0)));
        prototypes.put("Group", new GroupObject());
        object=new HashMap<>();
        panel=new GraphicObjectPanel();
        cmdH=new HistoryCommandHandler();
    }

    public HistoryCommandHandler getCmdH() {
        return cmdH;
    }

    public GraphicObjectPanel getPanel() {
        return panel;
    }

    public GraphicObject getPrototype(String object) {
        return prototypes.get(object).clone();
    }

    public Set<String> getAll() {
        return object.keySet();
    }

    public Set<String> getAllByType(String type) {
        Set<String> ret = new HashSet<>();
        for(GraphicObject o : object.values() ) {
            if(o.getType().equals(type)) {
                ret.add(o.getId());
            }
        }
        return ret;
    }

    public GraphicObject getObjectByID(String objId) {
        return object.get(objId);
    }

    public void removeObjectById(String objId) {
        object.remove(objId);
    }

    public String addObject(GraphicObject obj) {
        String idObject = "o" + (++objectCount);
        object.put(idObject, obj);
        return idObject;
    }
}

