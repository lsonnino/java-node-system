package jns.system;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;

public abstract class Data<T> implements Serializable {
    private T data;
    private Class<T> tClass;

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
    public Data(Class<T> tClass, T initialValue){
        this.data = initialValue;
        this.tClass = tClass;
    }

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

    public Class<T> getType(){
        return tClass;
    }

    public <E extends Data> void set(E obj){
        set(convert(obj));
    }
    public void set(T data) {
        this.data = data;
    }
    public T get() {
        return data;
    }

    public <E extends Data> T convert(E obj){
        if(getType().isInstance(obj.get())){
            return (T) obj.get();
        }

        throw new DataException("Cannot convert " + obj.getType().getName() + " to " + this.getType().getName());
    }
}
