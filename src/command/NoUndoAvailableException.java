package command;

public class NoUndoAvailableException extends RuntimeException {

    public NoUndoAvailableException() {
        super("Non è possibile effettuare l'undo.");
    }
}
