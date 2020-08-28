package duke.ui;

import duke.DukeCommandMatcher;
import duke.storage.Storage;
import duke.utils.Constants;
import duke.utils.UtilFunction;

import java.util.Objects;
import java.util.Scanner;

public class Ui {

    private Ui() {} //this class is not meant to be instantiated

    /**
     * Launch the Duke loop.
     * @param database the database to launch the Duke
     * @throws IllegalStateException
     * @see IllegalStateException
     */
    public static void loop(Storage database) throws IllegalStateException {
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
            } catch (Exception err) {
                UtilFunction.printLimit(err.toString());
                System.out.println(Constants.DIVIDER);
            }
        }
        dukeScanner.close();
    }
}
