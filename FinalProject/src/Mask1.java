public class Mask1 extends Mask {
    @Override
    public boolean shouldToggle(int row, int column) {
        return row % 2 == 0;
    }
}
