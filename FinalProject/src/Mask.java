public abstract class Mask {
     int[][] getMaskedMatrix(int[][] matrix) {
        int[][] result = new int[29][29];
        int[] location = {28,28};
        for(int i=0; i<566; i++) {
            location = Data.next(location[0], location[1]);
            int row = location[0];
            int column = location[1];
            if (shouldToggle(row, column)) {
                result[row][column] = (matrix[row][column] + 1) % 2;
            } else {
                result[row][column] = matrix[row][column];
            }
        }
        return result;
    }
    abstract boolean shouldToggle(int row, int column);
}

