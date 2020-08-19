import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I am Duke\n"
                + "What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String terminate = "bye";
        String input;
        ArrayList<Task> tasks = new ArrayList<>();

        while (!(input = sc.nextLine()).equals(terminate)) {

            Task newTask = new Task(input, false);
            String desc = newTask.getDescription();
            String trimmed = desc.trim();
            String first = trimmed.split(" ")[0].trim(); // checking the first word
            String last = desc.substring(first.length()).trim(); // for the case of "done "

            if (first.equals("")) {
                continue;
            } else if (first.equals("done")) {
                int id = Integer.parseInt(last) - 1;

                System.out.println("Nice! I've marked this task as done:");
                String changed = tasks.get(id).getDescription();
                System.out.println("[" + "\u2713" + "]" + changed);

                Task updatedTask = new Task(changed, true);
                tasks.set(id, updatedTask);
            } else if (!first.equals("list")) {
                newTask.addTask(desc);
                tasks.add(newTask);
            } else {
                Iterator<Task> iter = tasks.iterator();
                int index = 1;
                while (iter.hasNext()) {
                    Task currentTask = iter.next();
                    String next = currentTask.getDescription();
                    System.out.println(index + "." + "[" + currentTask.getStatusIcon() +"] " + next);
                    index++;
                }
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
