public class ByteMode
{
    public static int[] encode(char a)
    {
        int result[] = new int[8];
        switch(a)
        {
            case ' ': result = hexToBinary("20"); break;
            case '!': result = hexToBinary("21"); break;
            case '"': result = hexToBinary("22"); break;
            case '#': result = hexToBinary("23"); break;
            case '$': result = hexToBinary("24"); break;
            case '%': result = hexToBinary("25"); break;
            case '&': result = hexToBinary("26"); break;
            case '\'': result = hexToBinary("27"); break;
            case '(': result = hexToBinary("28"); break;
            case ')': result = hexToBinary("29"); break;
            case '*': result = hexToBinary("2A"); break;
            case '+': result = hexToBinary("2B"); break;
            case ',': result = hexToBinary("2C"); break;
            case '-': result = hexToBinary("2D"); break;
            case '.': result = hexToBinary("2E"); break;
            case '/': result = hexToBinary("2F"); break;
            case '0': result = hexToBinary("30"); break;
            case '1': result = hexToBinary("31"); break;
            case '2': result = hexToBinary("32"); break;
            case '3': result = hexToBinary("33"); break;
            case '4': result = hexToBinary("34"); break;
            case '5': result = hexToBinary("35"); break;
            case '6': result = hexToBinary("36"); break;
            case '7': result = hexToBinary("37"); break;
            case '8': result = hexToBinary("38"); break;
            case '9': result = hexToBinary("39"); break;
            case ':': result = hexToBinary("3A"); break;
            case ';': result = hexToBinary("3B"); break;
            case '<': result = hexToBinary("3C"); break;
            case '=': result = hexToBinary("3D"); break;
            case '>': result = hexToBinary("3E"); break;
            case '?': result = hexToBinary("3F"); break;
            case '@': result = hexToBinary("40"); break;
            case 'A': result = hexToBinary("41"); break;
            case 'B': result = hexToBinary("42"); break;
            case 'C': result = hexToBinary("43"); break;
            case 'D': result = hexToBinary("44"); break;
            case 'E': result = hexToBinary("45"); break;
            case 'F': result = hexToBinary("46"); break;
            case 'G': result = hexToBinary("47"); break;
            case 'H': result = hexToBinary("48"); break;
            case 'I': result = hexToBinary("49"); break;
            case 'J': result = hexToBinary("4A"); break;
            case 'K': result = hexToBinary("4B"); break;
            case 'L': result = hexToBinary("4C"); break;
            case 'M': result = hexToBinary("4D"); break;
            case 'N': result = hexToBinary("4E"); break;
            case 'O': result = hexToBinary("4F"); break;
            case 'P': result = hexToBinary("50"); break;
            case 'Q': result = hexToBinary("51"); break;
            case 'R': result = hexToBinary("52"); break;
            case 'S': result = hexToBinary("53"); break;
            case 'T': result = hexToBinary("54"); break;
            case 'U': result = hexToBinary("55"); break;
            case 'V': result = hexToBinary("56"); break;
            case 'W': result = hexToBinary("57"); break;
            case 'X': result = hexToBinary("58"); break;
            case 'Y': result = hexToBinary("59"); break;
            case 'Z': result = hexToBinary("5A"); break;
            case '[': result = hexToBinary("5B"); break;
            case '\\': result = hexToBinary("5C"); break;
            case ']': result = hexToBinary("5D"); break;
            case '^': result = hexToBinary("5E"); break;
            case '_': result = hexToBinary("5F"); break;
            case '`': result = hexToBinary("60"); break;
            case 'a': result = hexToBinary("61"); break;
            case 'b': result = hexToBinary("62"); break;
            case 'c': result = hexToBinary("63"); break;
            case 'd': result = hexToBinary("64"); break;
            case 'e': result = hexToBinary("65"); break;
            case 'f': result = hexToBinary("66"); break;
            case 'g': result = hexToBinary("67"); break;
            case 'h': result = hexToBinary("68"); break;
            case 'i': result = hexToBinary("69"); break;
            case 'j': result = hexToBinary("6A"); break;
            case 'k': result = hexToBinary("6B"); break;
            case 'l': result = hexToBinary("6C"); break;
            case 'm': result = hexToBinary("6D"); break;
            case 'n': result = hexToBinary("6E"); break;
            case 'o': result = hexToBinary("6F"); break;
            case 'p': result = hexToBinary("70"); break;
            case 'q': result = hexToBinary("71"); break;
            case 'r': result = hexToBinary("72"); break;
            case 's': result = hexToBinary("73"); break;
            case 't': result = hexToBinary("74"); break;
            case 'u': result = hexToBinary("75"); break;
            case 'v': result = hexToBinary("76"); break;
            case 'w': result = hexToBinary("77"); break;
            case 'x': result = hexToBinary("78"); break;
            case 'y': result = hexToBinary("79"); break;
            case 'z': result = hexToBinary("7A"); break;
            case '{': result = hexToBinary("7B"); break;
            case '|': result = hexToBinary("7C"); break;
            case '}': result = hexToBinary("7D"); break;
            case '~': result = hexToBinary("7E"); break;
        }
        return result;
    }

    /**
     * convert 2 digit hex (string) to 8 digit binary array
     * @param a
     * @return
     */
    private static int[] hexToBinary(String a)
    {
        int[] result = new int[8];
        switch(a.charAt(0))
        {
            case '2':
                result[0] = 0;result[1] = 0;result[2] = 1;result[3] = 0;
                break;
            case '3':
                result[0] = 0;result[1] = 0;result[2] = 1;result[3] = 1;
                break;
            case '4':
                result[0] = 0;result[1] = 1;result[2] = 0;result[3] = 0;
                break;
            case '5':
                result[0] = 0;result[1] = 1;result[2] = 0;result[3] = 1;
                break;
            case '6':
                result[0] = 0;result[1] = 1;result[2] = 1;result[3] = 0;
                break;
            case '7':
                result[0] = 0;result[1] = 1;result[2] = 1;result[3] = 1;
                break;
        }
        switch(a.charAt(1))
        {
            case '0':
                result[4] = 0;result[5] = 0;result[6] = 0;result[7] = 0;break;
            case '1':
                result[4] = 0;result[5] = 0;result[6] = 0;result[7] = 1;break;
            case '2':
                result[4] = 0;result[5] = 0;result[6] = 1;result[7] = 0;break;
            case '3':
                result[4] = 0;result[5] = 0;result[6] = 1;result[7] = 1;break;
            case '4':
                result[4] = 0;result[5] = 1;result[6] = 0;result[7] = 0;break;
            case '5':
                result[4] = 0;result[5] = 1;result[6] = 0;result[7] = 1;break;
            case '6':
                result[4] = 0;result[5] = 1;result[6] = 1;result[7] = 0;break;
            case '7':
                result[4] = 0;result[5] = 1;result[6] = 1;result[7] = 1;break;
            case '8':
                result[4] = 1;result[5] = 0;result[6] = 0;result[7] = 0;break;
            case '9':
                result[4] = 1;result[5] = 0;result[6] = 0;result[7] = 1;break;
            case 'A':
                result[4] = 1;result[5] = 0;result[6] = 1;result[7] = 0;break;
            case 'B':
                result[4] = 1;result[5] = 0;result[6] = 1;result[7] = 1;break;
            case 'C':
                result[4] = 1;result[5] = 1;result[6] = 0;result[7] = 0;break;
            case 'D':
                result[4] = 1;result[5] = 1;result[6] = 0;result[7] = 1;break;
            case 'E':
                result[4] = 1;result[5] = 1;result[6] = 1;result[7] = 0;break;
            case 'F':
                result[4] = 1;result[5] = 1;result[6] = 1;result[7] = 1;break;
        }
        return result;
    }
}
