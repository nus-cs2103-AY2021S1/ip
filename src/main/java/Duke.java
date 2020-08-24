import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static String line = "-------------------------------------";
    public static String defaultError = "Wat talking you?";
    public static String addedMsg = "Alright, I've added a new order: ";
    public static String help = "HELP";
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nextLine = "";
        // Introduction
        System.out.println(line);
        System.out.println("Welcome to mel's drive-in!");
        System.out.println("What would you like to have?");
        System.out.println(line);

        nextLine = scanner.nextLine();

        while (!nextLine.equals("bye")) {
            if (getFirstWord(nextLine).equals("todo") ||
                    getFirstWord(nextLine).equals("deadline") ||
                    getFirstWord(nextLine).equals("event")) {
                try {
                    Task curr = addItem(nextLine);

                    System.out.println(line);
                    System.out.println(addedMsg);
                    System.out.println(curr);
                    System.out.println("You have ordered " + list.size() + " items.");
                    System.out.println(line);
                } catch (DukeException ex1) {
                    System.out.println(line);
                    System.out.println(ex1);
                    System.out.println(line);
                }
                nextLine = scanner.nextLine();
            }
            else if (getFirstWord(nextLine).equals("done")) { // Case 4: checking off an item
                try {
                    Task curr = doneItem(nextLine);

                    System.out.println(line);
                    System.out.println("Great choice! I have taken your order: ");
                    System.out.println(curr);
                    System.out.println(line);
                } catch (DukeException ex1){
                    System.out.println(line);
                    System.out.println(ex1);
                    System.out.println(line);
                }
                nextLine = scanner.nextLine();
            } else if (getFirstWord(nextLine).equals("delete")) {
                try {
                    Task curr = deleteItem(nextLine);

                    System.out.println(line);
                    System.out.println("Too bad. I'll remove the following order: ");
                    System.out.println(curr);
                    System.out.println(line);
                }  catch(DukeException ex1) {
                    System.out.println(ex1);
                }
                nextLine = scanner.nextLine();
            } else if (nextLine.equals("list")) { // Case 5: Displaying List
                System.out.println(line);
                System.out.println("Here's what you have ordered so far...");
                for (int k = 0; k < list.size(); k++) {
                    System.out.println((k + 1) + ": " + list.get(k));
                }
                System.out.println((line));
                nextLine = scanner.nextLine();
            } else { // Case 6: Default errors
                System.out.println(line);
                System.out.println(defaultError);
                System.out.println(line);
                nextLine = scanner.nextLine();
            }
        }

        // Ending the bot
        System.out.println(line);
        System.out.println("Bye! Please come again!");
        System.out.println(line);
    }
}

