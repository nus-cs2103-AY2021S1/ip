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
            if (input.equals("list")) {
                listOfItems.getList();
            } else if (input.contains("done")) {
                listOfItems.doneItem(input);
            } else if (input.contains("delete")) {
                listOfItems.deleteItem(input);
            } else {
                listOfItems.addItem(input);
            }
            input = sc.nextLine();
        }

        String message = "Bye. Hope to see you again soon! :)";
        System.out.println(divider + "\n" + message + "\n" + divider);
    }
}

