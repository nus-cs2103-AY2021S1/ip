import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
  public static void main(String[] args) {
    String divider = "---------------------------------------------------------------------------------------------";
    String logo = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
    Scanner sc = new Scanner(System.in);
    String input = "";
    List<String> list = new ArrayList<>();

    System.out.println("\n" + logo);
    System.out.println("\t" + divider);
    System.out.println("\t" + "Hello! I'm Duke\n\tWhat can I do for you?");
    System.out.println("\t" + divider);

    while (true) {
      input = sc.nextLine();

      if (input.equals("bye")) {
        break;
      } else if (input.equals("list")) {
        System.out.println("\t" + divider);

        for (int i = 0; i < list.size(); i++) {
          System.out.printf("\t%d. %s\n", i + 1, list.get(i));
        }

        System.out.println("\t" + divider + "\n");
      } else {
        list.add(input);

        System.out.println("\t" + divider);
        System.out.println("\tadded: " + input);
        System.out.println("\t" + divider + "\n");
      }
    }

    System.out.println("\t" + divider);
    System.out.println("\tBye. Hope to see you again soon!");
    System.out.println("\t" + divider);
  }
}
