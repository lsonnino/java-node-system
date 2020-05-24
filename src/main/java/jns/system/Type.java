package jns.system;

/**
 * Defines an input/output/property type. It is based on two values:
 *      * the identifier: an enumeration used as a shortcut when creating nodes to define IO types
 *      * the actual data class used internally to handle the data type
 * Note that every node may not be compatible with every Type Systems
 */
public class Type {
    private Enum identifier;
    private Class<? extends Data> data;

    /**
     * Create a Type
     * @param identifier an enumeration used to identify a data type
     * @param data the actual class handling the data type
     */
    public Type(Enum identifier, Class<? extends Data> data){
        this.identifier = identifier;
        this.data = data;
    }

    /**
     * Get whether or not an enumeration correspond to the same type as the current instance
     * @param identifier the identifier to check
     * @return true if the identifier correspond to the same data type as the current instance, false otherwise
     */
    public boolean is(Enum identifier){
        return identifier.equals(this.identifier);
    }

    /**
     * Get the data type class: should only be used internally
     * @return a generic class object corresponding to an object extending the generic object Data
     */
    public Class<? extends Data> get(){
        return data;
    }
}
