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
        super(
                nodeSystem,
                new IO[]{new IO(INPUT, Type.BOOLEAN)},
                new IO[0],
                new IO[0]
        );
    }

    @Override
    public Data<?> out(IO requested) {
        System.out.println(property(INPUT).get());

        return new VoidData();
    }
}
