package jns.type;

import jns.system.Data;

public class NumberData extends Data<Double> {
    public NumberData(){
        this(0);
    }
    public NumberData(double initialValue){
        super(initialValue);
    }

    @Override
    public <E extends Data> Double convert(E obj) {
        if(obj.get() instanceof Boolean){
            return (Boolean) obj.get() ? 1.0 : 0.0;
        }
        else if(obj.get() instanceof Float){
            Float value = (Float) obj.get();

            return value.doubleValue();
        }
        else if(obj.get() instanceof Integer){
            Integer value = (Integer) obj.get();

            return value.doubleValue();
        }
        else if(obj.get() instanceof Short){
            Short value = (Short) obj.get();

            return value.doubleValue();
        }
        else if(obj.get() instanceof Long){
            Long value = (Long) obj.get();

            return value.doubleValue();
        }

        return super.convert(obj);
    }
}
