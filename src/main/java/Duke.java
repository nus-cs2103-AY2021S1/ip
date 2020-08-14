import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String res = "";
        List<Task> data = new ArrayList<Task>();
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
                    System.out.printf("     %d.[%s] %s\n", i + 1, data.get(i).getStatusIcon(), data.get(i).getDescription());
                }
                System.out.println("    ____________________________________________________________");
            } else if(res.substring(0, 5).equals("done ")) {
                int n = Integer.valueOf(res.substring(5, res.length())) - 1;
                try {
                    data.get(n).done();
                    System.out.println("    ____________________________________________________________");
                    System.out.println("    Nice! I've marked this task as done: ");
                    System.out.printf("     [%s] %s\n", data.get(n).getStatusIcon(), data.get(n).getDescription());
                    System.out.println("    ____________________________________________________________");
                }catch(IndexOutOfBoundsException e) {
                    System.out.println("IndexOutOfBoundsException: " + e);
                }

            } else {
                data.add(new Task(res));
                System.out.println("    ____________________________________________________________");
                System.out.printf("     added: %s\n", res);
                System.out.println("    ____________________________________________________________");
            }
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
        }

        public String getDescription() {
            return this.description;
        }
        public void done() {
            this.isDone = true;
        }
    }

}
