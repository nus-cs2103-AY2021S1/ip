import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static List<String> tasks = new ArrayList<>();

    public static void interact() {
        System.out.println("-----------------------------------------------------");
        System.out.println("    " + "Hello! I'm Duke\n    What can I do for you?");
        System.out.println("-----------------------------------------------------");

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("bye")) {
                System.out.println("-----------------------------------------------------");
                System.out.println("    " + "Bye. Hope to see you again soon!");
                System.out.println("-----------------------------------------------------");
                break;
            } else if (line.equals("list")) {
                System.out.println("-----------------------------------------------------");
                for (String task : tasks) {
                    int index = tasks.indexOf(task) + 1;
                    System.out.println("    " + index + ". " + task);
                }
                System.out.println("-----------------------------------------------------");
            } else {
                System.out.println("-----------------------------------------------------");
                tasks.add(line);
                System.out.println("    " + "added: " + line);
                System.out.println("-----------------------------------------------------");
            }
        }
    }

    public static void main(String[] args) {
        interact();
    }
}
