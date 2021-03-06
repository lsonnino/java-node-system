package jns.node;

import jns.system.*;
import jns.type.BooleanData;
import jns.type.TypeSystem.Type;
import jns.type.VoidData;

/**
 * Displays his input to the standard output
 * Inputs:
 *      * "Input": ObjectData
 * Properties: None
 * Outputs: None
 */
public class SystemOut extends OptimizedNode {
    public static final String INPUT = "Input";

    public SystemOut(NodeSystem nodeSystem) {
        this(nodeSystem, null);
    }
    public SystemOut(NodeSystem nodeSystem, Node input) {
        super(
                nodeSystem,
                new IO[]{new IO(INPUT, Type.ANY)},
                new IO[0],
                new IO[0]
        );

        if(input != null)
            connect(INPUT, input, 0);
    }

    @Override
    public Data<?> out(IO requested) {
        System.out.println(in(INPUT).get());

        return new VoidData();
    }
}
