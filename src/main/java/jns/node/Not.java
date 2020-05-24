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
public abstract class Not extends OptimizedNode {
    public static final String INPUT = "Input";
    public static final String OUTPUT = "Output";

    public Not(NodeSystem nodeSystem) {
        super(
                nodeSystem,
                new IO[]{new IO(INPUT, Type.BOOLEAN)},
                new IO[]{new IO(OUTPUT, Type.BOOLEAN)},
                new IO[0]
        );
    }

    @Override
    public Data<?> out(IO requested) {
        BooleanData data = (BooleanData) in(INPUT);

        return data.invert();
    }
}
