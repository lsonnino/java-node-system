package jns.node;

import jns.system.NodeSystem;
import jns.type.NumberData;
import jns.type.TypeSystem.Type;

/**
 * A NumberData output
 * Inputs: None
 * Properties:
 *      * "Value": NumberData
 * Outputs:
 *      * "Output": NumberData: the same value as the "Value" property
 */
public class NumberInput extends BasicInput {
    public NumberInput(NodeSystem nodeSystem) {
        this(nodeSystem, 0);
    }
    public NumberInput(NodeSystem nodeSystem, double initialValue) {
        super(nodeSystem, Type.NUMBER);

        property(BasicInput.VALUE).set(new NumberData(initialValue));
    }
}
