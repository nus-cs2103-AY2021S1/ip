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
            //command = list
            if(command.equals("list")) {
                System.out.print(line);
                System.out.println(" Here are the tasks in your list:");
                for(int i = 0; i < taskList.size(); i++) {
                    System.out.println(" " + (i + 1) + ". " + taskList.get(i));
                }
                System.out.println(line);
            } else {
                String[] commands = command.split(" ", 2);
                //command = done
                if(commands[0].equals("done")) {
                    int index = Integer.parseInt(commands[1]) - 1;
                    taskList.get(index).markAsDone();
                    System.out.println(line
                            + " Nice! I've marked this task as done: "
                            + "\n   " + taskList.get(index) + "\n"
                            + line);
                } else { //add tasks
                    System.out.println(line
                            + " Got it. I've added this task: ");
                    Task task = null;
                    //toDos
                    if(commands[0].equals("todo")) {
                        task = new Todo(commands[1]);
                    }
                    //deadline
                    if(commands[0].equals("deadline")) {
                        String[] arguments = commands[1].split(" /by ");
                        task = new Deadline(arguments[0], arguments[1]);
                    }
                    //event
                    if(commands[0].equals("event")) {
                        String[] arguments = commands[1].split(" /at ");
                        task = new Event(arguments[0], arguments[1]);
                    }
                    taskList.add(task);
                    System.out.println("   " + task
                            + "\n Now you have " + taskList.size() + " tasks in the list.\n"
                            + line);
                }
            }
            command = sc.nextLine();
        }

        System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
    }
}
