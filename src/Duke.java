import java.sql.SQLOutput;
import java.util.Scanner;

public class Duke {
    public static void displayMessage(String s) {
        System.out.println("\t---------------------------\n\t" +
                s +
                "\n\t---------------------------"
        );
    }

    public enum Commands {
        BYE("bye"), LIST("list"), DONE("done"), TODO("todo"), DEADLINE("deadline"), EVENT("event");

        private String s;

        private Commands(String s) {
            this.s = s;
        }

        public String toString() {
            return this.s;
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List list1 = List.startList();

        displayMessage("Hello! I'm Duke\n\tWhat can I do for you?");
        String input;
        boolean cont = true;
        while (cont) {
            input = sc.nextLine();

            int i = input.indexOf(' ');
            String[] arr = input.split(" ", 2);
            String pref = arr[0];
            String rest;

            String addedMsg;

            try {
                switch (pref) {
                    case "bye":
                        displayMessage("Bye. Hope to see you again soon!");
                        cont = false;
                        break;

                    case "list":
                        String listString = list1.toString();
                        displayMessage(listString);
                        break;

                    case "done":
                        if (arr.length == 1) throw new MissingDescriptionException("done");

                        rest = arr[1];
                        int n;

                        try {
                            n = Integer.parseInt(rest);
                        } catch (NumberFormatException e) {
                            throw new InvalidTaskNumberException("done");
                        }

                        displayMessage(list1.markAsDone(n));
                        break;

                    case "delete":
                        if (arr.length == 1) throw new MissingDescriptionException("delete");

                        rest = arr[1];
                        int m;

                        try {
                            m = Integer.parseInt(rest);
                        } catch (NumberFormatException e) {
                            throw new InvalidTaskNumberException("delete");
                        }

                        displayMessage(list1.remove(m));
                        break;

                    case "todo":
                        if (arr.length == 1) throw new MissingDescriptionException("todo");

                        rest = arr[1];
                        addedMsg = list1.addToList(new Todo(rest));
                        displayMessage(addedMsg);
                        break;

                    case "deadline":
                        if (arr.length == 1) throw new MissingDescriptionException("deadline");

                        rest = arr[1];
                        String[] arr1 = rest.split("/by", 2);

                        if (arr1.length == 1) throw new MissingDurationException("deadline");

                        addedMsg = list1.addToList(new Deadline(arr1[0], arr1[1]));
                        displayMessage(addedMsg);
                        break;

                    case "event":
                        if (arr.length == 1) throw new MissingDescriptionException("event");

                        rest = arr[1];
                        String[] arr2 = rest.split("/at", 2);

                        if (arr2.length == 1) throw new MissingDurationException("event");

                        addedMsg = list1.addToList(new Event(arr2[0], arr2[1]));
                        displayMessage(addedMsg);
                        break;

                    default:
                        throw new InvalidInputException();

                }
            } catch (Exception e) {
                displayMessage(e.getMessage());
            }
        }
    }
}
