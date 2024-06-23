import org.junit.jupiter.api.*;
import shapes.controller.ObjectManager;
import interpreter.*;
import shapes.model.CircleObject;


import java.io.StringReader;
import java.awt.geom.Point2D;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    private ObjectManager objectManager;

    @BeforeEach
    public void setUp() {
        objectManager = new ObjectManager();
    }

    @Test
    public void testNewCircleCommand() throws Exception {
        String command = "new circle (5.0) (100.0,100.0)";
        Parser parser = new Parser(objectManager, new StringReader(command));
        Comando comando = parser.getComando();

        assertNotNull(comando);
        assertTrue(comando instanceof Creation);

        Creation creation = (Creation) comando;
        assertEquals("Circle", creation.getGraphicObject().getType());
        assertEquals(new Point2D.Double(100.0, 100.0), creation.getPosizione());
        assertEquals(5.0, ((CircleObject) creation.getGraphicObject()).getRadius(), 0.001);
    }

    @Test
    public void testNewRectangleCommand() throws Exception {
        String command = "new rectangle (4.0, 5.0) (200.0,200.0)";
        Parser parser = new Parser(objectManager, new StringReader(command));
        Comando comando = parser.getComando();

        assertNotNull(comando);
        assertTrue(comando instanceof Creation);

        Creation creation = (Creation) comando;
        assertEquals("Rectangle", creation.getGraphicObject().getType());
        assertEquals(new Point2D.Double(200.0, 200.0), creation.getPosizione());
    }



    @Test
    public void testDeleteCommand() throws Exception {
        String command = "del o1";
        Parser parser = new Parser(objectManager, new StringReader(command));
        Comando comando = parser.getComando();

        assertNotNull(comando);
        assertTrue(comando instanceof Delete);

        Delete delete = (Delete) comando;
        assertEquals("o1", delete.getObjId());
    }

    @Test
    public void testMoveCommand() throws Exception {
        String command = "mv o1 (150.0,150.0)";
        Parser parser = new Parser(objectManager, new StringReader(command));
        Comando comando = parser.getComando();

        assertNotNull(comando);
        assertTrue(comando instanceof MV);

        MV move = (MV) comando;
        assertEquals("o1", move.getObjId());
        assertEquals(new Point2D.Double(150.0, 150.0), move.getPosizione());
    }

    @Test
    public void testScaleCommand() throws Exception {
        String command = "scale o1 1.5";
        Parser parser = new Parser(objectManager, new StringReader(command));
        Comando comando = parser.getComando();

        assertNotNull(comando);
        assertTrue(comando instanceof Scale);

        Scale scale = (Scale) comando;
        assertEquals("o1", scale.getObjId());
        assertEquals(1.5, scale.getF(), 0.001);
    }

    @Test
    public void testGroupCommand() throws Exception {
        String command = "grp o1,o2,o3";
        Parser parser = new Parser(objectManager, new StringReader(command));
        Comando comando = parser.getComando();

        assertNotNull(comando);
        assertTrue(comando instanceof Gruop);

        Gruop group = (Gruop) comando;
        assertEquals(3, group.getIdList().size());
        assertTrue(group.getIdList().contains("o1"));
        assertTrue(group.getIdList().contains("o2"));
        assertTrue(group.getIdList().contains("o3"));
    }

}
