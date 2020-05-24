package jns.node;

import jns.system.NodeSystem;

/**
 * AND gate node
 * Inputs:
 *      * "Input " + i: BooleanData, where i is the input number (starting from 1)
 * Properties: None
 * Outputs:
 *      * "Output": BooleanData: is equal to the logic OR between all the input
 */
public class Or extends LogicGate {
    /**
     * Creates an OR gate with two inputs
     * @param nodeSystem the node system in use
     */
    public Or(NodeSystem nodeSystem) {
        this(nodeSystem, 2);
    }

    /**
     * Create an OR gate with any number of inputs
     * @param nodeSystem the node system in use
     * @param inputNumber the number of inputs
     */
    public Or(NodeSystem nodeSystem, int inputNumber) {
        super(nodeSystem, inputNumber);
    }

    @Override
    public boolean logic(boolean[] input) {
        boolean result = false;

        for(boolean i : input){
            result = result || i;
        }

        return result;
    }
}
