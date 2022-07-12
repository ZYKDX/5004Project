public class Mask3 extends Mask {
    @Override
    public boolean shouldToggle(int row, int column) {
        return (row + column) % 3 == 0;
    }
}
