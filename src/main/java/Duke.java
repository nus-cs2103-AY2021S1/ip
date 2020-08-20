import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("————————————————————————————————————————————————————————————");
        System.out.println("Hello! I'm Duke! \nWhat can I do for you?");
        System.out.println("————————————————————————————————————————————————————————————");

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            System.out.println("————————————————————————————————————————————————————————————");
            System.out.println(command);
            System.out.println("————————————————————————————————————————————————————————————");
            command = sc.nextLine();
        }

        System.out.println("————————————————————————————————————————————————————————————");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("————————————————————————————————————————————————————————————");
        sc.close();
    }
}
