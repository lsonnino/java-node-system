package jns.node;

import jns.system.*;
import jns.type.BooleanData;
import jns.type.TypeSystem.Type;

public class And extends OptimizedNode {
    public static final String INPUT_1 = "Input 1", INPUT_2 = "Input 2";
    public static final String OUTPUT = "Output";

    public And(NodeSystem nodeSystem) {
        super(
                nodeSystem,
                new IO[]{new IO(INPUT_1, Type.BOOLEAN), new IO(INPUT_2, Type.BOOLEAN)},
                new IO[]{new IO(OUTPUT, Type.BOOLEAN)},
                new IO[0]
        );
    }

    @Override
    public Data<?> out(IO requested) {
        BooleanData in1 = (BooleanData) in(INPUT_1);
        BooleanData in2 = (BooleanData) in(INPUT_2);

        return new BooleanData(in1.get() && in2.get());
    }
}
