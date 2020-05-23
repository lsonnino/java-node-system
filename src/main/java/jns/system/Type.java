package jns.system;

public class Type {
    private Enum identifier;
    private Class<? extends Data> data;

    public Type(Enum identifier, Class<? extends Data> data){
        this.identifier = identifier;
        this.data = data;
    }

    public boolean is(Enum identifier){
        return identifier.equals(this.identifier);
    }

    public Class<? extends Data> get(){
        return data;
    }
}
