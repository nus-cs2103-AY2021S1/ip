import java.sql.SQLOutput;
import java.util.Scanner;

public class Duke {
    public static void displayMessage(String s) {
        System.out.println("\t---------------------------\n\t" +
                s +
                "\n\t---------------------------"
        );
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
            String pref;
            String rest = "";
            if (i > -1) {
                pref = input.substring(0,i);
                rest = input.substring(i+1);
            } else {
                pref = input;
            }

            String addedMsg;
            switch(pref) {
                case "bye":
                    displayMessage("Bye. Hope to see you again soon!");
                    cont = false;
                    break;

                case "list":
                    String listString = list1.toString();
                    displayMessage(listString);
                    break;

                case "done":
                    int n = Integer.parseInt(rest);
                    displayMessage(list1.markAsDone(n));
                    break;

                case "todo":
                    addedMsg = list1.addToList(new Todo(rest));
                    displayMessage(addedMsg);
                    break;

                case "deadline":
                    String[] arr1 = rest.split("/by", 2);
                    addedMsg = list1.addToList(new Deadline(arr1[0], arr1[1]));
                    displayMessage(addedMsg);
                    break;

                case "event":
                    String[] arr2 = rest.split("/at", 2);
                    addedMsg = list1.addToList(new Event(arr2[0], arr2[1]));
                    displayMessage(addedMsg);
                    break;

                default:
                    displayMessage("oops!");
            }
        }
    }
}
