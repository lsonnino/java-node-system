package jns.node;

import jns.system.NodeSystem;
import jns.type.BooleanData;
import jns.type.TypeSystem.Type;

/**
 * A BooleanData output
 * Inputs: None
 * Properties:
 *      * "Value": BooleanData
 * Outputs:
 *      * "Output": BooleanData: the same value as the "Value" property
 */
public class NumberInput extends BasicInput {
    public static final String VALUE = "Value";
    public static final String OUTPUT = "Output";

    public NumberInput(NodeSystem nodeSystem) {
        this(nodeSystem, false);
    }
    public NumberInput(NodeSystem nodeSystem, boolean initialValue) {
        super(nodeSystem, Type.NUMBER);

        property(VALUE).set(new BooleanData(initialValue));
    }
}
