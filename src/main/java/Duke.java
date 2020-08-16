import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class Duke {

    private static String line = "________________________________________________";

    public static void display(String text) {
        System.out.println(line);
        System.out.println(text);
        System.out.println(line);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String logo = "Hello I'm Verzachtend \n" +
                "What can I do for you?\n" +
                "BE YOURSELF, NEVER SURRENDER AND KEEP A SMILE ON YOUR FACE";
        System.out.println(logo);

        String end = "bye";
        String done = "done";
        List<Task> tasks = new ArrayList<>();
        String echo = scan.nextLine();
        String listing = "list";

        while (!echo.equals(end)) {
            String displayText = "";
            if (echo.equals(listing)) {
                int i = 1;
                System.out.println(line);
                System.out.println("Here are the tasks in your list:");
                for (Task item :tasks) {
                    if (item.check()) {
                        System.out.println(i + ".[✓] " + item.getName());
                    } else {
                        System.out.println(i + ".[✗] " + item.getName());
                    }
                    i++;
                }
                System.out.println(line);
            } else if (echo.toLowerCase().contains(done)) {
                int num = Integer.parseInt(echo.substring(5));
                Task curr = tasks.get(num - 1);
                tasks.set(num - 1, new Task(curr.getName() , true));
                displayText = "Nice! I've marked this task as done: \n"
                        + "[✓] " + curr.getName();
                display(displayText);
            } else {
                displayText = "added: " + echo;
                display(displayText);
                tasks.add(new Task(echo, false));
            }
            echo = scan.nextLine();
        }
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
