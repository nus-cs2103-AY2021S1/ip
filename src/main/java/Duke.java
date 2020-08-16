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

    System.out.println("\n" + logo);
    System.out.println("\t" + divider);
    System.out.println("\t" + "Hello! I'm Duke\n\tWhat can I do for you?");
    System.out.println("\t" + divider);

    while (true) {
      input = sc.next();

      if (input.equals("bye")) {
        break;
      }

      System.out.println("\t" + divider);
      System.out.println("\t" + input);
      System.out.println("\t" + divider + "\n");
    }

    System.out.println("\t" + divider);
    System.out.println("\tBye. Hope to see you again soon!");
    System.out.println("\t" + divider);
  }
}
