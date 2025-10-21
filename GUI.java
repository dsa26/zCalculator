import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import javax.swing.event.*;

// AI Usage (mostly for Swing) -- https://chatgpt.com/share/68e73091-27e8-8004-9f03-7c083e5ef59c

/*
    Class Structure -- Text fields and labels that are expected to change are private instant variables
    The init method initializes the main GUI
    The updateResults method allows modifying the result labels
    The getInputs method allows getting information about input text fields
    The HandleGUI interface defines an interface to handle input changes -- This allows the controller to
        listen for and process user actions
*/

public class GUI {
    private JFrame frame;
    private JLabel mainresultsLabel;
    private JLabel unixLabel;
    private JFormattedTextField dateField1;
    private JFormattedTextField dateField2;
    private JSpinner relativeDaysField;
    private JSpinner relativeMonthsField;
    private JSpinner relativeYearsField;
    private JFormattedTextField timeField1;
    private JFormattedTextField timeField2;
    private JFormattedTextField relativeTimeField;
    private JTextField unixTextField;

    public GUI(HandleGUI handleGUI) {
        frame = new JFrame("Epoch Calculator");
        init(handleGUI);
    }

    private void init(HandleGUI handleGUI) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel date1Panel = new JPanel();
        date1Panel.setLayout(new BoxLayout(date1Panel, BoxLayout.X_AXIS));
        JPanel date2Panel = new JPanel();
        date2Panel.setLayout(new BoxLayout(date2Panel, BoxLayout.X_AXIS));
        JPanel relativePanel = new JPanel();
        relativePanel.setLayout(new BoxLayout(relativePanel, BoxLayout.X_AXIS));
        JPanel unixPanel = new JPanel();
        unixPanel.setLayout(new BoxLayout(unixPanel, BoxLayout.X_AXIS));

        date1Panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        date2Panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        relativePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        unixPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel date1Label = new JLabel("Date 1 in DD/MM/YYYY HH:MM");
        JLabel date2Label = new JLabel("Date 2 in DD/MM/YYYY HH:MM");
        JLabel relativeLabel = new JLabel("Relative Time in DD MM YYYY HH:MM");
        JLabel fromUnixLabel = new JLabel("Unix Epoch Time");
        date1Label.setAlignmentX(Component.CENTER_ALIGNMENT);
        date2Label.setAlignmentX(Component.CENTER_ALIGNMENT);
        relativeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        fromUnixLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat hf = new SimpleDateFormat("HH:mm");
        DateFormat df2 = new SimpleDateFormat("dd MM yyyy");

        dateField1 = new JFormattedTextField(df1);
        dateField2 = new JFormattedTextField(df1);
        relativeDaysField = new JSpinner(new SpinnerNumberModel(0, 0, 30, 1));
        relativeMonthsField = new JSpinner(new SpinnerNumberModel(0, 0, 11, 1));
        relativeYearsField = new JSpinner(new SpinnerNumberModel(0, 0, 12, 1));
        timeField1 = new JFormattedTextField(hf);
        timeField2 = new JFormattedTextField(hf);
        relativeTimeField = new JFormattedTextField(hf);
        unixTextField = new JTextField();
        relativeTimeField.setText("00:00");
        dateField1.setColumns(10);
        dateField2.setColumns(10);
        timeField1.setColumns(5);
        timeField2.setColumns(5);
        relativeTimeField.setColumns(5);
        unixTextField.setColumns(20);

        dateField1.setMaximumSize(dateField1.getPreferredSize());
        dateField2.setMaximumSize(dateField2.getPreferredSize());
        relativeDaysField.setMaximumSize(relativeDaysField.getPreferredSize());
        relativeMonthsField.setMaximumSize(relativeMonthsField.getPreferredSize());
        relativeYearsField.setMaximumSize(relativeYearsField.getPreferredSize());
        timeField1.setMaximumSize(timeField1.getPreferredSize());
        timeField2.setMaximumSize(timeField2.getPreferredSize());
        relativeTimeField.setMaximumSize(relativeTimeField.getPreferredSize());
        unixTextField.setMaximumSize(unixTextField.getPreferredSize());

        date1Panel.add(dateField1);
        date1Panel.add(timeField1);
        date2Panel.add(dateField2);
        date2Panel.add(timeField2);
        relativePanel.add(relativeDaysField);
        relativePanel.add(relativeMonthsField);
        relativePanel.add(relativeYearsField);
        relativePanel.add(relativeTimeField);
        unixPanel.add(unixTextField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        JButton addButton = new JButton("Add");
        JButton differenceButton = new JButton("Difference");
        JButton unixButton = new JButton("To Unix");
        JButton constructButton = new JButton("Construct from Unix");
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        differenceButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        unixButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        constructButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleGUI.processInput("add", GUI.this, e);
            }
        });

        differenceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleGUI.processInput("difference", GUI.this, e);
            }
        });

        unixButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleGUI.processInput("unix", GUI.this, e);
            }
        });

        constructButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleGUI.processInput("construct", GUI.this, e);
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(differenceButton);
        buttonPanel.add(unixButton);
        buttonPanel.add(constructButton);

        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.X_AXIS));

        mainresultsLabel = new JLabel("Results");
        unixLabel = new JLabel("Unix Time");
        mainresultsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        unixLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultsPanel.add(mainresultsLabel);
        resultsPanel.add(new JLabel("           "));
        resultsPanel.add(unixLabel);

        mainPanel.add(date1Label);
        mainPanel.add(date1Panel);
        mainPanel.add(Box.createVerticalStrut(50));
        mainPanel.add(date2Label);
        mainPanel.add(date2Panel);
        mainPanel.add(Box.createVerticalStrut(50));
        mainPanel.add(relativeLabel);
        mainPanel.add(relativePanel);
        mainPanel.add(Box.createVerticalStrut(50));
        mainPanel.add(fromUnixLabel);
        mainPanel.add(unixPanel);
        mainPanel.add(Box.createVerticalStrut(100));
        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createVerticalStrut(50));
        mainPanel.add(resultsPanel);

        frame.add(mainPanel);

        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void updateResults(String mainResults, String unixResults) {
        mainresultsLabel.setText(mainResults);
        unixLabel.setText(unixResults);
    }

    public String[] getInputs(String type) {
        try {
            if (type.equals("difference")) {
                dateField1.commitEdit();
                dateField2.commitEdit();
                timeField1.commitEdit();
                timeField2.commitEdit();
                String[] results = { dateField1.getText(), timeField1.getText(), dateField2.getText(),
                        timeField2.getText() };
                return results;
            } else if (type.equals("add")) {
                dateField1.commitEdit();
                timeField1.commitEdit();
                relativeTimeField.commitEdit();
                String[] results = { dateField1.getText(), timeField1.getText(),
                        relativeDaysField.getValue().toString(),
                        relativeMonthsField.getValue().toString(), relativeYearsField.getValue().toString(),
                        relativeTimeField.getText() };
                return results;
            } else if (type.equals("unix")) {
                dateField1.commitEdit();
                timeField1.commitEdit();
                String[] results = { dateField1.getText(), timeField1.getText() };
                return results;
            } else if (type.equals("construct")) {
                String[] results = { unixTextField.getText() };
                return results;
            } else {
                return null;
            }
        } catch (ParseException e) {
            updateResults("Invalid Input", "N/A");
            return null;
        }
    }

    public interface HandleGUI {
        public void processInput(String buttonName, GUI instance, ActionEvent e);
    }
}