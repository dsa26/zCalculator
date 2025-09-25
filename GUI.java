import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;

public class GUI {
    private JFrame frame;

    public GUI() {
        frame = new JFrame("Epoch Calculator");
        init();
    }

    private void init() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel header = new JPanel();
        JLabel title = new JLabel("Epoch Calculator");
        title.setFont(new Font("Sans Serif", Font.BOLD, 36));
        title.setHorizontalAlignment(JLabel.CENTER);
        header.add(title);

        JPanel date = new JPanel();
        int[] l = {2, 2, 3, 2, 2};
        createTextInputs(l, date);

        mainPanel.add(header);
        mainPanel.add(date);
        frame.add(mainPanel);

        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void createTextInputs(int [] l, JPanel panel) {
        for (int i : l) {
            JTextField xx = new JTextField(i);
            xx.setHorizontalAlignment(JTextField.CENTER);
            xx.setFont(new Font("Sans Serif", Font.PLAIN, 12));
            xx.getDocument().addDocumentListener(new DocumentListener() {
                public void insertUpdate(DocumentEvent e) {
                    if (xx.getText().length() > i) {
                        xx.setText(xx.getText().substring(0, i));
                    }
                }

                public void removeUpdate(DocumentEvent e) {
                }

                public void changedUpdate(DocumentEvent e) {
                }
            });
            panel.add(xx);
        }
    }
}