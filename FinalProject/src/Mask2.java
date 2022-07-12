public class Mask2 extends Mask {
    @Override
    public boolean shouldToggle(int row, int column) {
        return column % 3 == 0;
    }
}
