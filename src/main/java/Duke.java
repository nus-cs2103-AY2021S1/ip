import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
  public static void main(String[] args) {
    String logo =
        " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    System.out.println(logo + "\nHello ! I'm Duke\nWhat can I do for you?\n");

    // Arraylist used to ensure scalability
    ArrayList<String> dataArrayList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    String input = "";
    while (!input.equals("bye")) {
      input = scanner.nextLine();
      // Switch case use to prepare for future requirements
      switch (input) {
        case "list":
          for (int i = 0; i < dataArrayList.size(); i++) {
            String listing = String.format("%d. %s", i + 1, dataArrayList.get(i));
            System.out.println(listing);
          }
          break;
        default:
          if (!input.equals("bye")) {
            dataArrayList.add(input);
            System.out.println("added: " + input);
          }
          break;
      }
    }
    System.out.println("Bye. Hope to see you again soon!");
  }
}
