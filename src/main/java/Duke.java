import java.util.*;

public class Duke {

    public static void main(String[] args) {
        List<Task> list = new ArrayList<>();

        String greet = "   ____________________________________________________________\n" +
                "    Hello! I'm Duke\n" +
                "    What can I do for you?\n" +
                "   ____________________________________________________________\n";

        String exit = "    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________";

        String line = "    ____________________________________________________________";

        System.out.println(greet);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.next();
            if (input.equals("bye")) {
                System.out.println(exit);
                break;
            } else if (input.equals("list")) {
                Iterator<Task> iterator = list.iterator();
                int count = 0;
                System.out.println(line);
                System.out.println("    Here are the tasks in your list:");
                while (iterator.hasNext()) {
                    count++;
                    Task task = iterator.next();
                    String done = task.getDone() ? "[✓] " : "[✗] ";
                    System.out.println("    " + count + ". " + done + task.getDetails());
                }
                System.out.println(line);
            } else if (input.equals("done")) {
                int taskNumber = sc.nextInt();
                if (list.size() >= taskNumber) {
                    Task task = list.get(taskNumber - 1);
                    task.markAsDone();
                    System.out.println(line + "\n" +
                            "    Nice! I've marked this task as done: " + "\n" +
                            "    [✓] " + task.getDetails() + "\n" +
                            line);
                } else {
                    System.out.println(line + "\n" +
                            "   Oops! No such task!" + "\n" +
                            line);
                }
            } else {
                list.add(new Task(input));
                System.out.println(line + "\n" + "     added: " + input + "\n" + line + "\n");
            }
        }
    }
}
