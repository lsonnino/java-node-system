package jns.node;

import jns.system.Data;
import jns.system.IO;
import jns.system.NodeSystem;
import jns.system.OptimizedNode;
import jns.type.BooleanData;
import jns.type.TypeSystem.Type;

/**
 * Produces an output equal to its single property
 * Inputs: None
 * Properties:
 *      * "Value": the same type as given as a constructor parameter
 * Outputs:
 *      * "Output": the same as it's property value
 */
public class BasicInput extends OptimizedNode {
    public static final String VALUE = "Value";
    public static final String OUTPUT = "Output";

    public BasicInput(NodeSystem nodeSystem, Enum type) {
        super(
                nodeSystem,
                new IO[0],
                new IO[]{new IO(OUTPUT, type)},
                new IO[]{new IO(VALUE, type)}
        );
    }

    @Override
    public Data<?> out(IO requested) {
        return property(VALUE);
    }
}
