import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
//    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//    }

    public static void main(String[] args) {

        String gap = "        ";

        String init = "        ____________________________________________________________\n" +
                "        Hello! I'm Duke\n" +
                "        What can I do for you?\n" +
                "        ____________________________________________________________\n";

        System.out.println(init);

        Scanner sc = new Scanner(System.in);
        StringBuilder sb;
        String temp;
        List<Task> tasks = new ArrayList<>();

        while(!(temp = sc.nextLine()).equals("bye")) {
            sb = new StringBuilder();
            sb.append(gap);
            sb.append("____________________________________________________________\n");

            if(temp.equals("list")) {
                sb.append(gap);
                sb.append("Here are the tasks in your list:\n");

                int ctr = 1;

                for(Task task: tasks) {
                    sb.append(gap);
                    sb.append(ctr).append(".");
                    sb.append(task);
                    sb.append("\n");
                    ctr++;
                }
            } else if(temp.startsWith("done")) {
                int index = Integer.parseInt(""+temp.charAt(5)) - 1;
                if(tasks.size() >= index && index >= 0) {
                    Task tas = tasks.get(index);
                    tas.markAsDone();

                    sb.append(gap);
                    sb.append("Nice! I've marked this task as done: \n");
                    sb.append(gap).append("  ");
                    sb.append(tas);
                    sb.append("\n");
                }

            } else if(temp.startsWith("todo")) {

                Todo todo = new Todo(temp.substring(5));
                tasks.add(todo);

                sb.append(gap);
                sb.append("Got it. I've added this task: \n");

                sb.append(gap).append("  ").append(todo);
                sb.append("\n");

                sb.append(gap);
                sb.append("Now you have ").append(tasks.size()).append(" task(s) in the list.\n");
            } else if(temp.startsWith("deadline")) {

                String[] s = temp.substring(9).split(" /by ");
                Deadline deadline = new Deadline(s[0], s[1]);
                tasks.add(deadline);

                sb.append(gap);
                sb.append("Got it. I've added this task: \n");

                sb.append(gap).append("  ").append(deadline);
                sb.append("\n");

                sb.append(gap);
                sb.append("Now you have ").append(tasks.size()).append(" task(s) in the list.\n");

            } else if(temp.startsWith("event")) {

                String[] s = temp.substring(6).split(" /at ");
                Event event = new Event(s[0], s[1]);
                tasks.add(event);

                sb.append(gap);
                sb.append("Got it. I've added this task: \n");

                sb.append(gap).append("  ").append(event);
                sb.append("\n");

                sb.append(gap);
                sb.append("Now you have ").append(tasks.size()).append(" task(s) in the list.\n");

            } else {
                tasks.add(new Task(temp));

                sb.append(gap);
                sb.append("added: ");
                sb.append(temp);
                sb.append("\n");

            }

            sb.append(gap);
            sb.append("____________________________________________________________\n");
            System.out.println(sb.toString());
        }


        String exit = "        ____________________________________________________________\n" +
                "        Bye. Hope to see you again soon!\n" +
                "        ____________________________________________________________\n";

        System.out.println(exit);
    }
}