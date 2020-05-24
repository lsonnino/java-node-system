package jns.node;

import jns.system.NodeSystem;
import jns.type.StringData;
import jns.type.TypeSystem.Type;

/**
 * A StringData output
 * Inputs: None
 * Properties:
 *      * "Value": StringData
 * Outputs:
 *      * "Output": StringData: the same value as the "Value" property
 */
public class StringInput extends BasicInput {
    public StringInput(NodeSystem nodeSystem) {
        this(nodeSystem, "");
    }
    public StringInput(NodeSystem nodeSystem, String initialValue) {
        super(nodeSystem, Type.STRING);

        property(BasicInput.VALUE).set(new StringData(initialValue));
    }
}
