package jns.type;

import jns.system.Data;

/**
 * Generic number Data type
 * It is based on Java's Double object and is compatible with any Data type based on Java's primitive types (Integer, ...)
 */
public class NumberData extends Data<Double> { // This data type is based upon java's Double object
    public NumberData(){ // An empty constructor is highly recommended for all functionality to work properly
        this(0);
    }
    public NumberData(double initialValue){ // A constructor taking an initial value is recommended for ease of use
        super(Double.class, initialValue);
    }

    /**
     * Convert the input data type containing an unknown value into a value that can be stored inside this data type
     * @param obj a data type instance to convert
     * @return a value as it could be stored inside this data type
     */
    @Override
    public <E extends Data> Double convert(E obj) {
        if(obj.get() instanceof Boolean){ // Handle conversion with a Boolean based data type
            return (Boolean) obj.get() ? 1.0 : 0.0;
        }
        else if(obj.get() instanceof Float){ // Handle conversion with a Float based data type
            Float value = (Float) obj.get();

            return value.doubleValue();
        }
        else if(obj.get() instanceof Integer){ // Handle conversion with a Integer based data type
            Integer value = (Integer) obj.get();

            return value.doubleValue();
        }
        else if(obj.get() instanceof Short){ // Handle conversion with a Short based data type
            Short value = (Short) obj.get();

            return value.doubleValue();
        }
        else if(obj.get() instanceof Long){ // Handle conversion with a Long based data type
            Long value = (Long) obj.get();

            return value.doubleValue();
        }

        // End with the super call to handle conversion between a data type the same type as this one (here Double based)
        // and throws error if not compatible
        return super.convert(obj);
    }
}
