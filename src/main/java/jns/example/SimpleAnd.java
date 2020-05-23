package jns.example;

import jns.system.NodeSystem;
import jns.node.*;

public class SimpleAnd {
    public static void main(String[] args) {
        NodeSystem nodeSystem = new NodeSystem();

        BooleanInput input1 = new BooleanInput(nodeSystem, true);
        BooleanInput input2 = new BooleanInput(nodeSystem, true);
        BooleanInput input3 = new BooleanInput(nodeSystem, false);

        And and1 = new And(nodeSystem);
        And and2 = new And(nodeSystem);

        SystemOut displayer1 = new SystemOut(nodeSystem);
        SystemOut displayer2 = new SystemOut(nodeSystem, and2);

        and1.connect(And.INPUT_1, input1, BooleanInput.OUTPUT);
        and1.connect(And.INPUT_2, input2, BooleanInput.OUTPUT);
        and1.connect(And.INPUT_1, input3, BooleanInput.OUTPUT);
        and2.connect(And.INPUT_2, and1, And.OUTPUT);

        displayer1.connect(SystemOut.INPUT, and1, And.INPUT_1);

        nodeSystem.setRoot(displayer2);

        nodeSystem.run(displayer1);
        nodeSystem.run();
    }
}
