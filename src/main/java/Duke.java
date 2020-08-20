import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner myScanner = new Scanner(System.in);
        Task tasks[] = new Task[100];
        int numTask = 0;
        while(true) {
            String cmd = myScanner.nextLine();
            if(cmd.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if(cmd.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for(int i = 1; i <= numTask; ++i) {
                    System.out.println(i + ".[" + tasks[i - 1].getStatusIcon() + "] " + tasks[i - 1].getDescription());
                }
            }
            else if(cmd.substring(0, 5).equals("done ")) {
                int c = 0;
                for(int i = 5; i < cmd.length(); ++i) {
                    c = c * 10 + cmd.charAt(i) - '0';
                }
                System.out.println("Nice! I've marked this task as done:");
                tasks[c - 1].done();
                System.out.println("[" + tasks[c - 1].getStatusIcon() + "] " + tasks[c - 1].getDescription());
            }
            else {
                tasks[numTask] = new Task(cmd);
                numTask++;
                System.out.println("added: " + cmd);
            }
        }
    }
}
