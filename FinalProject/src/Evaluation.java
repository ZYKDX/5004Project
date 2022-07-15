public class Evaluation {
    public static int[][] getBestMaskPattern(int[][][] matrices) {
        return new int[][]{};
    }

    private static int calculatePenaltyScore(int[][] matrix) {
        return 0;
    }

    private static int calculatePenaltyRule1(int[][] matrix) {
        return 0;
    }

    static int calculatePenaltyRule2(int[][] matrix) {
        int count = 0;
        for (int i = 0; i < matrix.length - 1; i++)
        {
            for (int j = 0; j < matrix[0].length - 1; j++)
            {
                if (matrix[i][j] == matrix[i+1][j]
                        && matrix[i][j] == matrix[i][j+1]
                        &&matrix[i][j] == matrix[i+1][j+1])
                {
                    count++;
                }
            }
        }
        return count * 3;
    }

    private static int calculatePenaltyRule3(int[][] matrix)
    {
        int result = 0;
        for(int i=0; i<29; i++)
        {
            for(int j=0; j<=18; j++)
            {
                String tested1 = "", tested2= "";
                for(int k = 0; k < 11; k++)
                {
                    tested1 = tested1 + matrix[i][j + k];
                    tested2 = tested2 + matrix[j + k][i];
                }
                if(tested1 == "00001011101" || tested1 == "10111010000")
                {
                    result += 40;
                }
                if(tested2 == "00001011101" || tested2 == "10111010000")
                {
                    result+=40;
                }
            }
        }
        return result;
    }
    private static int calculatePenaltyRule4(int[][] matrix)
    {
        int total = 29 * 29;
        int whiteNum = 0, blackNum = 0;
        for(int i = 0; i<20; i++)
        {
            for(int j = 0; j < 29; j++)
            {
                if(matrix[i][j] == 0)
                {
                    whiteNum ++;
                }
                else
                {
                    blackNum ++;
                }
            }
        }
        double darkRatio = ((double)blackNum / (double)total) * 100;
        int pre, nex;
        pre = 5 * ((int)darkRatio / 5);
        nex = pre + 5;
        pre = Math.abs(pre - 50) / 5;
        nex = Math.abs(nex - 50) / 5;
        return Math.min(pre, nex) * 10;
    }
}
