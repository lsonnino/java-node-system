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



## Node

A node is a computation unit taking inputs and using properties to produce an output. Inputs, outputs and properties are defined by an `IO` containing an unique id (automatically sequentially assigned. Id's are unique between inputs, outputs or properties for a single node), a name (unique names for a single nodes are recomended to propely identify an input/output/property by it's name) and a *data type*.

JNS comes with some basic nodes (defined in `jns.node`) including:

* `BooleanInput`: has no inputs, give on it's single output the value it has stored in his single property
* `RandomInput`: has no inputs, give on it's single output a random value between 0 and 1
* `And`: an $n$ input AND logic gate
* `SystemOut` prints on his standard output it's single input, has no output nor properties

