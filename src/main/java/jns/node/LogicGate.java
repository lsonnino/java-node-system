package jns.node;

import jns.system.Data;
import jns.system.IO;
import jns.system.NodeSystem;
import jns.system.OptimizedNode;
import jns.type.BooleanData;
import jns.type.TypeSystem.Type;

/**
 * AND gate node
 * Inputs:
 *      * "Input " + i: BooleanData, where i is the input number (starting from 1)
 * Properties: None
 * Outputs:
 *      * "Output": BouleanData: the result of the logic gate
 *
 * A logic gate can implement this class and implement the {@code logic} function
 */
public abstract class LogicGate extends OptimizedNode {
    public static final String INPUT = "Input";
    public static final String OUTPUT = "Output";
    private int inputNumber;

    /**
     * The logic gate's constructor
     * @param nodeSystem the node system in use
     * @param inputNumber the number of inputs
     */
    public LogicGate(NodeSystem nodeSystem, int inputNumber) {
        super(
                nodeSystem,
                genInput(inputNumber),
                new IO[]{new IO(OUTPUT, Type.BOOLEAN)},
                new IO[0]
        );

        this.inputNumber = inputNumber;
    }

    private static IO[] genInput(int number){
        IO[] array = new IO[number];
        for (int i = 0; i < number; i++) {
            array[i] = new IO(getInputName(i), Type.BOOLEAN);
        }

        return array;
    }
    public static String getInputName(int index){
        return INPUT + " " + (index + 1);
    }

    @Override
    public Data<?> out(IO requested) {
        boolean[] inputs = new boolean[this.inputNumber];

        for (int i = 0; i < this.inputNumber; i++) {
            BooleanData data = (BooleanData) in(i);
            inputs[i] = data.get();
        }

        return new BooleanData(logic(inputs));
    }

    /**
     * Get the number of inputs
     * @return the number of inputs
     */
    public int getInputNumber() {
        return inputNumber;
    }

    /**
     *
     * @param input an array of boolean with the same number of inputs as specified in the constructor
     * @return the logic gate's result
     */
    public abstract boolean logic(boolean[] input);
}
