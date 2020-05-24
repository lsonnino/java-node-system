package jns.node;

import jns.system.NodeSystem;
import jns.type.ArrayData;
import jns.type.TypeSystem.Type;

/**
 * A ArrayData output
 * Inputs: None
 * Properties:
 *      * "Value": ArrayData
 * Outputs:
 *      * "Output": ArrayData: the same value as the "Value" property
 */
public class ArrayInput extends BasicInput {
    public ArrayInput(NodeSystem nodeSystem) {
        this(nodeSystem, new double[0]);
    }
    public ArrayInput(NodeSystem nodeSystem, double[] initialValue) {
        super(nodeSystem, Type.ARRAY);

        property(BasicInput.VALUE).set(new ArrayData(initialValue));
    }
}
