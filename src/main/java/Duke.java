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
                    sb.append(ctr).append(".[");
                    sb.append(task.getStatusIcon());
                    sb.append("] ");
                    sb.append(task.description);
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
                    sb.append("[").append(tas.getStatusIcon()).append("] ");
                    sb.append(tas.description);
                    sb.append("\n");
                }
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