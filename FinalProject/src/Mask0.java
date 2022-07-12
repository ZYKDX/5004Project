public class Mask0
{
    public static int[][] masked0()
    {
        // mask rule: (row+column) mod 2 == 0
        Data.generateData();
        int[][] result = new int[29][29];
        for(int i=0; i<29; i++)
        {
            for(int j=0; j<29; j++)
            {
                result[i][j] = Data.DATA[i][j];
            }
        }
        // first case
        int row = 28, column = 28;
        if((row + column) % 2 == 0)
        {
            result[row][column] = change(result[row][column]);
        }
        while(Data.next(row,column) != null)
        {
            int[] newPosition = Data.next(row,column);
            row = newPosition[0];
            column = newPosition[1];
            if((row+column)%2==0)
            {
                result[row][column] = change(result[row][column]);
            }
        }
        // type information: 111011111000100
        int[] typeInfo = {1,1,1,0,1,1,1,1,1,0,0,0,1,0,0};
        setTypeInfo(result, typeInfo);
        return result;
    }
    private static void setTypeInfo(int[][] result, int[] typeInfo)
    {
        for(int i=0; i<=5; i++) {result[8][i]=typeInfo[i];}
        for(int i=5; i>=0; i--){result[i][8]=typeInfo[14-i];}
        for(int i=21; i<=28; i++){result[8][i] = typeInfo[i-14];}
        for(int i=22; i<=28; i++){result[i][8] = typeInfo[28-i];}
        result[7][8]=typeInfo[8];
        result[8][8]=typeInfo[7];
        result[8][7]=typeInfo[6];
    }
    private static int change(int i)
    {
        if(i==0) return 1;
        else if(i==1) return 0;
        return -1;
    }
}
