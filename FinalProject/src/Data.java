import java.util.Arrays;

public class Data
{
    public static int DATA[][] = new int[29][29];

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
        else if(row==3 && column==25)  // upper right
        {
            for(int i=0; i<=7; i++)
            {
                DATA[i][21]=0;
                DATA[7][21+i]=0;
            }
        }
        else if(row==25 && column==3)  // lower left
        {
            for(int i=0; i<=7; i++)
            {
                DATA[21][i]=0;
                DATA[21+i][7]=0;
            }
        }
    }

    /**
     * Fill the DATA array with 2 Timing Pattern strips
     * This pattern is fixed
     */
    public static void setTimingPatterns()
    {
        for(int i=8; i<=20; i++)
        {
            if(i%2==0)
            {
                DATA[6][i]=1;
                DATA[i][6]=1;
            }
            else
            {
                DATA[6][i]=0;
                DATA[i][6]=0;
            }
        }
    }

    /**
     * Fill the DATA array with 1 alignment pattern
     * This pattern is fixed
     */
    public static void setAlignmentPattern()
    {
        for(int i=20; i<=24; i++)
        {
            for(int j=20; j<=24; j++)
            {
                DATA[i][j]=1;
            }
        }
        for(int i=21; i<=23; i++)
        {
            DATA[21][i]=0;
            DATA[23][i]=0;
        }
        DATA[22][21]=0;
        DATA[22][23]=0;
    }
    public static void setDarkModule()
    {
        DATA[21][8]=1;
    }
    /**
     * Generate data code
     * Byte Mode indicator + String length indicator + String encoding + pad bits
     * Total length is 34 * 8 = 272 bits
     * @param s
     * @return
     */
    // tested
    public static int[] encodeData(String s)
    {
        int[] result = new int[440];
        int index = -1; // where are we?
        int len = s.length();
        int[] byteModeIndicator = {0,0,1,0};
        // byteModeIndicator, 4 bits - 0010 for Byte Mode

        index = arrayAppend(result, index, byteModeIndicator);

        // string length indicator 8 digits
        index = arrayAppend(result, index, decimalToBinary(len));
        //then encode the string
        for(int i=0; i<len; i++)
        {
            index = arrayAppend(result, index, ByteMode.encode(s.charAt(i)));
        }
        // pad bits?
        int[] pad0 = {0,0,0,0};
        index = arrayAppend(result, index, pad0);
        int[] pad1 = {1,1,1,0,1,1,0,0};
        int[] pad2 = {0,0,0,1,0,0,0,1};
        while(true)
        {
            if(index == 439) break;
            else
            {
                index = arrayAppend(result, index, pad1);
            }
            if(index == 439) break;
            else
            {
                index = arrayAppend(result, index, pad2);
            }
        }
        return result;
    }
    public static int[] getECcode(String s)
    {
        int[] encoded = encodeData(s);
        int[] result = new int[567];
        // put the data codewords into result
        for(int i=0; i<440; i++)
        {
            result[i] = encoded[i];
        }
        // generate EC codewords and put it in result
        // EC has 15 bytes

        // initialize message binomial
        int[] MBinomial;
        MBinomial = new int[55];
        for(int i = 0; i<55; i++)
        {
            MBinomial[i] = binaryToDecimal(Arrays.copyOfRange(encoded, i*8, i*8+8));
            // int form
        }
        // initialize generator binomial.fixed
        int[] GBinomial = {0,8,183,61,91,202,37,51,58,58,237,140,124,5,99,105};
        for(int i = 0; i<55; i++)
        {  // repeat the operation for 55 times
            int exp = logAntiLog.intToExp(MBinomial[0]);
            int[] GCopy = new int[16];
            for(int j=0; j<16; j++)
            {
                GCopy[j] = GBinomial[j];
            }
            for(int j=0; j<16; j++)
            {
                GCopy[j] += exp;
                GCopy[j] = GCopy[j] % 255;
                GCopy[j] = logAntiLog.expToInt(GCopy[j]); // convert back to int
            }
            int MLength = Math.max(MBinomial.length, GBinomial.length);
            int[] XOR = new int[MLength];
            if(GCopy.length <= MBinomial.length)
            {
                for(int m=0; m< GCopy.length; m++)
                {
                    XOR[m] = GCopy[m] ^ MBinomial[m];
                }
                for(int n= GCopy.length; n<MLength; n++)
                {
                    XOR[n] = MBinomial[n] ^ 0;
                }
            }
            else
            {
                for(int m=0; m< MBinomial.length; m++)
                {
                    XOR[m] = GCopy[m] ^ MBinomial[m];
                }
                for(int n= MBinomial.length; n<MLength; n++)
                {
                    XOR[n] = GCopy[n] ^ 0;
                }
            }
            MBinomial = Arrays.copyOfRange(XOR, 1, XOR.length);
        }
        // now the 15 numbers in MBinomial will be the EC codewords
        for(int i=0; i<15; i++)
        {
            int[] ECcode = decimalToBinary(MBinomial[i]);
            for(int j=440+i*8; j<440+i*8+8; j++)
            {
                result[j] = ECcode[j-440-i*8];
            }
        }
        // remainder: append 7 0s to the end
        for(int i=560; i<567; i++)
        {
            result[i] = 0;
        }
        return result;
    }



    /**
     * helper function, append target array to result array
     * @param result
     * @param index
     * @param target
     * @return
     */
    private static int arrayAppend(int[] result, int index, int[] target)
    {
        for(int i=0; i<target.length; i++)
        {
            result[index+i+1] = target[i];
        }
        return index+target.length;
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
    private static int binaryToDecimal(int[] s)
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

    /**
     * i j is the location of current pixel
     * return the location of next pixel
     * @param i
     * @param j
     * @return
     */
    private static int[] next(int i, int j)
    {
        int[] result = new int[2];
        if(j>=8 && j<=28 && j%2==0) {result[0] = i; result[1] = j-1;}
        if(j>=1 && j<=5 && j%2==1) {result[0]=i; result[1]=j-1;}
        if(j==27)
        {
            if(i==9) {result[0] = i; result[1] = j-1;}
            else {result[0]=i-1; result[1]=j+1;}
        }
        if(j==25)
        {
            if(i==28) {result[0]=i; result[1]=j-1;}
            else {result[0]=i+1; result[1]=j+1;}
        }
        if(j==23)
        {
            if(i==25) {result[0]=19;result[1]=j+1;}
            else if(i==9) {result[0]=i;result[1]=j-1;}
            else {result[0]=i-1;result[1]=j+1;}
        }
        if(j==21)
        {
            if(i==19) {result[0]=25;result[1]=j+1;}
            else if(i==28) {result[0]=i;result[1]=j-1;}
            else{result[0]=i+1; result[1]=j+1;}
        }
        if(j==19)
        {
            if(i>=21 && i<=25) {result[0]=i-1;result[1]=j;}
            else if(i==7) {result[0]=5;result[1]=j+1;}
            else if(i==0) {result[0]=i;result[1]=j-1;}
            else {result[0]=i-1;result[1]=j+1;}
        }
        if(j==17||j==13)
        {
            if(i==5) {result[0]=7;result[1]=j+1;}
            else if(i==28) {result[0]=i;result[1]=j-1;}
            else {result[0]=i+1;result[1]=j+1;}
        }
        if(j==15||j==11)
        {
            if(i==7) {result[0]=5;result[1]=j+1;}
            else if(i==0) {result[0]=i;result[1]=j-1;}
            else {result[0]=i-1;result[1]=j+1;}
        }
        if(j==9)
        {
            if(i==5) {result[0]=7;result[1]=j+1;}
            else if(i==28){result[0]=20;result[1]=8;}
            else{result[0]=i+1;result[1]=j+1;}
        }
        if(j==7)
        {
            if(i==9) {result[0]=i;result[1]=5;}
            else {result[0]=i-1;result[1]=j+1;}
        }
        if(j==4)
        {
            if(i==20) {result[0]=i;result[1]=j-1;}
            else {result[0]=i+1;result[1]=j+1;}
        }
        if(j==2)
        {
            if(i==9) {result[0]=i;result[1]=j-1;}
            else {result[0]=i-1;result[1]=j+1;}
        }
        if(j==0)
        {
            if(i==20) {return null;}
            else {result[0]=i+1;result[1]=j+1;}
        }
        return result;
    }
    public static void generateData()
    {
        setPositionDetection(3,3);
        setPositionDetection(3,25);
        setPositionDetection(25,3);
        setTimingPatterns();
        setAlignmentPattern();
        setDarkModule();
        int[] target = getECcode("Hello");
        DATA[28][28] = target[0];
        int[] location = {28,28};
        for(int i=0; i<566; i++)
        {
            location = next(location[0], location[1]);
            DATA[location[0]][location[1]] = target[i+1];
        }
    }
    public static void main(String[] args)
    {

    }

}
