package jns.type;

import jns.system.Data;

/**
 * Boolean based Data type
 * It is compatible with any Data type based on Java's numbers (Integer, Double, ...) with the following conversion:
 *      * true if the number is not 0
 *      * false if the number is 0
 */
public class BooleanData extends Data<Boolean> {
    public BooleanData(){
        this(Boolean.FALSE);
    }
    public BooleanData(boolean initialValue){
        super(Boolean.class, initialValue);
    }

    public <E extends Data> Boolean convert(E obj) {
        if(obj.get() instanceof Double){
            Double value = (Double) obj.get();

            return value != 0;
        }
        else if(obj.get() instanceof Float){
            Float value = (Float) obj.get();

            return value != 0;
        }
        else if(obj.get() instanceof Integer){
            Integer value = (Integer) obj.get();

            return value != 0;
        }
        else if(obj.get() instanceof Short){
            Short value = (Short) obj.get();

            return value != 0;
        }
        else if(obj.get() instanceof Long){
            Long value = (Long) obj.get();

            return value != 0;
        }

        return super.convert(obj);
    }
}
