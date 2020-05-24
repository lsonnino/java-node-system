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
    public BooleanInput(NodeSystem nodeSystem) { // Empty node defining the property's initial value
        this(nodeSystem, false);
    }
    public BooleanInput(NodeSystem nodeSystem, boolean initialValue) {
        super(nodeSystem, Type.BOOLEAN); // Defining the node based on Type.BOOLEAN type from the default type system

        // Setting the node's default value
        property(BasicInput.VALUE).set(new BooleanData(initialValue));
    }
}
