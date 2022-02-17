package duke.common;

import duke.Main;

/**
 * Class create to print items.
 *
 * @author Galvin Leow Wen Yuan
 * @author A0200204J
 * @version v1.0
 */
public class Ui {

    /**
     * Returns welcome message.
     *
     * @return string welcome message.
     */
    public String showWelcome() {
        String logo =
                " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                        + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        return logo + "\nHello ! I'm Duke.\nWhat can I do for you?";
    }

    /**
     * Returns String that provide user with functionalities of the application.
     *
     * @return String menu
     */
    public String getMenu() {
        return "1. list\n " + "2. done...{item number}\n" + "3. todo...{description}\n"
                + "4. deadline...{description}.../by...{dd/mm/yyyy}...{hhmm}\n"
                + "\t e.g. deadline return book /by 1/12/2020 1800\n"
                + "5. event...{description}.../at...{dd/mm/yyyy}...{hhmm}-{hhmm}\n"
                + "\t e.g. event meeting /at 1/12/2020 1800-1900\n" + "6. delete...{item number}\n"
                + "7. find...{item to find}\n" + "8. update...{item number}.../{desc OR date}...{value}\n"
                + "\t e.g. update 1 /desc new value OR update 1 /date 1/12/2020 1800\n" + "9. bye";
    }

    /**
     * Prints loading error that will use when file fail to load.
     */
    public void showLoadingError() {
        showError("Error: Fail to load file!");
    }

    /**
     * Uses to divide the application to be more user friendly.
     */
    public String showLine() {
        return "____________________________________";
    }

    /**
     * Prints out an error message.
     *
     * @param error error that occur.
     */
    public void showError(String error) {
        System.err.println("Error: " + error);
    }

    /**
     * Prints out any string.
     *
     * @param statement any string input
     */
    public static void printString(String statement) {
        Main.getMainWindow().printDukeDialog(statement);
    }
}
