package jns.system;

public class DataException extends NodeRuntimeException {
    public DataException() {
        super();
    }

    public DataException(String expectedValue) {
        super("Expected value type \'" + expectedValue + "\'");
    }
}
