package jns.system;

import jns.type.TypeSystem;

import java.io.Serializable;

public class NodeSystem implements Serializable {
    private Type[] typeSystem;
    private Node root;

    public NodeSystem(){
        this(TypeSystem.typeSystem);
    }
    public NodeSystem(Type[] typeSystem){
        this.typeSystem = typeSystem;
    }

    public Type[] getTypeSystem() {
        return typeSystem;
    }
    public Class<? extends Data> getType(Enum type){
        for(Type t : typeSystem){
            if(t.is(type))
                return t.get();
        }

        throw new NodeRuntimeException("Unexpected type " + type.name());
    }

    public void setRoot(Node root) {
        this.root = root;
    }
    public Node getRoot() {
        return root;
    }

    public void run(){
        run(this.root);
    }
    public void run(Node root){
        if(root == null)
            throw new NodeRuntimeException("No root node");

        root.run();
    }
}
