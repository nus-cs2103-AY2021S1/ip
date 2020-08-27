import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
        ArrayList<Task> tasks = new ArrayList<Task>();
        while(true) {
            String cmd = myScanner.nextLine();
            if(cmd.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if(cmd.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for(int i = 1; i <= tasks.size(); ++i) {
                    System.out.println(i + "." + tasks.get(i - 1).getStatus());
                }
            }
            else if(cmd.length() >= 4 && cmd.substring(0, 4).equals("done")) {
                int c = 0;
                for(int i = 5; i < cmd.length(); ++i) {
                    c = c * 10 + cmd.charAt(i) - '0';
                }
                System.out.println("Nice! I've marked this task as done:");
                tasks.get(c - 1).done();
                System.out.println(tasks.get(c - 1).getStatus());
            }
            else if(cmd.length() >= 4 && cmd.substring(0, 4).equals("todo")) {
                try {
                    checkCmd(cmd, 4, "☹ OOPS!!! The description of a todo cannot be empty.");
                    String getName = cmd.substring(5);
                    Todo tmp = new Todo(getName);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println("  " + tmp.getStatus());
                    tasks.add(tmp);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                }
                catch (DukeException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            else if(cmd.length() >= 8 &&cmd.substring(0, 8).equals("deadline")) {
                try {
                    checkCmd(cmd, 8, "☹ OOPS!!! The description of a deadline cannot be empty.");
                    String getName = "", getDeadline = "";
                    for (int i = 0; i < cmd.length(); ++i) {
                        if (cmd.charAt(i) == '/' && cmd.charAt(i + 1) == 'b' && cmd.charAt(i + 2) == 'y') {
                            getDeadline = cmd.substring(i + 4);
                            getName = cmd.substring(9, i - 1);
                            break;
                        }
                    }
                    getDeadline = formatDate(getDeadline);
                    Deadline tmp = new Deadline(getName, getDeadline);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println("  " + tmp.getStatus());
                    tasks.add(tmp);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                }
                catch (DukeException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            else if(cmd.length() >= 5 &&cmd.substring(0, 5).equals("event")) {
                try {
                    checkCmd(cmd, 5, "☹ OOPS!!! The description of a event cannot be empty.");
                    String getName = "", getTime = "";
                    for (int i = 0; i < cmd.length(); ++i) {
                        if (cmd.charAt(i) == '/' && cmd.charAt(i + 1) == 'a' && cmd.charAt(i + 2) == 't') {
                            getTime = cmd.substring(i + 4);
                            getName = cmd.substring(6, i - 1);
                            break;
                        }
                    }
                    getTime = formatDate(getTime);
                    Event tmp = new Event(getName, getTime);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println("  " + tmp.getStatus());
                    tasks.add(tmp);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    //
                }
                catch (DukeException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            else if(cmd.length() >= 6 && cmd.substring(0, 6).equals("delete")){
                int c = 0;
                for(int i = 7; i < cmd.length(); ++i) {
                    c = c * 10 + cmd.charAt(i) - '0';
                }
                System.out.println("Noted. I've removed this task: ");
                System.out.println(tasks.get(c - 1).getStatus());
                tasks.remove(c - 1);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            }
            else System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static String formatDate(String str) {
        LocalDate d = LocalDate.parse(str);
        return d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public static void checkCmd(String cmd, int len, String Ex) throws DukeException {
        if(cmd.length() == len) throw new DukeException(Ex);
    }
}
