package jns.system;

/**
 * Handles any Data related exception happening at runtime
 */
public class DataException extends NodeRuntimeException {
    /**
     * Default NodeRuntimeException
     */
    public DataException() {
        super();
    }

    /**
     * Display the error with a custom error message
     * @param message the error message to pass through to NodeRuntimeException
     */
    public DataException(String message) {
        super(message);
    }
}
