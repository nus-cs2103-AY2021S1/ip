import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String res;
        List<Task> data = new ArrayList<>();
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
        while (true) {
            res = sc.nextLine();
            if (res.equals("bye")) {
                break;
            } else if (res.equals("list")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     Here are the tasks in your list:");
                for (int i = 0; i < data.size(); i++) {
                    System.out.printf("     %d.%s\n", i + 1, data.get(i).toString());
                }
                System.out.println("    ____________________________________________________________");
            } else if(res.startsWith("done ")) {
                int n = Integer.parseInt(res.substring(5)) - 1;
                try {
                    data.get(n).done();
                    System.out.println("    ____________________________________________________________");
                    System.out.println("    Nice! I've marked this task as done: ");
                    System.out.printf("     [%s] %s\n", data.get(n).getStatusIcon(), data.get(n).getDescription());
                    System.out.println("    ____________________________________________________________");
                } catch(IndexOutOfBoundsException e) {
                    System.out.println("IndexOutOfBoundsException: " + e);
                }
            } else if(res.startsWith("todo ")) {
                Todo t = new Todo(res.substring(5));
                data.add(t);
                System.out.println("    ____________________________________________________________");
                System.out.println("     Got it. I've added this task: ");
                System.out.printf("       %s\n", t.toString());
                System.out.printf("     Now you have %d tasks in the list.\n", data.size());
                System.out.println("    ____________________________________________________________");
            } else if(res.startsWith("deadline ")) {
                String[] ss = res.split("/");
                Deadline t = new Deadline(ss[0].substring(9), ss[1].substring(3));
                data.add(t);
                System.out.println("    ____________________________________________________________");
                System.out.println("     Got it. I've added this task: ");
                System.out.printf("       %s\n", t.toString());
                System.out.printf("     Now you have %d tasks in the list.\n", data.size());
                System.out.println("    ____________________________________________________________");
            } else if(res.startsWith("event ")) {
                String[] ss = res.split("/");
                Event t = new Event(ss[0].substring(6), ss[1].substring(3));
                data.add(t);
                System.out.println("    ____________________________________________________________");
                System.out.println("     Got it. I've added this task: ");
                System.out.printf("       %s\n", t.toString());
                System.out.printf("     Now you have %d tasks in the list.\n", data.size());
                System.out.println("    ____________________________________________________________");
            } else {
                System.out.println("Error: Invalid command.");
            }
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

}
