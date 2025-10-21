import java.awt.event.*;

public class Controller {
    public static void processAction(String buttonName, GUI guiInstance, ActionEvent e) {
        String[] results = guiInstance.getInputs(buttonName); // Method that returns required text field data based on the button
        try {
            if (results != null) {
                String[] dateString1 = {};
                String[] timeString1 = {};
                Date date1 = null;
                if (!buttonName.equals("construct")) { // Results only has length 1 for construct, so this would cause errors
                    dateString1 = results[0].split("/");
                    timeString1 = results[1].split(":");
                    date1 = new Date(timeString1[1], timeString1[0], dateString1[0], dateString1[1],
                            dateString1[2]);
                }
                if (buttonName.equals("difference")) {
                    String[] dateString2 = results[2].split("/");
                    String[] timeString2 = results[3].split(":");
                    Date date2 = new Date(timeString2[1], timeString2[0], dateString2[0], dateString2[1],
                            dateString2[2]);
                    RelativeDate difference = date1.difference(date2);
                    String resultText = String.format("Formatted: %02d/%02d/%04d %02d:%02d", difference.Days,
                            difference.Months, difference.Years, difference.Hours, difference.Minutes);
                    guiInstance.updateResults(resultText, "Unix: " + difference.getUnix());
                } else if (buttonName.equals("add")) {
                    String[] relativeTimeString = results[5].split(":");
                    RelativeDate relativeDate = new RelativeDate(relativeTimeString[1], relativeTimeString[0],
                            results[2], results[3], results[4]);
                    Date sum = date1.add(relativeDate);
                    String resultText = String.format("Formatted: %02d/%02d/%04d %02d:%02d", sum.Days,
                            sum.Months, sum.Years, sum.Hours, sum.Minutes);
                    guiInstance.updateResults(resultText, "Unix: " + sum.getUnix());
                } else if (buttonName.equals("unix")) {
                    guiInstance.updateResults("N/A", "Unix: " + date1.getUnix());
                } else if (buttonName.equals("construct")) {
                    Date date = new Date(results[0]);
                    String resultText = String.format("Formatted: %02d/%02d/%04d %02d:%02d", date.Days,
                            date.Months, date.Years, date.Hours, date.Minutes);
                    guiInstance.updateResults(resultText, "N/A");
                }
            }
        } catch (Exception ex) {
            guiInstance.updateResults("Error", "Check your inputs");
            System.out.println(ex);
        }
    }
}
