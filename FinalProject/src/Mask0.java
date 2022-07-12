public class Mask0 extends Mask {
    @Override
    public boolean shouldToggle(int row, int column) {
        return (row + column) % 2 == 0;
    }
}
