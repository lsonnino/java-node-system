package jns.type;

/**
 * The default JNS's Type System
 */
public class TypeSystem {
    // Defining the possible data types identifiers
    // These identifiers will be used to reference data types
    public enum Type {
        VOID,
        ANY,
        BOOLEAN,
        NUMBER,
        ARRAY;
    }

    // Connecting the identifiers to the actual Data type classes
    public static final jns.system.Type[] typeSystem = new jns.system.Type[]{
            new jns.system.Type(Type.VOID, VoidData.class),
            new jns.system.Type(Type.ANY, ObjectData.class),
            new jns.system.Type(Type.BOOLEAN, BooleanData.class),
            new jns.system.Type(Type.NUMBER, NumberData.class),
            new jns.system.Type(Type.ARRAY, ArrayData.class)
    };
}
