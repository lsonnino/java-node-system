package jns.type;

import jns.system.Data;
import jns.system.DataException;

/**
 * Generic double array Data type
 * It is based on Java's Double Arrays object
 */
public class ArrayData extends Data<Double[]> {
    public ArrayData(){
        this(0);
    }
    public ArrayData(int length){
        this(new double[length]);
    }
    public ArrayData(int length, double initialValue){
        this(new double[length]);

        Double[] data = get();
        for (int i = 0; i < length; i++) {
            data[i] = initialValue;
        }
    }
    public ArrayData(double[] initialValue){
        super(Double[].class, toObject(initialValue));
    }

    public static Double[] toObject(double[] data){
        Double[] array = new Double[data.length];

        for (int i = 0; i < data.length; i++) {
            array[i] = data[i];
        }

        return array;
    }

    @Override
    public <E extends Data> Double[] convert(E obj) {
        try{
            Double value = new NumberData().convert(obj);

            // Conversion successful
            return new Double[]{value};
        }
        catch(DataException ignore){} // Try something else

        return super.convert(obj);
    }
}
