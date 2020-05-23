package jns.system;

import java.io.Serializable;
import java.util.Objects;

public class IO implements Serializable {
    private String name;
    private Enum type;
    private int id;

    public IO(String name, Enum type){
        this(name, type, -1);
    }
    IO(IO model, int id){
        this(model.name, model.type, id);
    }
    IO(String name, Enum type, int id){
        this.name = name;
        this.type = type;
        this.id = id;
    }

    void setID(int id){
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Enum getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IO io = (IO) o;
        return id == io.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, id);
    }
}
