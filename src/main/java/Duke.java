import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String tab = "     ";
        String line = tab + "____________________________________________________________";
        String intro = line + "\n" +
                tab + "Hello! I'm Duke\n" +
                tab + "What can I do for you?\n" +
                line + "\n";
        System.out.println(intro);
        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        while(true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(line);
                System.out.println(tab + "Bye. Hope to see you again soon!");
                System.out.println(line + "\n");
                break;
            } else if (input.equals("list")){
                int id = 1;
                System.out.println(line);
                System.out.println(tab + "Here are the tasks in your list:");
                for(Task task : tasks) {
                    System.out.println(tab + id + ". " + task);
                    id++;
                }
                System.out.println(line + "\n");
            } else if (input.split(" ")[0].equals("done")) {
                System.out.println(tab + "Nice! I've marked this task as done:");
                int idx = Integer.parseInt(input.split(" ")[1]) - 1;
                Task doneTask = tasks.get(idx).done();
                tasks.set(idx, doneTask);
                System.out.println(tab + "   " + doneTask);
            }
            else {
                tasks.add(new Task(input));
                System.out.println(line);
                System.out.println(tab + "added: " + input);
                System.out.println(line + "\n");
            }
        }
    }
}
