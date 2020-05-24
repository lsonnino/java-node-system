package jns.node;

import jns.system.Data;
import jns.system.IO;
import jns.system.NodeSystem;
import jns.system.OptimizedNode;
import jns.type.BooleanData;
import jns.type.TypeSystem.Type;

/**
 * NOT gate node
 * Inputs:
 *      * "Input": BooleanData
 * Properties: None
 * Outputs:
 *      * "Output": BouleanData: the inverted input
 */
public abstract class Not extends OptimizedNode { // Extending OptimizedNode is recommended to avoid useless computations
    // Defining IO names for ease of use
    public static final String INPUT = "Input";
    public static final String OUTPUT = "Output";

    public Not(NodeSystem nodeSystem) {
        super(
                nodeSystem,
                new IO[]{new IO(INPUT, Type.BOOLEAN)}, // Defining the single input of type BOOLEAN from the default type system
                new IO[]{new IO(OUTPUT, Type.BOOLEAN)}, // Defining the single output of the same type
                new IO[0]
        );
    }

    @Override
    public Data<?> out(IO requested) {
        // Only one possible output (the requested IO's validity has already been checked automatically): no need to check
        // which output has been asked for

        // Get needed inputs
        BooleanData data = (BooleanData) in(INPUT); // Data type conversion between nodes is handled automatically

        // Produce a new Data type BooleanData with the inverted input
        return data.invert();
    }
}
