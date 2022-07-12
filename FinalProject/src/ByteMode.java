public class ByteMode
{
    public static int[] encode(char c)
    {
        String s = Integer.toBinaryString(c);
        s = ("00000000" + s).substring(s.length());
        int[] encoded = new int[8];
        for (int i = 0; i < 8; i++) {
            encoded[i] = Character.getNumericValue(s.charAt(i));
        }
        return encoded;
    }
}
