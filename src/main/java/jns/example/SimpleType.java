package jns.example;


import jns.system.Data;
import jns.type.NumberData;

public class SimpleType extends Data<Integer> {
    public SimpleType(){
        this(0);
    }
    public SimpleType(int initialValue){
        super(Integer.class, initialValue);
    }

    @Override
    public <E extends Data> Integer convert(E obj) {
        if(obj.get() instanceof Double){
            Double value = (Double) obj.get();

            return value.intValue();
        }

        return super.convert(obj);
    }

    public static void main(String[] args) {
        NumberData numberData = new NumberData(3);

        SimpleType simpleTypeData = new SimpleType();
        simpleTypeData.set(numberData);

        System.out.println(simpleTypeData.get());
    }
}
