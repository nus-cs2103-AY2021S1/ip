import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static String horiLine = "____________________________________________________________" + "\n";
    private static ArrayList<Task> List = new ArrayList<>();

    private static void greet() {
        String greeting = "Hello! I'm Duke" + "\n" + "What can I do for you?" + "\n";
        System.out.println(horiLine + greeting + horiLine);
    }

    private static void echo() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) { //exit condition
                String goodbye = "Bye. Hope to see you again soon!";
                System.out.println(horiLine + goodbye + "\n" + horiLine);
            } else if (input.equals("list")) {
                int counter = 1;
                System.out.println(horiLine);
                for (Task task : List) {
                    System.out.println(counter + ". " + task.getName());
                    counter++;
                }
                System.out.println(horiLine);
            } else {
                List.add(new Task(input));
                System.out.println(horiLine + "added: " + input + "\n" + horiLine);
            }
        }
    }

    public static void main(String[] args) {
        greet();
        echo();
    }
}



