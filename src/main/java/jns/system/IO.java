package jns.system;

import java.io.Serializable;
import java.util.Objects;

/**
 * Basic structure defining a parameter, input or output
 * It is defined by:
 *      * An unique id used to identify the input/output/property
 *          note: the same id may be used for an input and an output. It is only ment to be used to distinguish inputs
 *              outputs or properties but not between them
 *      * A name. If it is unique, il can be used the same way the id is
 *      * A type defining the data type contained or transmitted through the io
 */
public class IO implements Serializable {
    private String name;
    private Enum type;
    private int id;

    /**
     * Public constructor created from a name and a type. An id will automatically be assigned later and can be retrieved
     * through the {@code getID()} function
     * @param name the io's name. If unique, can be used later to identify the IO
     * @param type the type transmitted through the io
     */
    public IO(String name, Enum type){
        this(name, type, -1);
    }
    IO(IO model, int id){
        this(model.name, model.type, id);
    }
    IO(String name, Enum type, int id){
        this.name = name;
        this.type = type;
        this.id = id;
    }

    void setID(int id){
        this.id = id;
    }

    /**
     * Retrieve the id
     * @return the io's id
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieve the io's name
     * @return the io's name
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieve the io's type
     * @return the identifier of the data type transmitted through the io
     */
    public Enum getType() {
        return type;
    }

    /**
     * Compare two io's
     * Two io's are the same if they have the same id
     * @param o the object to compare to the current instance
     * @return {@code true} if the twi are the same, {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IO io = (IO) o;
        return id == io.id;
    }

    /**
     * Return a single hashcode from the io's name, id and type
     * @return an integer containing the io's hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, type, id);
    }
}
