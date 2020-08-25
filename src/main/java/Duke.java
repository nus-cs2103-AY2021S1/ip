import duke.Parser;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Parser parser = new Parser(taskList, ui);

        // Initialise TaskList
        Storage.loadTasksFrom("data/duke.txt", taskList);
        
        ui.showWelcomeScreen();
        
        // Initialise booleans and scanners
        Scanner inputScanner = new Scanner(System.in);
        
        while (true) {
            // blocks program until input is received
            String newInput = inputScanner.nextLine();
            
            //trim the input to remove leading and ending whitespace
            newInput = newInput.trim();
            
            if (!parser.parse(newInput)) {
                break;
            }
        }

        // quit
        inputScanner.close();
        
        // Save tasks to file
        Storage.saveTasksTo("data/duke.txt", taskList);
        
        ui.showGoodbyeScreen();
    }
}
