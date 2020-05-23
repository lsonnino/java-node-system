package jns.system;

/**
 * Defines any exception happening at runtime
 */
public class NodeRuntimeException extends RuntimeException {
    /**
     * Default Java's RuntimeException
     */
    public NodeRuntimeException() {
        super();
    }

    /**
     * @param message the error message to be displayed
     */
    public NodeRuntimeException(String message) {
        super(message);
    }
}
