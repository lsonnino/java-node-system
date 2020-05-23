package jns.system;

import jns.type.TypeSystem;

import java.io.Serializable;

/**
 * Handles node trees
 */
public class NodeSystem implements Serializable {
    private Type[] typeSystem;
    private Node root;

    /**
     * Default constructor. It has no root nodes and uses the default Type System
     */
    public NodeSystem(){
        this(TypeSystem.typeSystem);
    }
    /**
     * Define a Type System but no root nodes.
     * Note that every node may not be compatible with every Type Systems
     * @param typeSystem a Type array defining the type system to use
     */
    public NodeSystem(Type[] typeSystem){
        this.typeSystem = typeSystem;
    }

    /**
     * Get the used Type System
     * @return a Type array defining the current Type System
     */
    public Type[] getTypeSystem() {
        return typeSystem;
    }
    /**
     * Get the Class object corresponding to a specific Type identifier
     * @param type the type identifier
     * @return The class object corresponding to the identifier
     */
    public Class<? extends Data> getType(Enum type){
        for(Type t : typeSystem){
            if(t.is(type))
                return t.get();
        }

        throw new NodeRuntimeException("Unexpected type " + type.name());
    }

    /**
     * Set the root node to execute by default
     * @param root a Node instance with no outputs
     */
    public void setRoot(Node root) {
        this.root = root;
    }
    /**
     * Get the current root node
     * @return a Node instance if the root node is set, null otherwise
     */
    public Node getRoot() {
        return root;
    }

    /**
     * Execute the node tree ending with the specified root node
     */
    public void run(){
        run(this.root);
    }
    /**
     * Execute the node tree ending with a specific root node
     * @param root a node instance with no output. It will be the end of the tree
     */
    public void run(Node root){
        if(root == null)
            throw new NodeRuntimeException("No root node");

        root.run();

        root.reset();
    }
}
