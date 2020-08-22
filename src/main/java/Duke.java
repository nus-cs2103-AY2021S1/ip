import java.io.IOException;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) throws IOException {
        ListOfItems listOfItems = new ListOfItems();
        HandleFile handleFile = new HandleFile(listOfItems);
        handleFile.checkFile();

        Scanner sc = new Scanner(System.in);

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
                    handleFile.writeFile(listOfItems);
                } else if (input.contains("delete")) {
                    listOfItems.deleteItem(input);
                    handleFile.writeFile(listOfItems);
                } else {
                    listOfItems.addItem(input);
                    handleFile.writeFile(listOfItems);
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

