public class Mask4 extends Mask{
    @Override
    public boolean shouldToggle(int row, int column) {
        return (Math.floor((row / 2)) + Math.floor(column / 3)) % 2 == 0;
    }
}
