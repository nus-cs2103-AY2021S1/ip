import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        sayHello();

        List<Task> taskList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while (handleCommand(command, taskList)) {
            command = scanner.nextLine();
        }
    }

    public static void deleteTask(String[] commands, List<Task> taskList) throws DukeException {
        int index = getTaskIndex(commands, taskList);
        Task task = taskList.remove(index);
        System.out.println(wrapDivider(
                "     Noted. I've removed this task: \n" +
                "       " + task.toString() + "\n" +
                "     Now you have "+ taskList.size() +" tasks in the list.\n"
        ));

    }

    private static int getTaskIndex(String[] commands, List<Task> taskList) throws DukeException {
        if (commands.length < 2) {
            throw new DukeException("☹ OOPS!!! Please put a number to select a task for the \"" +
                    commands[0] + "\" action!");
        }
        try {
            int index = Integer.parseInt(commands[1]);
            Task task = taskList.get(index - 1);
            return index - 1;
        }  catch (NumberFormatException nfe) {
            throw new DukeException("☹ OOPS!!! Please input an actual number e.g. 1, 2, 3.");
        } catch (IndexOutOfBoundsException iobe) {
            throw new DukeException("☹ OOPS!!! Please select a valid task.");
        }
    }

    private static void checkValidAddCommand(String[] commands) throws DukeException {
        if (commands.length < 2) {
            throw new DukeException("☹ OOPS!!! The description of a " + commands[0] + " cannot be empty.");
        }
    }

    private static boolean handleCommand(String commandLine, List<Task> taskList) {
        String[] commands = commandLine.split(" ", 2);
        try {
            switch (commands[0]) {
                case "todo":
                    checkValidAddCommand(commands);
                    addTask(new Todo(commands[1]), taskList);
                    break;
                case "deadline":
                    checkValidAddCommand(commands);
                    addTask(Deadline.create(commands[1]), taskList);
                    break;
                case "event":
                    checkValidAddCommand(commands);
                    addTask(Event.create(commands[1]), taskList);
                    break;
                case "list":
                    displayTaskList(taskList);
                    break;
                case "done":
                    markTaskDone(commands, taskList);
                    break;
                case "delete":
                    deleteTask(commands, taskList);
                    break;
                case "bye":
                    sayBye();
                    return false;
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch(DukeException dukeException) {
            System.out.println(wrapDivider("     " + dukeException.getMessage() + "\n"));
        }
        return true;
    }

    private static void addTask(Task task, List<Task> taskList) {
        taskList.add(task);
        System.out.println(wrapDivider(
                "     Got it. I've added this task: \n" +
                "       " + task.toString() + "\n" +
                "     Now you have "+ taskList.size() + " tasks in the list.\n"
        ));

    }

    private static void markTaskDone(String[] commands, List<Task> taskList) throws DukeException {
        int index = getTaskIndex(commands,taskList);
        Task task = taskList.get(index);
        task.markDone();
        System.out.println(wrapDivider(
                "     Nice! I've marked this task as done: \n" +
                        "       "+ task.toString() + "\n"
        ));
    }

    private static void echoAddCommand(String command) {
        System.out.println(wrapDivider("     added: " + command + "\n"));
    }

    private static void sayHello() {
        System.out.println(wrapDivider(
            "      ____        _        \n" +
            "     |  _ \\ _   _| | _____ \n" +
            "     | | | | | | | |/ / _ \\\n" +
            "     | |_| | |_| |   <  __/\n" +
            "     |____/ \\__,_|_|\\_\\___|\n" +
            "     Hello! I'm Thuya\n" +
            "     What may I do for you, sir/madam?\n")
        );
    }

    private static void sayBye() {
        System.out.println(wrapDivider("     Bye. Hope to see you again soon!\n"));
    }

    private static void displayTaskList(List<Task> textList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < textList.size(); i++) {
            stringBuilder.append("     " + (i+1) + "."+ textList.get(i) +"\n");
        }
        System.out.println(wrapDivider(stringBuilder.toString()));
    }

    private static String wrapDivider(String text) {
        return  "    ____________________________________________________________\n" +
                text +
                "    ____________________________________________________________\n" ;
    }
}
