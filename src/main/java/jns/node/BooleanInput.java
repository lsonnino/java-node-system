package jns.node;

import jns.system.Data;
import jns.system.IO;
import jns.system.Node;
import jns.system.NodeSystem;
import jns.type.BooleanData;
import jns.type.TypeSystem.Type;

public class BooleanInput extends Node {
    public static final String VALUE = "Value";
    public static final String OUTPUT = "Output";

    public BooleanInput(NodeSystem nodeSystem) {
        this(nodeSystem, false);
    }
    public BooleanInput(NodeSystem nodeSystem, boolean initialValue) {
        super(
                nodeSystem,
                new IO[0],
                new IO[]{new IO(OUTPUT, Type.BOOLEAN)},
                new IO[]{new IO(VALUE, Type.BOOLEAN)}
        );

        property(VALUE).set(new BooleanData(initialValue));
    }

    @Override
    public Data<?> out(IO requested) {
        BooleanData in1 = (BooleanData) property(VALUE);

        return in1;
    }
}
