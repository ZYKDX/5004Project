public class Mask6 extends Mask {
    @Override
    public boolean shouldToggle(int row, int column) {
        return ((row * column) % 2 + (row * column) % 3) % 2 == 0;
    }
}
