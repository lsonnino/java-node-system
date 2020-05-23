package jns.example;


import jns.system.Data;
import jns.type.NumberData;

/**
 * A Data type example based on Java's Integer object
 */
public class SimpleType extends Data<Integer> {
    public SimpleType(){ // Empty constructor: must always be implemented
        this(0);
    }
    public SimpleType(int initialValue){ // Initial value constructor
        super(Integer.class, initialValue);
    }

    @Override
    public <E extends Data> Integer convert(E obj) {
        if(obj.get() instanceof Double){ // Can convert a Double based Data type to a SimpleType one
            Double value = (Double) obj.get();

            return value.intValue();
        }

        // If the object to convert is not a Double type, let Data handle it
        return super.convert(obj);
    }

    /**
     * Test to create and convert a NumberData type to a SimpleType one
     */
    public static void main(String[] args) {
        NumberData numberData = new NumberData(3);

        SimpleType simpleTypeData = new SimpleType();
        simpleTypeData.set(numberData);

        System.out.println(simpleTypeData.get());
    }
}
