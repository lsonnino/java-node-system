package jns.type;

import jns.system.Data;

/**
 * Void data type
 * Only has one state: null
 */
public class VoidData extends Data<Void> {
    public VoidData(){
        super(Void.class, null);
    }
    public VoidData(Object initialValue){
        super(Void.class, null);
    }

    public <E extends Data> Void convert(E obj) {
        return null;
    }
}
