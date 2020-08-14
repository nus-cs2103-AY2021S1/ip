import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ______  ___       __         __        _____\n"
                     + "   |    /         /  \\       /  \\     /\n"
                     + "   |    \\___     /____\\     /____\\   |\n"
                     + "   |        \\   /      \\   /      \\   \\\n"
                     + " ------  ___/  /        \\ /        \\    -----\n";
        System.out.println("Hello! I'm\n" + logo + "\nWhat can I do for you?");
        String next = "";
        while (true) {
          next = sc.nextLine();
          if (next.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            break;
          } else {
            System.out.println(next);
          }
        }
    }
}
