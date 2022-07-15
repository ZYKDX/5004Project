import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EvaluationTest {
    @Test
    void calculatePenaltyRule2() {
        assertEquals(3, Evaluation.calculatePenaltyRule2(new int[][]{{1,1,0}, {1,1,0}, {0,0,0}}));
        assertEquals(6, Evaluation.calculatePenaltyRule2(new int[][]{{1,0,0}, {1,0,0}, {0,0,0}}));
    }
}
