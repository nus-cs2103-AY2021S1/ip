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
    public static ArrayList<Task> list = new ArrayList<>();

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

    public static Task addItem(String input) throws DukeException {

            String arr[] = input.split(" ", 2);
            Task curr = new Task("");
            if (arr.length == 1) {
                throw new DukeException("The description of a " + arr[0] + " cannot be empty!");
            } else if (arr[0].equals("todo")) {
                curr = new ToDo(arr[1]);
                list.add(curr);
            } else if (arr[0].equals("deadline")) {
                String info[] = arr[1].split("/by ", 2);
                if (info.length == 1) {
                    throw new DukeException("Deadline not provided!");
                } else {
                    curr = new Deadline(info[0], dateParser(info[1]));
                    list.add(curr);
                }
            } else if (arr[0].equals("event")) {
                String info[] = arr[1].split("/at ", 2);
                if (info.length == 1) {
                    throw new DukeException("Time not provided!");
                } else {
                    String[] t = info[1].split(" ", 2);
                    curr = new Event(info[0], dateParser(t[0]), timeParser(t[1]));
                    list.add(curr);
                }
            }
            return curr;
    }

    public static Task deleteItem(String input) throws DukeException{
        String info[] = input.split(" ", 2);
        Task toBeDeleted = new Task("");
        if (info.length == 1) {
            throw new DukeException("Which item???");
        } else {
            try {
                int index = Integer.parseInt(info[1]);
                if (index > list.size() || index <= 0) {
                    throw new DukeException("Excuse Moi???");
                } else {
                    toBeDeleted = list.get(index - 1);
                    list.remove(index - 1);
                    return toBeDeleted;
                }
            } catch (NumberFormatException ex1){
                throw new DukeException("Excuse Meee? number pls.");
            }
        }
    }

    public static Task doneItem(String input) throws DukeException {
        String info[] = input.split(" ", 2);
        Task toBeRet = new Task("");
        if (info.length == 1) {
            throw new DukeException("Which item???");
        } else {
            int index = Integer.parseInt(info[1]);
            if (index > list.size() || index <= 0) {
                throw new DukeException("Excuse Moi???");
            } else {
                list.get(index-1).markAsDone();
                toBeRet = list.get(index-1);
            }
        }
        return toBeRet;
    }

    public static void main(String[] args) {
        storage = new Storage("data/Duke.txt");
        Scanner scanner = new Scanner(System.in);

        String nextLine = "";

        try {
            list = storage.load();
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
                    Task curr = addItem(nextLine);
                    ui.addedItem(curr);
                } catch (DukeException ex1) {
                    ui.showError(ex1.getMessage());
                }
                nextLine = scanner.nextLine();
            }
            else if (getFirstWord(nextLine).equals("done")) { // Case 4: checking off an item
                try {
                    Task curr = doneItem(nextLine);
                    ui.doneItem(curr);
                } catch (DukeException ex1){
                    ui.showError(ex1.getMessage());
                }
                nextLine = scanner.nextLine();
            } else if (getFirstWord(nextLine).equals("delete")) {
                try {
                    Task curr = deleteItem(nextLine);
                    ui.deleteItem(curr);
                }  catch(DukeException ex1) {
                    ui.showError(ex1.getMessage());
                }
                nextLine = scanner.nextLine();
            } else if (nextLine.equals("list")) { // Case 5: Displaying List
                ui.returnList();
                nextLine = scanner.nextLine();
            } else if (nextLine.equals("save")) { // Case 6: saving data onto Duke.txt
                try {
                    storage.overwriteData(list);
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

