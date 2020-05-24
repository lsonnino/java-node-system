package jns.node;

import jns.system.*;
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
public class BooleanInput extends BasicInput {
    public static final String VALUE = "Value";
    public static final String OUTPUT = "Output";

    public BooleanInput(NodeSystem nodeSystem) {
        this(nodeSystem, false);
    }
    public BooleanInput(NodeSystem nodeSystem, boolean initialValue) {
        super(nodeSystem, Type.BOOLEAN);

        property(VALUE).set(new BooleanData(initialValue));
    }
}
