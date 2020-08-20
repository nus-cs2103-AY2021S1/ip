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
                    System.out.println(i + "." + tasks[i - 1].getStatus());
                }
            }
            else if(cmd.substring(0, 5).equals("done ")) {
                int c = 0;
                for(int i = 5; i < cmd.length(); ++i) {
                    c = c * 10 + cmd.charAt(i) - '0';
                }
                System.out.println("Nice! I've marked this task as done:");
                tasks[c - 1].done();
                System.out.println(tasks[c - 1].getStatus());
            }
            else if(cmd.substring(0, 4).equals("todo")) {
                Todo tmp = new Todo(cmd.substring(5));
                System.out.println("Got it. I've added this task: ");
                System.out.println("  " + tmp.getStatus());
                tasks[numTask] = tmp;
                numTask++;
                System.out.println("Now you have " + numTask + " tasks in the list.");
            }
            else if(cmd.substring(0, 8).equals("deadline")) {
                String getName = "", getDeadline = "";
                for(int i = 0; i < cmd.length(); ++i) {
                    if(cmd.charAt(i) == '/' && cmd.charAt(i + 1) == 'b' && cmd.charAt(i + 2) == 'y') {
                        getDeadline = cmd.substring(i + 4);
                        getName = cmd.substring(9, i - 1);
                        break;
                    }
                }
                Deadline tmp = new Deadline(getName, getDeadline);
                System.out.println("Got it. I've added this task: ");
                System.out.println("  " + tmp.getStatus());
                tasks[numTask] = tmp;
                numTask++;
                System.out.println("Now you have " + numTask + " tasks in the list.");
            }
            else if(cmd.substring(0, 5).equals("event")) {
                String getName = "", getTime = "";
                for(int i = 0; i < cmd.length(); ++i) {
                    if(cmd.charAt(i) == '/' && cmd.charAt(i + 1) == 'a' && cmd.charAt(i + 2) == 't') {
                        getTime = cmd.substring(i + 4);
                        getName = cmd.substring(6, i - 1);
                        break;
                    }
                }
                Event tmp = new Event(getName, getTime);
                System.out.println("Got it. I've added this task: ");
                System.out.println("  " + tmp.getStatus());
                tasks[numTask] = tmp;
                numTask++;
                System.out.println("Now you have " + numTask + " tasks in the list.");
            }
        }
    }
}
