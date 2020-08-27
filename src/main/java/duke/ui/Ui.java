package duke.ui;

import duke.DukeCommandMatcher;
import duke.storage.Storage;
import duke.utils.Constants;
import duke.utils.UtilFunction;

import java.util.Objects;
import java.util.Scanner;

public class Ui {

    private Ui() {}
    
    public static void loop(Storage database) throws IllegalStateException{
        Printer.printGreeting();
        Scanner dukeScanner = new Scanner(System.in);
        DukeCommandMatcher dukeCommandMatcher = new DukeCommandMatcher(database);
        while (dukeScanner.hasNextLine()) {
            String command = dukeScanner.nextLine();
            try {
                System.out.println(Constants.DIVIDER);
                String response = dukeCommandMatcher.matchCommand(command);
                if (Objects.equals(response, "EXIT")) {
                    break;
                }
                System.out.println(Constants.DIVIDER);
            }catch(Exception err){
                UtilFunction.printLimit(err.toString());
                System.out.println(Constants.DIVIDER);
            }
        }
        dukeScanner.close();
    }
}
