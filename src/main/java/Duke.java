import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "____________________________________________________________\n";
        System.out.println(line
                + logo
                + "\n Hello, I'm Duke \n What can I do for you?\n"
                + line);

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        List<Task> taskList = new ArrayList<>();

        while(!command.equals("bye")) {
            try {
                //command = list
                if (command.equals("list")) {
                    System.out.print(line);
                    System.out.println(" Here are the tasks in your list:");
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println(" " + (i + 1) + ". " + taskList.get(i));
                    }
                    System.out.println(line);
                } else {
                    String[] commands = command.split(" ", 2);
                    //command = done
                    if (commands[0].equals("done")) {
                        int index = Integer.parseInt(commands[1]) - 1;
                        taskList.get(index).markAsDone();
                        System.out.println(line
                                + " Nice! I've marked this task as done: "
                                + "\n   " + taskList.get(index) + "\n"
                                + line);
                    } else if (commands[0].equals("todo")
                            || commands[0].equals("deadline")
                            || commands[0].equals("event")) { //add tasks
                        Task task = null;
                        //toDos
                        if (commands[0].equals("todo")) {
                            if(commands[1].isBlank()) {
                                throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
                            }
                            task = new Todo(commands[1]);
                        }
                        //deadline
                        if (commands[0].equals("deadline")) {
                            String key = " /by ";
                            if(commands[1].isBlank()) {
                                throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
                            }
                            if(commands[1].indexOf(key) == -1) {
                                throw new DukeException(" ☹ OOPS!!! The description of a deadline must include /by keywords");
                            }
                            String[] arguments = commands[1].split(key);
                            task = new Deadline(arguments[0], arguments[1]);
                        }
                        //event
                        if (commands[0].equals("event")) {
                            String key = " /at ";
                            if(commands[1].isBlank()) {
                                throw new DukeException(" ☹ OOPS!!! The description of a event cannot be empty.");
                            }
                            if(commands[1].indexOf(key) == -1) {
                                throw new DukeException(" ☹ OOPS!!! The description of a event must include /at keywords");
                            }
                            String[] arguments = commands[1].split(key);
                            task = new Event(arguments[0], arguments[1]);
                        }
                        System.out.println(line
                                + " Got it. I've added this task: ");
                        taskList.add(task);
                        System.out.println("   " + task
                                + "\n Now you have " + taskList.size() + " tasks in the list.\n"
                                + line);
                    } else { //meaningless command
                        throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
            } catch (DukeException e) {
                System.out.println(line
                        + e.msg + "\n"
                        + line);
            }
            command = sc.nextLine();
        }

        System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
    }
}
