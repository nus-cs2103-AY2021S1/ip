import java.util.Scanner;

public class Duke {
  public static void main(String[] args) {
    String logo =
        " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    System.out.println("Hello ! I'm Duke\nWhat can I do for you?\n" + logo);

    while (true) {
      Scanner scanner = new Scanner(System.in);
      String input = scanner.nextLine();
      if (input.equals("bye")) {
        break;
      } else {
        System.out.println(input);
      }
    }
    System.out.println("Bye. Hope to see you again soon!");
  }
}
