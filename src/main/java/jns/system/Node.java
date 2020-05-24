package jns.system;

import jns.type.TypeSystem;

import java.io.Serializable;

/**
 * Defines the normal behaviour and structure of a node. A node instance contains:
 *      * the running state of the node. It is used to detect loops (if the node's state is {@code State.RUNNING}, the
 *          node is working or waiting for an input. Otherwise, the node's state is {@code State.READY})
 *      * a {@code NodeSystem} instance defining the data types allowed and handled by the nodes
 *          note: not every node is compatible with every node system
 *      * it's input io's. When an input is connected, the corresponding {@code InputInterface} is set to define the connection.
 *          Otherwise the input interface is set to null
 *      * it's output io's. A node is only allowed to call an io which is explicitly supported by the node
 *      * some properties that cannot be defined by an input node (except through the {@code out} function)
 *
 * Node also automatically set its io's id's in sequential order starting from 0
 *
 * A node must extend this class and define it's abstract functions
 */
public abstract class Node implements Serializable {
    private enum State {
        READY,
        RUNNING;
    }

    private State state;
    private NodeSystem system;
    private IO[] inputs, outputs, properties;
    private InputInterface[] inputNodes;
    private Data<?>[] propertiesData;

    /**
     * A node constructor defining the node's basic properties
     * @param nodeSystem the node system in use
     * @param inputIO the inputs accepted/needed by the node
     * @param outputIO the output supported by the node
     * @param propertyIO some internal properties the node needs
     */
    public Node(NodeSystem nodeSystem, IO[] inputIO, IO[] outputIO, IO[] propertyIO){
        this.state = State.READY;
        this.system = nodeSystem;

        this.inputs = setIO(inputIO);
        this.outputs = setIO(outputIO);
        this.properties = setIO(propertyIO);

        this.inputNodes = new InputInterface[this.inputs.length];
        this.propertiesData = new Data[this.properties.length];
        for (int i = 0; i < this.properties.length; i++) {
            this.propertiesData[i] = Data.fromClass(this.system.getType(this.properties[i].getType()));
        }
    }
    private IO[] setIO(IO[] model){
        IO[] out = new IO[model.length];

        for (int i = 0; i < out.length; i++) {
            out[i] = new IO(model[i], i);
        }

        return out;
    }

    /**
     * Get an input io based on it's name. If input io's names are not unique, the correctness of this function cannot
     * be guaranteed
     * @param name the (unique) name of the io
     * @return the io instance if it has been found, null otherwise
     */
    public IO getInput(String name){
        for (int i = 0; i < this.inputs.length; i++) {
            if(this.inputs[i].getName().equals(name))
                return this.inputs[i];
        }

        return null;
    }
    /**
     * Get an input io based on it's unique id
     * @param id the io's id
     * @return the io instance if it has been found, null otherwise
     */
    public IO getInput(int id){
        if(id < 0 || id >= this.inputs.length){
            return null;
        }

        return this.inputs[id];
    }
    /**
     * Get an output io based on it's name. If output io's names are not unique, the correctness of this function cannot
     * be guaranteed
     * @param name the (unique) name of the io
     * @return the io instance if it has been found, null otherwise
     */
    public IO getOutput(String name){
        for (int i = 0; i < this.outputs.length; i++) {
            if(this.outputs[i].getName().equals(name))
                return this.outputs[i];
        }

        return null;
    }
    /**
     * Get an output io based on it's unique id
     * @param id the io's id
     * @return the io instance if it has been found, null otherwise
     */
    public IO getOutput(int id){
        if(id < 0 || id >= this.outputs.length){
            return null;
        }

        return this.outputs[id];
    }
    /**
     * Get a property io based on it's name. If properties io's names are not unique, the correctness of this function cannot
     * be guaranteed
     * @param name the (unique) name of the io
     * @return the io instance if it has been found, null otherwise
     */
    public IO getProperty(String name){
        for (int i = 0; i < this.properties.length; i++) {
            if(this.properties[i].getName().equals(name))
                return this.properties[i];
        }

        return null;
    }
    /**
     * Get a property io based on it's unique id
     * @param id the io's id
     * @return the io instance if it has been found, null otherwise
     */
    public IO getProperty(int id){
        if(id < 0 || id >= this.properties.length){
            return null;
        }

        return this.properties[id];
    }

    /**
     * The following methods are used to connect an input io to a node
     * @param input the current node's input io (name, id or io instance)
     * @param node the node the input is connected to
     * @param output the connected node's output io (name, id or io instance)
     */
    public void connect(int input, Node node, int output){
        this.connect(getInput(input), node, node.getOutput(output));
    }
    public void connect(String input, Node node, String output){
        this.connect(getInput(input), node, node.getOutput(output));
    }
    public void connect(String input, Node node, int output){
        this.connect(getInput(input), node, node.getOutput(output));
    }
    public void connect(int input, Node node, String output){
        this.connect(getInput(input), node, node.getOutput(output));
    }
    public void connect(IO input, Node node, IO output){
        this.inputNodes[input.getId()] = new InputInterface(input, node, output);
    }
    /**
     * Disconnect the input's io. If the input was not connected, nothing happens
     * @param input the input io instance to disconnect
     */
    public void disconnect(IO input){
        this.inputNodes[input.getId()] = null;
    }

    /**
     * Request data from an input
     * @param id, name, requested the input io's name, id or instance. If input io's names are not unique, the correctness
     *            of this function cannot be guaranteed if the input io is referenced by it's name
     * @return a data packet the same type as the input io containing the requested information. If the input is connected
     *            to a different type output, the conversion is automatically handled (if possible)
     *
     * @throws NodeRuntimeException if the specified input was not connected
     */
    public Data<?> in(int id) {
        IO io = getInput(id);

        if(io == null){
            throw new NodeRuntimeException("Invalid input index: " + id);
        }

        return in(io);
    }
    public Data<?> in(String name) {
        IO io = getInput(name);

        if(io == null){
            throw new NodeRuntimeException("Invalid input name: " + name);
        }

        return in(io);
    }
    public Data<?> in(IO requested){
        if(requested.getId() < 0 || requested.getId() >= this.inputNodes.length){
            throw new ArrayIndexOutOfBoundsException("Invalid input " + requested.getId() + " (named: \'" + requested.getName() + "\')");
        }

        InputInterface inputInterface = this.inputNodes[requested.getId()];
        if(inputInterface == null){
            throw new NodeRuntimeException("Disconnected input " + requested.getId() + " (named: \'" + requested.getName() + "\')");
        }

        Data data = inputInterface.inputNode.run(inputInterface.inputNodeOutput);
        Data out = Data.fromClass(this.system.getType(requested.getType()));
        out.set(data.get());

        return out;
    }

    /**
     * Request data from a property
     * @param id, name, requested the property io's name, id or instance. If properties io's names are not unique, the correctness
     *            of this function cannot be guaranteed if the input io is referenced by it's name
     * @return a data packet the same type as the property io containing the requested information.
     *
     * @throws NodeRuntimeException if the specified property's id or name is invalid
     */
    public Data<?> property(int id) {
        IO io = getProperty(id);

        if(io == null){
            throw new NodeRuntimeException("Invalid input index: " + id);
        }

        return property(io);
    }
    public Data<?> property(String name) {
        IO io = getProperty(name);

        if(io == null){
            throw new NodeRuntimeException("Invalid input name: " + name);
        }

        return property(io);
    }
    public Data<?> property(IO requested){
        if(requested.getId() < 0 || requested.getId() >= this.propertiesData.length){
            throw new ArrayIndexOutOfBoundsException("Invalid property " + requested.getId() + " (named: \'" + requested.getName() + "\')");
        }

        return this.propertiesData[requested.getId()];
    }

    /**
     * Get an output requested by a connected node
     * @param requested the io to which the node is connected to. The correctness of this parameter has been previously
     *                  checked
     * @return a data of this node's requested output io containing the requested information. Data type conversions will
     *                  be automatically handled if possible
     * @throws NodeRuntimeException if the requested output is invalid or if the node is already running
     */
    public abstract Data<?> out(IO requested);
    Data<?> run(){
        if(this.outputs.length > 0)
            throw new NodeRuntimeException("An output node need to be specified");

        return runUnchecked(new IO("Output", TypeSystem.Type.VOID, -1));
    }
    Data<?> run(IO requested){
        boolean found = false;
        for (IO io : this.outputs) {
            if(io.equals(requested)) {
                found = true;
                break;
            }
        }
        if(!found)
            throw new NodeRuntimeException("Cannot find output " + requested.getId() + " (named: \'" + requested.getName() + "\')");

        return runUnchecked(requested);
    }
    private Data<?> runUnchecked(IO requested){
        if(this.state == State.RUNNING)
            throw new NodeRuntimeException("Node already running. This may be caused by a loop in the node tree");

        this.state = State.RUNNING;

        Data data = out(requested);

        this.state = State.READY;

        return data;
    }

    /**
     * Define the node's behaviour when the node tree execution ends. By default, simply propagates the reset signal to
     * it's connected children
     */
    public void reset(){
        for(InputInterface inputInterface : this.inputNodes){
            inputInterface.inputNode.reset();
        }
    }

    private class InputInterface implements Serializable {
        Node inputNode;
        IO inputNodeOutput, inputIO;

        InputInterface(IO inputIO, Node inputNode, IO inputNodeOutput){
            this.inputIO = inputIO;
            this.inputNode = inputNode;
            this.inputNodeOutput = inputNodeOutput;
        }
    }
}
