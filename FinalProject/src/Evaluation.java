public class Evaluation {
    public static int[][] getBestMaskPattern(int[][][] matrices) {
        int minScore = Integer.MAX_VALUE;
        int[][] result = matrices[0];
        for(int[][] matrix: matrices) {
            int score = calculatePenaltyScore(matrix);
            if (score <= minScore) {
                minScore = score;
                result = matrix;
            }
        }
        return result;
    }

    private static int calculatePenaltyScore(int[][] matrix) {
        return calculatePenaltyRule1(matrix) + calculatePenaltyRule2(matrix)
                + calculatePenaltyRule3(matrix) + calculatePenaltyRule4(matrix);
    }

    static int calculatePenaltyRule1(int[][] matrix) {
       int result = 0;
       // horizontal
       for (int i = 0; i < matrix.length; i++)
       {
           int j = 0;
           while (j < matrix[i].length - 5)
           {
               if (matrix[i][j] == matrix[i][j+1]
                       && matrix[i][j] == matrix[i][j+2]
                       && matrix[i][j] == matrix[i][j+3]
                       && matrix[i][j] == matrix[i][j+4])
               {
                   result = result + 3;
                   j = j + 5;
                   while(j < matrix[i].length && matrix[i][j-5] == matrix[i][j])
                   {
                       result++;
                       j++;
                   }
               } else
               {
                   j++;
               }
           }
       }
        // Vertical
        for (int j = 0; j < matrix[0].length; j++)
        {
            int i = 0;
            while (i < matrix.length-5)
            {
                if (matrix[i][j] == matrix[i+1][j]
                        && matrix[i][j] == matrix[i+2][j]
                        && matrix[i][j] == matrix[i+3][j]
                        && matrix[i][j] == matrix[i+4][j])
                {
                    result = result + 3;
                    i= i + 5;
                    while(i < matrix.length && matrix[i-5][j] == matrix[i][j])
                    {
                        result++;
                        i++;
                    }
                } else
                {
                    i++;
                }
            }
        }
       return result;
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

    static int calculatePenaltyRule3(int[][] matrix)
    {
        int result = 0;
        // Horizontal
        for(int i=0; i<matrix.length; i++)
        {
            for(int j=0; j<=matrix[0].length-11; j++)
            {
                String tested = "";
                for(int k = 0; k < 11; k++)
                {
                    tested = tested + matrix[i][j + k];
                }
                if(tested.equals("00001011101") || tested.equals("10111010000"))
                {
                    result += 40;
                }
            }
        }
        // Vertical
        for(int j=0; j<matrix[0].length; j++)
        {
            for(int i=0; i<=matrix.length-11; i++)
            {
                String tested = "";
                for(int k = 0; k < 11; k++)
                {
                    tested = tested + matrix[i + k][j];
                }
                if(tested.equals("00001011101") || tested.equals("10111010000"))
                {
                    result+=40;
                }
            }
        }
        return result;
    }
    static int calculatePenaltyRule4(int[][] matrix)
    {
        int total = matrix.length * matrix[0].length;
        int whiteNum = 0, blackNum = 0;
        for(int i = 0; i< matrix.length; i++)
        {
            for(int j = 0; j < matrix[0].length; j++)
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
