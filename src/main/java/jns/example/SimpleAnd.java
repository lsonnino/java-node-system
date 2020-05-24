package jns.example;

import jns.system.NodeSystem;
import jns.node.*;

/**
 * Create a two AND circuit with two output and launch the circuit for each output
 */
public class SimpleAnd {
    public static void main(String[] args) {
        // Create the Node System
        NodeSystem nodeSystem = new NodeSystem();

        // Create the boolean input
        BooleanInput input1 = new BooleanInput(nodeSystem, true);
        BooleanInput input2 = new BooleanInput(nodeSystem, true);
        BooleanInput input3 = new BooleanInput(nodeSystem, false);

        // Create the AND gates
        And and1 = new And(nodeSystem);
        And and2 = new And(nodeSystem);

        // Create components to display the output
        SystemOut displayer1 = new SystemOut(nodeSystem);
        SystemOut displayer2 = new SystemOut(nodeSystem, and2); // This one is already connected to the second AND gate's output

        // Connect the AND gates
        and1.connect(0, input1, BooleanInput.OUTPUT);
        and1.connect(1, input2, BooleanInput.OUTPUT);
        and2.connect(0, input3, BooleanInput.OUTPUT);
        and2.connect(1, and1, And.OUTPUT);

        // Connect the first displayer (the second one is already connected via the constructor)
        displayer1.connect(SystemOut.INPUT, and1, And.OUTPUT);

        // Set the second displayer as the circuit's output
        nodeSystem.setRoot(displayer2);

        System.out.println("Displayer 1 output:"); // Result should be true
        nodeSystem.run(displayer1); // Run with the first displayer as output
        System.out.println("Displayer 2 output:"); // Result should be false
        nodeSystem.run(); // Run with the second displayer as output
    }
}
