package godfather.ui;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Duke can have UI that can greet users, read commands that they give, display lines and errors and bid the users
 * farewell upon exiting the program
 */
public interface Ui {
    void greet();
    String readCommand(Scanner sc);
    void bidFarewell();
    void display(ArrayList<String> lines);
    void displayError(String message);
}
