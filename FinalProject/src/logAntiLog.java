public class logAntiLog
{
    public static int expToInt(int a)
    {
        int[] receiver = new int[256];
        receiver[0] = 1;
        for(int i=1; i<256; i++)
        {
            receiver[i] = 2 * receiver[i-1];
            if(receiver[i] > 255)
            {
                receiver[i] = receiver[i] ^ 285;
            }
        }
        return receiver[a];
    }

    /**
     * a = 1,2,...,255
     * @param a
     * @return
     */
    public static int intToExp(int a)
    {
        for(int i=0; i<256; i++)
        {
            if(expToInt(i) == a)
            {
                return i;
            }
        }
        return 0;
    }
}
