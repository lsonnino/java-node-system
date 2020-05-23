package jns.system;

import jns.type.TypeSystem;

import java.io.Serializable;

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

    public IO getInput(String name){
        for (int i = 0; i < this.inputs.length; i++) {
            if(this.inputs[i].getName().equals(name))
                return this.inputs[i];
        }

        return null;
    }
    public IO getInput(int id){
        if(id < 0 || id >= this.inputs.length){
            return null;
        }

        return this.inputs[id];
    }
    public IO getOutput(String name){
        for (int i = 0; i < this.outputs.length; i++) {
            if(this.outputs[i].getName().equals(name))
                return this.outputs[i];
        }

        return null;
    }
    public IO getOutput(int id){
        if(id < 0 || id >= this.outputs.length){
            return null;
        }

        return this.outputs[id];
    }
    public IO getProperty(String name){
        for (int i = 0; i < this.properties.length; i++) {
            if(this.properties[i].getName().equals(name))
                return this.properties[i];
        }

        return null;
    }
    public IO getProperty(int id){
        if(id < 0 || id >= this.properties.length){
            return null;
        }

        return this.properties[id];
    }

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
    public void disconnect(IO input){
        this.inputNodes[input.getId()] = null;
    }

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
