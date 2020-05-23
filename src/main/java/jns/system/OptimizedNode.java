package jns.system;

/**
 * A Node extension that avoid computing twice the same output
 * If the same output is used twice, only computes once the output at the cost of some extra memory usage
 */
public abstract class OptimizedNode extends Node {
    private Data[] loadedOutput;

    public OptimizedNode(NodeSystem nodeSystem, IO[] inputIO, IO[] outputIO, IO[] propertyIO) {
        super(nodeSystem, inputIO, outputIO, propertyIO);

        loadedOutput = new Data[outputIO.length];
    }

    /**
     * If the requested output has already been computed, return the answer without computing it again.
     * Otherwise, compute and remember the answer.
     * @param requested an IO instance telling which output is requested
     * @return a Data instance the same type as the node's output containing the answer
     */
    @Override
    Data<?> run(IO requested) {
        if(requested.getId() >= 0 && requested.getId() < loadedOutput.length){ // Check the IO id
            Data<?> loaded = loadedOutput[requested.getId()];
            if(loaded != null){ // Check if the output has already been computed
                return loaded;
            }
            else {
                // Compute the output
                Data output = super.run(requested);
                // Remember the output
                loadedOutput[requested.getId()] = output;
                return output;
            }
        }

        // Fall back on the default behaviour
        return super.run(requested);
    }

    /**
     * Reset the node's memory: every output will need to be computed once again if needed
     */
    @Override
    public void reset() {
        for (int i = 0; i < loadedOutput.length; i++) {
            loadedOutput[i] = null;
        }

        super.reset();
    }
}
