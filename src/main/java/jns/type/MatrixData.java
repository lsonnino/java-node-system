package jns.type;

import jns.system.Data;
import jns.system.DataException;

/**
 * Generic double array Data type
 * It is based on Java's Double Arrays object
 */
public class MatrixData extends Data<Double[][]> {
    public MatrixData(){
        this(0, 0);
    }
    public MatrixData(int width, int height){
        this(new double[width][height]);
    }
    public MatrixData(int width, int height, double initialValue){
        this(new double[width][height]);

        Double[][] data = get();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                data[x][y] = initialValue;
            }
        }
    }
    public MatrixData(double[][] initialValue){
        super(Double[][].class, toObject(initialValue));
    }

    public static Double[][] toObject(double[][] data){
        if(data.length == 0){
            return new Double[0][];
        }

        Double[][] matrix = new Double[data.length][data[0].length];

        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[x].length; y++) {
                matrix[x][y] = data[x][y];
            }
        }

        return matrix;
    }

    @Override
    public <E extends Data> Double[][] convert(E obj) {
        try{
            Double[] value = new ArrayData().convert(obj);

            // Conversion successful
            return new Double[][]{value};
        }
        catch(DataException ignore){} // Try something else

        return super.convert(obj);
    }
}
