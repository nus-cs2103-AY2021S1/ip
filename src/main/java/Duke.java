import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("————————————————————————————————————————————————————————————");
        System.out.println("Hello! I'm Duke! \nWhat can I do for you?");
        System.out.println("————————————————————————————————————————————————————————————");

        Scanner sc = new Scanner(System.in);
        List<String> tasks = new ArrayList<>();
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            System.out.println("————————————————————————————————————————————————————————————");
            if (command.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + ". " + tasks.get(i));
                }
            } else {
                tasks.add(command);
                System.out.println("added: " + command);
            }
            System.out.println("————————————————————————————————————————————————————————————");
            command = sc.nextLine();
        }

        System.out.println("————————————————————————————————————————————————————————————");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("————————————————————————————————————————————————————————————");
        sc.close();
    }
}
