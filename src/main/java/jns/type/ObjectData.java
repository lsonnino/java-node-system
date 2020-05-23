package jns.type;

import jns.system.Data;

public class ObjectData extends Data<Object> {
    public ObjectData(){
        this(null);
    }
    public ObjectData(Object obj){
        super(Object.class, obj);
    }

    public <E extends Data> Object convert(E obj) {
        return obj.get();
    }
}
