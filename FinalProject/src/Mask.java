public abstract class Mask {
     int[][] getMaskedMatrix(int[][] matrix) {
        int[][] result = new int[29][29];
        for(int i=0; i<29; i++)
        {
            for(int j=0; j<29; j++)
            {
                result[i][j] = matrix[i][j];
            }
        }
        int[] location = {28,28};
        for(int i=0; i<566; i++) {
            location = Data.next(location[0], location[1]);
            int row = location[0];
            int column = location[1];
            if (shouldToggle(row, column)) {
                result[row][column] = (result[row][column] + 1) % 2;
            } else {
                result[row][column] = result[row][column];
            }
        }
        return result;
    }
    public static void setTypeInfo(int[][] result, int[] typeInfo)
    {
        for(int i=0; i<=5; i++) {result[8][i]=typeInfo[i];}
        for(int i=5; i>=0; i--){result[i][8]=typeInfo[14-i];}
        for(int i=21; i<=28; i++){result[8][i] = typeInfo[i-14];}
        for(int i=22; i<=28; i++){result[i][8] = typeInfo[28-i];}
        result[7][8]=typeInfo[8];
        result[8][8]=typeInfo[7];
        result[8][7]=typeInfo[6];
    }
    abstract boolean shouldToggle(int row, int column);
}
