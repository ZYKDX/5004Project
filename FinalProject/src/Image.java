import javax.swing.*;
//import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Image extends JFrame implements ActionListener
{
    public static final int WIDTH = 900;
    public static final int HEIGHT = 1000;
    public String entered;
    public JTextField infoInput;
    public JPanel infoArea;
    public JLabel infoLabel;
    public JPanel imageContainer;
    public JPanel imageArea;
    //public JPanel cell;
    public int[][] data;
    public JPanel[][] cells;
    public Image()
    {
        super("QR code generator");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        infoArea = new JPanel();
        infoArea.setLayout(new GridLayout(1,3));
        infoLabel = new JLabel("Input your message, get a QR code.");
        infoArea.add(infoLabel);
        infoInput = new JTextField("This is a test string");
        entered = "www.google.com";
        infoArea.add(infoInput);
        JButton submit = new JButton("Submit");
        submit.addActionListener(this);
        infoArea.add(submit);
        add(infoArea, BorderLayout.NORTH);
        imageContainer = new JPanel();
        imageContainer.setLayout(new BorderLayout());
        imageArea = new JPanel();
        imageContainer.add(imageArea);
        cells = new JPanel[29][29];
        for(int i=0; i<29; i++)
        {
            for(int j=0; j<29; j++)
            {
                cells[i][j] = new JPanel();
            }
        }
        add(imageContainer, BorderLayout.CENTER);

    }
    public void actionPerformed(ActionEvent e)
    {
        entered = infoInput.getText();
        imageContainer.remove(imageArea);
        imageArea = new JPanel();
        imageArea.setOpaque(true);
        imageArea.setLayout(new GridLayout(29,29));
        data = Data.generateData(entered);
        for(int i=0; i<29; i++)
        {
            for(int j=0; j<29; j++)
            {
                if(data[i][j]==0)
                {
                    cells[i][j].setBackground(Color.WHITE);
                }
                else
                {
                    cells[i][j].setBackground(Color.BLACK);
                }
                imageArea.add(cells[i][j]);
            }
        }
        imageContainer.add(imageArea);
    }

}
