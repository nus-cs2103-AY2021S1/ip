import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Luke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> todo = new ArrayList<>();
        System.out.printf("Luke:\n\tHey there! I'm Luke.\n\tPlease tell me what to add to your to-do list.\nYou:\n\t");
        while (true) {
            String input = sc.nextLine().toLowerCase();
            if (Pattern.matches("^(task) +.*$", input)){
                String task = input.replaceAll("task ", "");
                Task newTask = new Task(task);
                todo.add(newTask);
                String number = countTasks(todo);
                System.out.printf("Luke:\n\tThe following task has been successfully added.\n\t%s\n\tNow you have %s in your to-do list.\nYou:\n\t", newTask, number);
            } else if (Pattern.matches("^(deadline) +.*$", input)){
                String[] deadline = input.split("deadline | /by ");
                Deadline newDeadline = new Deadline(deadline[1], deadline[2]);
                todo.add(newDeadline);
                String number = countTasks(todo);
                System.out.printf("Luke:\n\tThe following task has been successfully added.\n\t%s\n\tNow you have %s in your to-do list.\nYou:\n\t", newDeadline, number);
            } else if (Pattern.matches("^(event) +.*$", input)) {
                String[] event = input.split("event | /at ");
                Event newEvent = new Event(event[1], event[2]);
                todo.add(newEvent);
                String number = countTasks(todo);
                System.out.printf("Luke:\n\tThe following task has been successfully added.\n\t%s\n\tNow you have %s in your to-do list.\nYou:\n\t", newEvent, number);
            } else if (input.equals("list")) {
                String todoSummary = "Luke:\n\tHere are the tasks in your to-do list.";
                for (int i = 0; i < todo.size(); i++) {
                    Task current = todo.get(i);
                    todoSummary += String.format("\n\t%d.%s", i + 1, current);
                }
                System.out.printf("%s\nYou:\n\t", todoSummary);
            } else if (Pattern.matches("^(done)+ ?[0-9]*$", input)) {
                String task = input.replaceAll("\\D+", ""); //extract only the digits from the input
                Task done = todo.get(Integer.parseInt(task) - 1);
                done.markAsDone();
                System.out.printf("Luke:\n\tThe following task has successfully been marked as done!\n\t%s\nYou:\n\t",
                        done);
            } else if (input.equals("bye")) {
                System.out.println("Luke:\n\tOh, are you leaving? Hope to see you soon!");
                break;
            } else {
                System.out.printf("Luke:\n\tSorry I do not understand :( Please try another command.\nYou:\n\t");
            }
        }
    }

    private static String countTasks(ArrayList<Task> arrayList) {
        int n = arrayList.size();
        return n <= 1
                ? String.format("%d task", n)
                : String.format("%d tasks", n);
    }
}
