package command;

import java.util.LinkedList;

public class HistoryCommandHandler implements CommandHandler {

	private int maxHistoryLength = 100;

	private LinkedList<Command> history = new LinkedList<>();

	private LinkedList<Command> redoList = new LinkedList<>();

	public HistoryCommandHandler() {
		this(100);
	}

	public HistoryCommandHandler(int maxHistoryLength) {
		super();
		if (maxHistoryLength < 0)
			throw new IllegalArgumentException();
		this.maxHistoryLength = maxHistoryLength;
	}

	public void handle(Command cmd) {
		if (cmd.doIt()) { // esegue il comando e restituisce true se può essere annullato così può essere aggiunto alla storia
			addToHistory(cmd);
		} else {
			history.clear(); // svuotiamo la storia
		}
		if (redoList.size() > 0) { // se ci sono comandi nella lista di redo li svuotiamo
			redoList.clear();
		}
	}

	public void redo() {
		if (redoList.size() == 0) { // se non ci sono comandi nella lista di redo, lancio un'eccezione
			throw new NoRedoAvailableException();
		}
		Command redoCmd = redoList.removeFirst();
		redoCmd.doIt();
		history.addFirst(redoCmd);
	}

	public void undo() {
		if (history.size() == 0) { // se non ci sono comandi nella lista della storia, lancio un'eccezione
			throw new NoUndoAvailableException();
		}
		Command undoCmd = history.removeFirst();
		undoCmd.undoIt();
		redoList.addFirst(undoCmd);
	}

	private void addToHistory(Command cmd) {
		history.addFirst(cmd);
		if (history.size() > maxHistoryLength) {
			history.removeLast();
		}

	}

}
