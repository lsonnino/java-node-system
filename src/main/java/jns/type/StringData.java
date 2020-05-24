package jns.type;

import jns.system.Data;
import jns.system.DataException;

/**
 * Generic double array Data type
 * It is based on Java's Double Arrays object
 */
public class StringData extends Data<String> {
    public StringData(String initialValue){
        super(String.class, initialValue);
    }

    @Override
    public <E extends Data> String convert(E obj) {
        try{
            Double value = new NumberData().convert(obj);

            // Conversion successful
            return value.toString();
        }
        catch(DataException ignore){} // Try something else

        return super.convert(obj);
    }
}
