public class Mask5 extends Mask {
    @Override
    public  boolean shouldToggle(int row, int column) {
        return ((row * column) % 2) + ((row * column) % 3) == 0;
    }
}
