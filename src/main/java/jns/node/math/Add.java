package jns.node.math;

import jns.system.*;

public class Add extends ElementWiseOperation {
    public Add(NodeSystem nodeSystem) {
        super(nodeSystem);
    }

    @Override
    public Double execute(Double a, Double b) {
        return a + b;
    }
}
