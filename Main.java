import java.awt.event.*;

public class Main {
    public static void main(String[] args) {
        GUI mainGUI = new GUI(new GUI.HandleGUI() { // Creates an anonymous inner class for the HandleGUI interface
            @Override // Overrides the processInput method and defines how to process input changes
            public void processInput(String buttonName, GUI instance, ActionEvent e) {
                Controller.processAction(buttonName, instance, e); // Calls the controller method to handle GUI changes
            }
        });
    }
}