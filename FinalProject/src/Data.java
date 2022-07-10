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

    public static void main()
    {
        setPositionDetection(3,3);
        setPositionDetection(3,21);
        setPositionDetection(21,3);
        setTimingPatterns();
        setAlignmentPattern();
    }

}
