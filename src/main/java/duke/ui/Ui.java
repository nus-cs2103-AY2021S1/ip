package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

public interface Ui {
    void greet();
    
    String readCommand(Scanner sc);
    
    void bidFarewell();
    
    void display(ArrayList<String> lines);
    
    void displayError(String message);
    
    void displayLoadError();
}
