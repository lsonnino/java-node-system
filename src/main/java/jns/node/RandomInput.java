package jns.node;

import jns.system.Data;
import jns.system.IO;
import jns.system.NodeSystem;
import jns.system.OptimizedNode;
import jns.type.BooleanData;
import jns.type.NumberData;
import jns.type.TypeSystem.Type;

/**
 * Produce a random NUMBER output between 0 and 1
 * Inputs: None
 * Properties: None
 * Outputs:
 *      * "Output": NumberData
 */
public class RandomInput extends OptimizedNode {
    public static final String OUTPUT = "Output";

    public RandomInput(NodeSystem nodeSystem) {
        super(
                nodeSystem,
                new IO[0],
                new IO[]{new IO(OUTPUT, Type.NUMBER)},
                new IO[0]
        );
    }

    @Override
    public Data<?> out(IO requested) {
        return new NumberData(Math.random());
    }
}
