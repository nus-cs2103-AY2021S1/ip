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

        Storage storage = new Storage("data/duke.txt");
        List<Task> taskList;
        try {
            taskList = storage.load();
        } catch (DukeException e) {
            System.out.println(line
                    + e.msg + "\n"
                    + line);
            return;
        }

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

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
                    switch (commands[0]) {
                        case "done": {
                            if (!isNumeric(commands[1])) {
                                throw new DukeException(" ☹ OOPS!!! The usage of done command is incorrect.");
                            }
                            int index = Integer.parseInt(commands[1]) - 1;
                            taskList.get(index).markAsDone();
                            System.out.println(line
                                    + " Nice! I've marked this task as done: "
                                    + "\n   " + taskList.get(index) + "\n"
                                    + line);
                            break;
                        }
                        case "delete": {
                            if (!isNumeric(commands[1])) {
                                throw new DukeException(" ☹ OOPS!!! The usage of delete command is incorrect.");
                            }
                            int index = Integer.parseInt(commands[1]) - 1;
                            System.out.println(line
                                    + " Noted. I've removed this task: "
                                    + "\n   " + taskList.get(index)
                                    + "\n Now you have " + (taskList.size() - 1) + " tasks in the list.\n"
                                    + line);
                            taskList.remove(index);
                            break;
                        }
                        case "todo":
                        case "deadline":
                        case "event":  //add tasks
                            Task task = createTask(commands);
                            System.out.println(line
                                    + " Got it. I've added this task: ");
                            taskList.add(task);
                            System.out.println("   " + task
                                    + "\n Now you have " + taskList.size() + " tasks in the list.\n"
                                    + line);
                            break;
                        default:  //meaningless command
                            throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
                storage.store(taskList);
            } catch (DukeException e) {
                System.out.println(line
                        + e.msg + "\n"
                        + line);
            }
            command = sc.nextLine();
        }

        System.out.println(line + " Bye. Hope to see you again soon!\n" + line);
    }

    private static boolean isNumeric(String str){
        for (int i = str.length(); --i >= 0;) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        } return true;
    }

    private static Task createTask(String[] commands) throws DukeException{
        switch (commands[0]) {
            case "todo":
                if (commands.length < 2 || commands[1].isBlank()) {
                    throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
                }
                return new Todo(commands[1]);
            case "deadline": {
                String key = " /by ";
                if (commands[1].isBlank()) {
                    throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                if (!commands[1].contains(key)) {
                    throw new DukeException(" ☹ OOPS!!! The description of a deadline must contain /by keywords");
                }
                String[] arguments = commands[1].split(key);
                return new Deadline(arguments[0], arguments[1]);
            }
            case "event": {
                String key = " /at ";
                if (commands[1].isBlank()) {
                    throw new DukeException(" ☹ OOPS!!! The description of a event cannot be empty.");
                }
                if (!commands[1].contains(key)) {
                    throw new DukeException(" ☹ OOPS!!! The description of a event must contain /at keywords");
                }
                String[] arguments = commands[1].split(key);
                return new Event(arguments[0], arguments[1]);
            }
            default: {
                return null;
            }
        }
    }
}
