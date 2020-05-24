package jns.node.math;

import jns.system.*;
import jns.type.MatrixData;
import jns.type.TypeSystem;

public class Multiply extends Node {
    public static final String INPUT_1 = "Input 1";
    public static final String INPUT_2 = "Input 2";
    public static final String OUTPUT = "Output";

    public Multiply(NodeSystem nodeSystem) {
        super(
                nodeSystem,
                new IO[]{new IO(INPUT_1, TypeSystem.Type.MATRIX), new IO(INPUT_2, TypeSystem.Type.MATRIX)},
                new IO[]{new IO(OUTPUT, TypeSystem.Type.MATRIX)},
                new IO[0]
        );
    }

    @Override
    public Data<?> out(IO requested) {
        Double[][] matrix1 = (Double[][]) in(INPUT_1).get();
        Double[][] matrix2 = (Double[][]) in(INPUT_2).get();

        if(matrix1.length == 0 || matrix1.length != matrix2.length){
            throw new NodeRuntimeException("Invalid matrix sizes: " + matrix1.length + " and " + matrix2.length);
        }
        Double[][] result = new Double[matrix1.length][matrix1[0].length];

        for (int x = 0; x < result.length; x++) {
            if(matrix1[x].length != result[0].length || matrix2[x].length != result[0].length){
                throw new NodeRuntimeException("Invalid matrices dimensions for row " + x + ": " +
                        matrix1[x].length + " and " + matrix2[x].length + " while should be: " + result[x].length);
            }

            for (int y = 0; y < result[x].length; y++) {
                result[x][y] = matrix1[x][y] * matrix2[x][y];
            }
        }

        return new MatrixData(result);
    }
}
