package jns.node;

import jns.system.Data;
import jns.system.IO;
import jns.system.Node;
import jns.system.NodeSystem;
import jns.type.BooleanData;
import jns.type.TypeSystem.Type;
import jns.type.VoidData;

public class SystemOut extends Node {
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
