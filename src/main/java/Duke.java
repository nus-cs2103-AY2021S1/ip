import java.util.Scanner;

public class Duke {
  public static void main(String[] args) {
    System.out.println("Hello! I'm Duke.\nWhat can I do for you?");
    Scanner sc = new Scanner(System.in);
    String input;
    while (!(input = sc.next()).equals("bye")) {
      System.out.println(input);
    }
    System.out.println("Bye. Hope to see you again.");
  }
}
