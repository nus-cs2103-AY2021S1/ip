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
                    System.out.println("    " + count + ". " + iterator.next().toString());
                }
                System.out.println(line);
            } else if (input.equals("done")) {
                int taskNumber = sc.nextInt();
                if (list.size() >= taskNumber) {
                    Task task = list.get(taskNumber - 1);
                    task.markAsDone();
                    System.out.println(line + "\n" +
                            "    Nice! I've marked this task as done: " + "\n" +
                            "    " + task.toString() + "\n" +
                            line);
                } else {
                    System.out.println(line + "\n" +
                            "   Oops! No such task!" + "\n" +
                            line);
                }
            } else if (input.equals("todo")) {
                ToDo toDo = new ToDo(sc.nextLine());
                list.add(toDo);
                System.out.println(line + "\n" +
                        "    Got it! I have added this todo to the list!" + "\n" +
                        "      " + toDo + "\n" +
                        String.format("    Now you have %d tasks in the list.", list.size()) + "\n" +
                        line + "\n");
            } else if (input.equals("event")) {
                String s = sc.nextLine();
                String[] arr = s.split("/at");
                Event event = new Event(arr[0], arr[1]);
                list.add(event);
                System.out.println(line + "\n" +
                        "    Got it! I have added this event to the list!" + "\n" +
                        "      " + event + "\n" +
                        String.format("    Now you have %d tasks in the list.", list.size()) + "\n" +
                        line + "\n");
            } else if (input.equals("deadline")) {
                String s = sc.nextLine();
                String[] arr = s.split("/by");
                Deadline deadline = new Deadline(arr[0], arr[1]);
                list.add(deadline);
                System.out.println(line + "\n" +
                        "    Got it! I have added this deadline to the list!" + "\n" +
                        "      " + deadline + "\n" +
                        String.format("    Now you have %d tasks in the list.", list.size()) + "\n" +
                        line + "\n");
            }
        }
    }
}
