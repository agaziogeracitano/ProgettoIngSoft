import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shapes.controller.ObjectManager;
import shapes.model.CircleObject;
import shapes.model.GraphicObject;
import shapes.model.RectangleObject;

import java.awt.geom.Point2D;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ObjectManagerTest {

    private ObjectManager objectManager;

    @BeforeEach
    void setUp() {
        objectManager = new ObjectManager();
    }

    @Test
    void testAddObject() {
        CircleObject circle = new CircleObject(new Point2D.Double(100.0, 100.0), 5.0);
        String id = objectManager.addObject(circle);
        assertEquals(circle, objectManager.getObjectByID(id));
    }

    @Test
    void testGetPrototype() {
        GraphicObject circlePrototype = objectManager.getPrototype("Circle");
        assertTrue(circlePrototype instanceof CircleObject);
        assertEquals("Circle", circlePrototype.getType());
    }

    @Test
    void testRemoveObjectById() {
        CircleObject circle = new CircleObject(new Point2D.Double(100.0, 100.0), 5.0);
        String id = objectManager.addObject(circle);
        objectManager.removeObjectById(id);
        assertNull(objectManager.getObjectByID(id));
    }

    @Test
    void testGetAll() {
        CircleObject circle = new CircleObject(new Point2D.Double(100.0, 100.0), 5.0);
        RectangleObject rectangle = new RectangleObject(new Point2D.Double(200.0, 200.0), 4.0, 5.0);
        String ob1=objectManager.addObject(circle);
        String ob2=objectManager.addObject(rectangle);
        Set<String> allObjects = objectManager.getAll();
        assertEquals(2, allObjects.size());
        assertTrue(allObjects.contains(ob1));
        assertTrue(allObjects.contains(ob2));
    }


    @Test
    void testGetPanel() {
        assertNotNull(objectManager.getPanel());
    }

    @Test
    void testGetCmdH() {
        assertNotNull(objectManager.getCmdH());
    }
}
