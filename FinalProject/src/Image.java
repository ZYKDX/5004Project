import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Image extends JFrame
{
    public static final int WIDTH = 900;
    public static final int HEIGHT = 1000;
    public Image()
    {
        super("QR code generator");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        JPanel infoArea = new JPanel();
        infoArea.setLayout(new GridLayout(1,2));
        JLabel infoLabel = new JLabel("Input your message, get a QR code, for free.");
        infoArea.add(infoLabel);
        JTextField infoInput = new JTextField();
        infoArea.add(infoInput);
        add(infoArea, BorderLayout.NORTH);

        JPanel imageArea = new JPanel();
        imageArea.setOpaque(true);
        imageArea.setLayout(new GridLayout(25,25));
        JPanel cell;
        Data.main();
        for(int i=0; i<25; i++)
        {
            for(int j=0; j<25; j++)
            {
                cell = new JPanel();
                if(Data.DATA[i][j]==0)
                {

                    cell.setBackground(Color.WHITE);
                }
                else
                {
                    cell.setBackground(Color.BLACK);
                }
                imageArea.add(cell);
            }

        }
        add(imageArea, BorderLayout.CENTER);
    }
}
