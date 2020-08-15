import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    static List<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________\n");
        
        Scanner sc = new Scanner(System.in);
        String userCommand = sc.nextLine();

        while (!userCommand.equals("bye")) {
            if (userCommand.equals("list")) {
                System.out.println("\t____________________________________________________________");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println("\t" + (i + 1) + ". " + tasks.get(i));
                }
                System.out.println("\t____________________________________________________________\n");
                userCommand = sc.nextLine();
            } else {
                System.out.println("\t____________________________________________________________");
                System.out.println("\t" + "added: " + userCommand);
                System.out.println("\t____________________________________________________________\n");
                tasks.add(userCommand);
                userCommand = sc.nextLine();
            }
        }

        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }
}
