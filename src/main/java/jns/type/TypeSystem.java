package jns.type;

public class TypeSystem {
    public enum Type {
        VOID,
        ANY,
        BOOLEAN,
        NUMBER;
    }

    public static final jns.system.Type[] typeSystem = new jns.system.Type[]{
            new jns.system.Type(Type.VOID, VoidData.class),
            new jns.system.Type(Type.ANY, ObjectData.class),
            new jns.system.Type(Type.BOOLEAN, BooleanData.class),
            new jns.system.Type(Type.NUMBER, NumberData.class)
    };
}
