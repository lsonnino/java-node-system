# java-node-system



**java-node-system** is a powerful, easy to use library to implement a node based system.



## Basic concept

The node system is based around three basic classes:

* Data
* Node
* Node System



### Data

A Data or *data type* is an abstract packet containing data formated to be handled by nodes. It's main feature is a `convert` function used to convert data types into compatibles data types. Data types are based on an object (for instance, `jns.type.NumberData` is based on the `java.lang.Double` object which means it contains a `java.lang.Double` value). When implementing nodes, data types are referenced by identifiers, or *types* which is an `enum`. The `NodeSystem` then handles the translation between *type* and *data type*. For instance, the type `jns.type.TypeSystem.Type.NUMBER` references to the type `jns.type.NumberData`.

JNS comes with some basic data types (defined in `jns.type`) including:

* `Type.VOID` an empty type: only has one value, `null`
* `Type.ANY` a `java.lang.Object` based type: any *data type* can be converted into a `Type.ANY` type
* `Type.BOOLEAN` a boolean based type: only has two values, `true` and `false`
* `Type.NUMBER` a double based type
* `Type.ARRAY` a double array based type



### Node

A node is a computation unit taking inputs and using properties to produce an output. Inputs, outputs and properties are defined by an `IO` containing an unique id (automatically sequentially assigned. Id's are unique between inputs, outputs or properties for a single node), a name (unique names for a single nodes are recomended to propely identify an input/output/property by it's name) and a *data type*.

JNS comes with some basic nodes (defined in `jns.node`) including:

* `BooleanInput`: has no inputs, give on it's single output the value it has stored in his single property
* `RandomInput`: has no inputs, give on it's single output a random value between 0 and 1
* `And`: an n input AND logic gate
* `SystemOut` prints on his standard output it's single input, has no output nor properties



### Node System

Handles the correct node execution from a root node (the last node in the execution tree). It handles the data types, even tough an extension of the default data type is recomended to avoid incompatibilities.



## Usage

The implementation of JNS is done in four steps as follow.



### 1. Data types

To create a data type, simply extends `jns.system.Data` and implementing it's `convert` function. As an example, here is the implementation of the `jns.type.NumberData` data type:

```java
package jns.type;

import jns.system.Data;

/**
 * Generic number Data type
 * It is based on Java's Double object and is compatible with any Data type based on Java's primitive types (Integer, ...)
 */
public class NumberData extends Data<Double> { // This data type is based upon java's Double object
    public NumberData(){ // An empty constructor is highly recommended for all functionality to work properly
        this(0);
    }
    public NumberData(double initialValue){ // A constructor taking an initial value is recommended for ease of use
        super(Double.class, initialValue);
    }

    /**
     * Convert the input data type containing an unknown value into a value that can be stored inside this data type
     * @param obj a data type instance to convert
     * @return a value as it could be stored inside this data type
     */
    @Override
    public <E extends Data> Double convert(E obj) {
        if(obj.get() instanceof Boolean){ // Handle conversion with a Boolean based data type
            return (Boolean) obj.get() ? 1.0 : 0.0;
        }
        else if(obj.get() instanceof Float){ // Handle conversion with a Float based data type
            Float value = (Float) obj.get();

            return value.doubleValue();
        }
        else if(obj.get() instanceof Integer){ // Handle conversion with a Integer based data type
            Integer value = (Integer) obj.get();

            return value.doubleValue();
        }
        else if(obj.get() instanceof Short){ // Handle conversion with a Short based data type
            Short value = (Short) obj.get();

            return value.doubleValue();
        }
        else if(obj.get() instanceof Long){ // Handle conversion with a Long based data type
            Long value = (Long) obj.get();

            return value.doubleValue();
        }
        
        // End with the super call to handle conversion between a data type the same type as this one (here Double based)
        // and throws error if not compatible
        return super.convert(obj);
    }
}
```

The data type then has two functions:

* `get()`: get the value stored inside the data type
* `set(T value)`: set a value inside the data type, where `T` is the same as the object the data type is based on



A type system can then be implemented. A type system is defined as a `jns.system.Type` array where each entry links the identifier (an enumeration) to the data type's class.

Here following is the implementation of the default type system:

```java
package jns.type;

/**
 * The default JNS's Type System
 */
public class TypeSystem {
    // Defining the possible data types identifiers
    // These identifiers will be used to reference data types
    public enum Type {
        VOID,
        ANY,
        BOOLEAN,
        NUMBER,
        ARRAY;
    }

    // Connecting the identifiers to the actual Data type classes
    public static final jns.system.Type[] typeSystem = new jns.system.Type[]{
            new jns.system.Type(Type.VOID, VoidData.class),
            new jns.system.Type(Type.ANY, ObjectData.class),
            new jns.system.Type(Type.BOOLEAN, BooleanData.class),
            new jns.system.Type(Type.NUMBER, NumberData.class),
            new jns.system.Type(Type.ARRAY, ArrayData.class)
    };
}
```

<u>node:</u> an extension of this default type system is highly recommended to avoid incompatibilities with the implemented type system and the provided data types and nodes



### 2. Node System

Once the necessary data types have been implemented, a Node System instance can be created:

```java
// import jns.system.NodeSystem is required

// Use the default constructor to use the default type system
NodeSystem nodeSystem = new NodeSystem();

// Give the Type System as created in step 1 if one is required by the implementation
NodeSystem nodeSystem = new NodeSystem(Type[] typeSystem);
```

 The node system instance then has four main functions:

* `setRoot(Node root)`: set the root node, the one the node tree is based on
* `getRoot()`: get the root node
* `run(Node root)`: run the node tree based on a node different from the set root node
* `run()`: run the node tree from the root node

The node tree is executing with a bottom-up approach. The last node is executed first, executing it's child nodes if needed. The root node is this last node: the one executed first and producing his output at last.



### 3. Node

The third step is implementing the nodes. Here is an example of the implementation of the `jns.node.Not` node, implementing a Not logic gate (inverts his input):

```java
package jns.node;

import jns.system.Data;
import jns.system.IO;
import jns.system.NodeSystem;
import jns.system.OptimizedNode;
import jns.type.BooleanData;
import jns.type.TypeSystem.Type;

/**
 * NOT gate node
 * Inputs:
 *      * "Input": BooleanData
 * Properties: None
 * Outputs:
 *      * "Output": BouleanData: the inverted input
 */
public abstract class Not extends OptimizedNode { // Extending OptimizedNode is recommended to avoid useless computations
    // Defining IO names for ease of use
    public static final String INPUT = "Input";
    public static final String OUTPUT = "Output";

    public Not(NodeSystem nodeSystem) {
        super(
                nodeSystem,
                new IO[]{new IO(INPUT, Type.BOOLEAN)}, // Defining the single input of type BOOLEAN from the default type system
                new IO[]{new IO(OUTPUT, Type.BOOLEAN)}, // Defining the single output of the same type
                new IO[0]
        );
    }

    @Override
    public Data<?> out(IO requested) {
        // Only one possible output (the requested IO's validity has already been checked automatically): no need to check
        // which output has been asked for
        
        // Get needed inputs
        BooleanData data = (BooleanData) in(INPUT); // Data type conversion between nodes is handled automatically
        
        // Produce a new Data type BooleanData with the inverted input
        return data.invert();
        
        // An alternative to this last line would be:
        // BooleanData output = new BooleanData(! data.get());
        // return output;
    }
}
```



For ease of use, a shortcut exists to implement an n boolean logic gate using `jns.node.LogicGate`. For instance, here is the implementation of an AND gate (logical AND between all his inputs) from `jns.node.AND`:

```java
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
```



Implementing a generator node has also been made simplier using the `jns.node.BasicInput`. These nodes simply output the value stored in his single property. For instance, here is the implementation of `jns.node.BooleanInput` node:

```java
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

    public BooleanInput(NodeSystem nodeSystem) { // Empty node defining the property's initial value
        this(nodeSystem, false);
    }
    public BooleanInput(NodeSystem nodeSystem, boolean initialValue) {
        // Defining the node based on Type.BOOLEAN type from the default type system
        super(nodeSystem, Type.BOOLEAN);

        // Setting the node's default value
        property(VALUE).set(new BooleanData(initialValue));
    }
}
```

Nodes access their inputs with the `in(input)` function (where `input` is an argument defining which input to access) and their properties using the similar function `property`.



### 4. Creating the node tree

Creating a node tree is done by connecting nodes between them then executing the node tree with the node system. Here is an example from `jns.example.SimpleAnd`:

```java
package jns.example;

import jns.system.NodeSystem;
import jns.node.*;

/**
 * Create a two AND circuit with two output and launch the circuit for each output
 */
public class SimpleAnd {
    public static void main(String[] args) {
        // Create the Node System
        NodeSystem nodeSystem = new NodeSystem();

        // Create the boolean input
        BooleanInput input1 = new BooleanInput(nodeSystem, true);
        BooleanInput input2 = new BooleanInput(nodeSystem, true);
        BooleanInput input3 = new BooleanInput(nodeSystem, false);

        // Create the AND gates
        And and1 = new And(nodeSystem);
        And and2 = new And(nodeSystem);

        // Create components to display the output
        SystemOut displayer1 = new SystemOut(nodeSystem);
        SystemOut displayer2 = new SystemOut(nodeSystem, and2); // This one is already connected to the second AND gate's output

        // Connect the AND gates
        and1.connect(0, input1, BooleanInput.OUTPUT);
        and1.connect(1, input2, BooleanInput.OUTPUT);
        and2.connect(0, input3, BooleanInput.OUTPUT);
        and2.connect(1, and1, And.OUTPUT);

        // Connect the first displayer (the second one is already connected via the constructor)
        displayer1.connect(SystemOut.INPUT, and1, And.OUTPUT);

        // Set the second displayer as the circuit's output
        nodeSystem.setRoot(displayer2);

        System.out.println("Displayer 1 output:"); // Result should be true
        nodeSystem.run(displayer1); // Run with the first displayer as output
        System.out.println("Displayer 2 output:"); // Result should be false
        nodeSystem.run(); // Run with the second displayer as output
    }
}
```

