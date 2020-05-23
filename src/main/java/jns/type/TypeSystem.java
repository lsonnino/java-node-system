package jns.type;

/**
 * The default NJS's Type System
 */
public class TypeSystem {
    // Defining the possible data types identifiers
    public enum Type {
        VOID,
        ANY,
        BOOLEAN,
        NUMBER;
    }

    // Connecting the identifiers to the actual Data type classes
    public static final jns.system.Type[] typeSystem = new jns.system.Type[]{
            new jns.system.Type(Type.VOID, VoidData.class),
            new jns.system.Type(Type.ANY, ObjectData.class),
            new jns.system.Type(Type.BOOLEAN, BooleanData.class),
            new jns.system.Type(Type.NUMBER, NumberData.class)
    };
}
