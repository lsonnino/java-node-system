package jns.node;

        import jns.system.*;

/**
 * AND gate node
 * Inputs:
 *      * "Input " + i: BooleanData, where i is the input number (starting from 1)
 * Properties: None
 * Outputs:
 *      * "Output": BooleanData: is equal to the logic AND between all the input
 */
public class And extends LogicGate {
    /**
     * Creates an AND gate with two inputs
     * @param nodeSystem the node system in use
     */
    public And(NodeSystem nodeSystem) {
        this(nodeSystem, 2);
    }
    /**
     * Create an AND gate with any number of inputs
     * @param nodeSystem the node system in use
     * @param inputNumber the number of inputs
     */
    public And(NodeSystem nodeSystem, int inputNumber) {
        super(nodeSystem, inputNumber);
    }

    @Override
    public boolean logic(boolean[] input) {
        // The input array contains all the inputs in sequential order

        boolean result = true;

        for(boolean i : input){
            result = result && i;
        }

        return result;
    }
}
