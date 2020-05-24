package jns.node.math;

import jns.system.*;

public class Multiply extends ElementWiseOperation {
    public Multiply(NodeSystem nodeSystem) {
        super(nodeSystem);
    }

    @Override
    public Double execute(Double a, Double b) {
        return a * b;
    }
}
