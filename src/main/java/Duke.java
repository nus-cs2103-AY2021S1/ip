import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static String printTask(Task tsk) {
        return "[" + tsk.getStatusIcon() + "] " + tsk.getDescription();
    }
    public static void main(String[] args) {
        String greetings = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greetings);

        ArrayList<Task> lst = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                String listIntro = "Here are the tasks in your list:";
                System.out.println(listIntro);
                for (int i = 0; i < lst.size(); i ++) {
                    Task currentTask = lst.get(i);
                    String num = Integer.toString(i + 1);
                    System.out.println(num + "." + printTask(currentTask));
                }
            } else if (input.contains("done")) {
                String[] splitted = input.split("\\s+");
                int taskIndex = Integer.parseInt(splitted[1]) - 1;
                Task selectedTask = lst.get(taskIndex);
                selectedTask.markAsDone();
                System.out.println("Nice! I've marked this task as done:\n  " + printTask(selectedTask));
            } else {
                lst.add(new Task(input));
                System.out.println("added: " + input);
            }
        }
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        System.out.println(goodbyeMessage);
    }
}
