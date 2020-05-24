package jns.system;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Defines the data structure for a data type
 * Data types are used to pack, transmit and handle information between nodes. A data type is defined based on an object
 * and handles the conversion between data types.
 *
 * To create a data type, simply extend this class. Data types must have an empty constructor defining it's default value
 *
 * @param <T> the object the data type is based on
 *          note: the data type an object is based on must have an empty constructor defining it's default value
 */
public abstract class Data<T> implements Serializable {
    private T data;
    private Class<T> tClass;

    /**
     * Create an instance of the data type
     * @param tClass the object's class the data type is based on
     * @throws DataException if the data type does not have an empty constructor or if an error occurred when instantiating
                   the data type
     */
    public Data(Class<T> tClass){
        try {
            Class<T> c = getType();
            Constructor<T> constructor = c.getConstructor();
            this.data = constructor.newInstance();
        }
        catch(NoSuchMethodException exc){
            throw new DataException("Data types need to have an empty constructor to be instantiated to a default value");
        }
        catch(InstantiationException | IllegalAccessException | InvocationTargetException exc){
            throw new DataException("Could not instantiate data to it's default value");
        }

        this.tClass = tClass;
    }

    /**
     * Create an instance of the data type and assign an initial value
     * @param tClass the class of the object the data type is based on
     * @param initialValue the packet's initial value
     */
    public Data(Class<T> tClass, T initialValue){
        this.data = initialValue;
        this.tClass = tClass;
    }

    /**
     * Create an instance of a data type based on it's class object (not to be confused with {@code T}, the object the
     * data type is based on)
     * @param c the data type's class
     * @param <X> the data type. It must have an empty constructor
     * @return an instance of the data type, created from it's empty constructor
     * @throws DataException if the data type does not have an empty constructor or if an error occurred when instantiating
     *              the data type
     */
    public static <X extends Data> X fromClass(Class<X> c){
        try {
            Constructor<X> constructor = c.getConstructor();
            return constructor.newInstance();
        }
        catch(NoSuchMethodException exc){
            throw new DataException("Data types need to have an empty constructor to be instantiated to a default value");
        }
        catch(InstantiationException | IllegalAccessException | InvocationTargetException exc){
            throw new DataException("Could not instantiate data to it's default value");
        }
    }

    /**
     * Get the object the data type is based on
     * @return the object's class
     */
    public Class<T> getType(){
        return tClass;
    }

    /**
     * Set the data type's value from a different, compatible, data type
     * @param obj a data type object (can be a different data type from the current instance as long as the two are
     *            compatible)
     * @param <E> a data type, compatible with the current instance  i.e. the the {@code convert} function can convert
     * {@code obj} to a {@code T} instance
     */
    public <E extends Data> void set(E obj){
        set(convert(obj));
    }

    /**
     * Set the data's content
     * @param data the new data's content
     */
    public void set(T data) {
        this.data = data;
    }
    /**
     * Get the data's content
     * @return the data's content
     */
    public T get() {
        return data;
    }

    /**
     * Convert a {@code <E>} data type to a {@code <T>} instance
     *      note: {@code <E>} is a data type while {@code <T>} is the object the current data type is based on
     *
     * By default, convert the object if the two are the same type (which is required for the node system to work) and
     * throws an exception otherwise
     * @param obj a data type instance to convert
     * @param <E> a data type
     * @return the value the data type has been converted to (an instance of the object the current data type is based on)
     * @throws DataException if the data type cannot be converted to the current data type
     */
    public <E extends Data> T convert(E obj){
        if(getType().isInstance(obj.get())){
            return (T) obj.get();
        }

        throw new DataException("Cannot convert " + obj.getType().getName() + " to " + this.getType().getName());
    }
}
