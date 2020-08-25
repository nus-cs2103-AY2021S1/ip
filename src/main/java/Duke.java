import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Duke {

    private static Storage storage;
    private static Ui ui = new Ui();
    private static TaskList tasks;

    private static String getFirstWord(String text) {

        int index = text.indexOf(' ');

        if (index > -1) { // Check if there is more than one word.

            return text.substring(0, index).trim(); // Extract first word.

        } else {

            return text; // Text is the first word itself.
        }
    }

    public static LocalDate dateParser(String input) throws DukeException {
        try {
            input = input.replace("/", "-");
            LocalDate ret = LocalDate.parse(input);
            return ret;
        } catch (DateTimeParseException ex1) {
            throw new DukeException("Wrong formatting!");
        }
    }

    public static String timeParser(String input) {
        String ret = "";
        int hour = Integer.valueOf(input)/100;
        if (hour >= 12) {
            ret = "PM";
            ret = Integer.toString(hour == 12 ? 12 : hour - 12).concat(ret);
        } else {
            ret = "AM";
            ret = Integer.toString(hour).concat(ret);
        }
        return ret;
    }

    public static void main(String[] args) {
        storage = new Storage("data/Duke.txt");
        Scanner scanner = new Scanner(System.in);

        String nextLine = "";

        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException ex1) {
            ui.showError(ex1.getMessage());
        }

        // Introduction
        ui.showWelcome();

        nextLine = scanner.nextLine();

        while (!nextLine.equals("bye")) {
            if (getFirstWord(nextLine).equals("todo") ||
                    getFirstWord(nextLine).equals("deadline") ||
                    getFirstWord(nextLine).equals("event")) {
                try {
                    Task curr = tasks.addItem(nextLine);
                    ui.addedItem(curr, tasks.getListSize());
                } catch (DukeException ex1) {
                    ui.showError(ex1.getMessage());
                }
                nextLine = scanner.nextLine();
            }
            else if (getFirstWord(nextLine).equals("done")) { // Case 4: checking off an item
                try {
                    Task curr = tasks.doneItem(nextLine);
                    ui.doneItem(curr);
                } catch (DukeException ex1){
                    ui.showError(ex1.getMessage());
                }
                nextLine = scanner.nextLine();
            } else if (getFirstWord(nextLine).equals("delete")) {
                try {
                    Task curr = tasks.deleteItem(nextLine);
                    ui.deleteItem(curr);
                }  catch(DukeException ex1) {
                    ui.showError(ex1.getMessage());
                }
                nextLine = scanner.nextLine();
            } else if (nextLine.equals("list")) { // Case 5: Displaying List
                ui.returnList(tasks.getList());
                nextLine = scanner.nextLine();
            } else if (nextLine.equals("save")) { // Case 6: saving data onto Duke.txt
                try {
                    storage.overwriteData(tasks.getList());
                    ui.save();
                } catch (IOException ex1) {
                    ui.showError(ex1.getMessage());
                }
                nextLine = scanner.nextLine();
            } else { // Case 6: Default errors
                ui.defaultError();
                nextLine = scanner.nextLine();
            }
        }

        // Ending the bot
        ui.bye();
    }
}

