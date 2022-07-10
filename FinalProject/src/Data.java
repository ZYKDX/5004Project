
public class Data
{
    public static int DATA[][] = new int[25][25];

    /**
     * Fill the DATA array with a position detection pattern, center is (row, column)
     * @param row row of the center point
     * @param column column of the center point
     */
    public static void setPositionDetection(int row, int column)
    {
        for(int i=row-3; i<=row+3; i++)
        {
            for(int j=column-3; j<=column+3; j++)
            {
                DATA[i][j]=1;
            }
        }
        for(int i=column-2; i<=column+2; i++)
        {
            DATA[row-2][i] = 0;
            DATA[row+2][i] = 0;
        }
        for(int i=row-2; i<=row+2; i++)
        {
            DATA[i][column-2] = 0;
            DATA[i][column+2] = 0;
        }
        if(row==3 && column==3)  //upper left
        {
            for(int i=0; i<=7; i++)
            {
                DATA[7][i] = 0;
                DATA[i][7] = 0;
            }
        }
        else if(row==3 && column==21)  // upper right
        {
            for(int i=0; i<=7; i++)
            {
                DATA[i][17]=0;
                DATA[7][17+i]=0;
            }
        }
        else if(row==21 && column==3)  // lower left
        {
            for(int i=0; i<=7; i++)
            {
                DATA[17][i]=0;
                DATA[17+i][7]=0;
            }
        }
    }

    /**
     * Fill the DATA array with 2 Timing Pattern strips
     * This pattern is fixed
     */
    public static void setTimingPatterns()
    {
        for(int i=8; i<=16; i++)
        {
            if(i%2==0)
            {
                DATA[7][i]=1;
                DATA[i][7]=1;
            }
            else
            {
                DATA[7][i]=0;
                DATA[i][7]=0;
            }
        }
    }

    /**
     * Fill the DATA array with 1 alignment pattern
     * This pattern is fixed
     */
    public static void setAlignmentPattern()
    {
        for(int i=15; i<=19; i++)
        {
            for(int j=15; j<=19; j++)
            {
                DATA[i][j]=1;
            }
        }
        for(int i=16; i<=18; i++)
        {
            DATA[16][i]=0;
            DATA[18][i]=0;
        }
        DATA[17][16]=0;
        DATA[17][18]=0;
    }

    /**
     * Generate data code
     * Byte Mode indicator + String length indicator + String encoding + pad bits
     * Total length is 34 * 8 = 272 bits
     * @param s
     * @return
     */
    public static int[] encodeData(String s)
    {
        int[] result = new int[272];
        int index = 0; // where are we?
        int len = s.length();
        int[] byteModeIndicator = {0,0,1,0};
        // byteModeIndicator, 4 bits - 0010 for Byte Mode
        for(int i=0; i<4; i++)
        {
            result[i] = byteModeIndicator[i];
        }
        index = 3;
        // string length indicator 8 digits
        for(int i=0; i<8; i++)
        {
            result[i+4] = decimalToBinary(len)[i];
        }
        index = 11;
        //then encode the string
        for(int i=0; i<len; i++)
        {
            for(int j=index+1; j<index+9; j++)
            {
                result[j] = ByteMode.encode(s.charAt(i))[j-index-1];
            }
            index += 8;
        }

        // pad bits?

        return result;
    }

    /**
     * Convert a decimal to 8-digit binary array
     * @return
     */
    private static int[] decimalToBinary(int s)
    {
        int[] result = new int[8];
        int power = 128;
        for(int i=0; i<8; i++)
        {
            result[i] = s / power;
            s %= power;
            power /= 2;
        }
        return result;
    }
    /**
     * convert 8-digit binary to int
     * @return
     */
    private int binaryToDecimal(int[] s)
    {
        int result = 0;
        int power = 1;
        for(int i=7; i>=0; i--)
        {
            result += s[i] * power;
            power *= 2;
        }
        return result;
    }

    public static void generateData()
    {
        setPositionDetection(3,3);
        setPositionDetection(3,21);
        setPositionDetection(21,3);
        setTimingPatterns();
        setAlignmentPattern();

    }
    public static void main(String[] args)
    {

    }

}
