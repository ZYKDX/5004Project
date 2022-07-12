import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ByteModeTest {
    @Test
    void encode() {
        assertArrayEquals(ByteMode.encode(' '), new int[]{0,0,1,0,0,0,0,0});
        assertArrayEquals(ByteMode.encode('0'), new int[]{0,0,1,1,0,0,0,0});
        assertArrayEquals(ByteMode.encode('a'), new int[]{0,1,1,0,0,0,0,1});
        assertArrayEquals(ByteMode.encode('A'), new int[]{0,1,0,0,0,0,0,1});
        assertArrayEquals(ByteMode.encode('~'), new int[]{0,1,1,1,1,1,1,0});
    }
}