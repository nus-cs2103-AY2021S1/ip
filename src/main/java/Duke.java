import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class Duke {

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

    public static Task addItem(String input) throws DukeException {
        String arr[] = input.split(" ", 2);
        Task curr = new Task("");
        if (arr.length == 1) {
            throw new DukeException("The description of a " + arr[0] + " cannot be empty!");
        } else if (arr[0].equals("todo")) {
            curr = new ToDo(arr[1]);
            list.add(curr);
        } else if (arr[0].equals("deadline")) {
            String info[] = arr[1].split("/by", 2);
            if (info.length == 1) {
                throw new DukeException("Deadline not provided!");
            } else {
                curr = new Deadline(info[0], info[1]);
                list.add(curr);
            }
        } else if (arr[0].equals("event")) {
            String info[] = arr[1].split("/at", 2);
            if (info.length == 1) {
                throw new DukeException("Time not provided!");
            } else {
                curr = new Event(info[0], info[1]);
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

    public static void writeSaveData() throws FileNotFoundException, DukeException {
        try {
            File dir = new File("data/Duke.txt");
            if (dir.exists()) {
                Scanner data = new Scanner(dir);

                while(data.hasNextLine()) {
                    String curr = data.nextLine();
                    String[] info = curr.split(", ", 4);
                    if (info[0].equals("T")) {
                        ToDo tobeAdded = new ToDo(info[2]);
                        if (info[1].equals("1")) {
                            tobeAdded.markAsDone();
                        }
                        list.add(tobeAdded);
                    } else if (info[0].equals("D")) {
                        Deadline tobeAdded = new Deadline(info[2], info[3]);
                        if (info[1].equals("1")) {
                            tobeAdded.markAsDone();
                        }
                        list.add(tobeAdded);
                    } else if (info[0].equals("E")) {
                        Event tobeAdded = new Event(info[2], info[3]);
                        if (info[1].equals("1")) {
                            tobeAdded.markAsDone();
                        }
                        list.add(tobeAdded);
                    }
                }
            }
        } catch (FileNotFoundException ex1) {
            throw new DukeException("Saved Data not found");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nextLine = "";

        try {
            writeSaveData();
        } catch (FileNotFoundException ex1) {
            ui.showError(ex1.getMessage());
        } catch (DukeException ex2) {
            ui.showError(ex2.getMessage());
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
            } else { // Case 6: Default errors
                ui.defaultError();
                nextLine = scanner.nextLine();
            }
        }

        // Ending the bot
        ui.bye();
    }
}

