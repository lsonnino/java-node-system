package jns.system;

public abstract class OptimizedNode extends Node {
    private Data[] loadedOutput;

    public OptimizedNode(NodeSystem nodeSystem, IO[] inputIO, IO[] outputIO, IO[] propertyIO) {
        super(nodeSystem, inputIO, outputIO, propertyIO);

        loadedOutput = new Data[outputIO.length];
    }

    @Override
    Data<?> run(IO requested) {
        if(requested.getId() >= 0 && requested.getId() < loadedOutput.length){
            Data<?> loaded = loadedOutput[requested.getId()];
            if(loaded != null){
                return loaded;
            }
        }

        return super.run(requested);
    }

    @Override
    public void reset() {
        for (int i = 0; i < loadedOutput.length; i++) {
            loadedOutput[i] = null;
        }

        super.reset();
    }
}
