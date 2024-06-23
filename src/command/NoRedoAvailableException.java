package command;

public class NoRedoAvailableException extends RuntimeException {

    public NoRedoAvailableException() {
        super("Non è possibile effettuare la redo.");
    }
}