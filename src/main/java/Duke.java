import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListOfItems listOfItems = new ListOfItems();
        String divider = "____________________________________________________________";
        String intro = "Hello! I'm Bob\n" +
                "What can I do for you?\n";

        System.out.println(divider + "\n" + intro + "\n" + divider);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            try {
                if (input.equals("list")) {
                    listOfItems.getList();
                } else if (input.contains("done")) {
                    listOfItems.doneItem(input);
                } else if (input.contains("delete")) {
                    listOfItems.deleteItem(input);
                } else if (input.contains("show items due by")) { // check items due on a specific date
                    listOfItems.check(input);
                } else {
                    listOfItems.addItem(input);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            input = sc.nextLine();
        }

        String message = "Bye. Hope to see you again soon! :)";
        System.out.println(divider + "\n" + message + "\n" + divider);
    }
}

