import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.*;
import command.NoRedoAvailableException;
import command.NoUndoAvailableException;
import interpreter.Parser;
import shapes.controller.ObjectManager;
import shapes.model.CircleObject;
import shapes.model.ImageObject;
import shapes.model.RectangleObject;
import shapes.view.*;

public class Main {

	public static void main(String[] args) {

		JFrame f = new JFrame();
		f.setTitle("MiniCAD");
		ObjectManager objectManager = new ObjectManager();
		GraphicObjectPanel gpanel = objectManager.getPanel();
		gpanel.setPreferredSize(new Dimension(600, 500));


		GraphicObjectViewFactory.FACTORY.installView(RectangleObject.class, new RectangleObjectView());
		GraphicObjectViewFactory.FACTORY.installView(CircleObject.class, new CircleObjectView());
		GraphicObjectViewFactory.FACTORY.installView(ImageObject.class, new ImageObjectView());

		f.add(new JScrollPane(gpanel), BorderLayout.CENTER);

		// Pannello dei bottoni
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		JButton buttonUndo = new JButton("Undo");
		JButton buttonRedo = new JButton("Redo");
		JButton buttonSave = new JButton("Salva"); // Aggiunto pulsante Salva

		buttonUndo.addActionListener(evt -> {
			try {
				objectManager.getCmdH().undo();
			} catch (NoUndoAvailableException undo) {
				System.err.println("Non è possibile eseguire l'undo");
			}
		});

		buttonRedo.addActionListener(evt -> {
			try {
				objectManager.getCmdH().redo();
			} catch (NoRedoAvailableException redo) {
				System.err.println("Non è possibile eseguire la redo");
			}
		});

		buttonSave.addActionListener(evt -> {
			JFileChooser fileChooser = new JFileChooser();
			int result = fileChooser.showSaveDialog(f);
			if (result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				String fileName = selectedFile.getAbsolutePath();
				try {
					hideButtons(buttonPanel);
					saveScreen(f.getContentPane(), fileName);
					System.out.println("Schermata salvata correttamente!");
					showButtons(buttonPanel);
				} catch (IOException e) {
					System.err.println("Errore durante il salvataggio della schermata: " + e.getMessage());
				}
			}
		});

		buttonPanel.add(buttonUndo);
		buttonPanel.add(buttonRedo);
		buttonPanel.add(buttonSave); // Aggiunto il pulsante Salva al pannello dei bottoni

		f.add(buttonPanel, BorderLayout.NORTH);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);

		// Input dalla console
		Scanner scanner = new Scanner(System.in);
		while (true) {
			String comando = scanner.nextLine();
			if ("exit".equalsIgnoreCase(comando.trim())) {
				System.exit(1);
			}
			try {
				if (!comando.trim().isEmpty()) {
					StringReader sr = new StringReader(comando);
					Parser p = new Parser(objectManager,sr);
					p.getComando().interpreta();
				}
			} catch (RuntimeException ex) {
				System.err.println("Syntax error: Comando non corretto, riprova :) ");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private static void hideButtons(JPanel buttonPanel) {
		buttonPanel.setVisible(false);
	}

	private static void showButtons(JPanel buttonPanel) {
		buttonPanel.setVisible(true);
	}

	private static void saveScreen(Container container, String fileName) throws IOException {
		BufferedImage image = new BufferedImage(container.getWidth(), container.getHeight(), BufferedImage.TYPE_INT_RGB);
		container.paint(image.getGraphics());
		ImageIO.write(image, "png", new File(fileName));
	}
}




