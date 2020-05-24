package jns.node;

import jns.system.NodeSystem;
import jns.type.TypeSystem.Type;
import jns.type.VoidData;

/**
 * A VoidData output
 * Inputs: None
 * Properties:
 *      * "Value": VoidData
 * Outputs:
 *      * "Output": VoidData: the same value as the "Value" property
 */
public class VoidInput extends BasicInput {
    public VoidInput(NodeSystem nodeSystem) {
        super(nodeSystem, Type.VOID);

        property(BasicInput.VALUE).set(new VoidData());
    }
}
