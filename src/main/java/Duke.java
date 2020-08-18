import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      System.out.println("Hi! I'm Duke" + "\n" + "What can I do for you?");
      String current = scanner.nextLine();

      while (!current.equals("bye")) {
          System.out.println(current);
          current = scanner.nextLine();
      }

      System.out.println("Bye. Hope to see you again!");
      scanner.close();
    }
}
