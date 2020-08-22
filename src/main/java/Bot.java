import java.util.Scanner;
import java.util.ArrayList;

public class Bot {
    static String logo = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    static String prompt = "What can I do for you?";
    static String farewellMsg = "Bye. Hope to see you again soon!";
    static String indentation = "    ";
    static String demarcation = Bot.indentation + "-------------------------------------------------------------------";

    ArrayList<Task> tasks;

    Bot() {
        this.tasks = new ArrayList<>(100);
    }

    public void interact() {
        System.out.println("Hello from\n" + Bot.logo);
        System.out.println(Bot.prompt);

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String command = scanner.nextLine();
            String[] commandArr = command.split(" ");
            String commandType = commandArr[0];
            
            System.out.println(Bot.demarcation);
            // Dispatch respective handlers depending on command
            switch (commandType) {
            case ("bye"):
                System.out.println(indentWord(farewellMsg));
                return;
            case ("list"):
                printTasks();
                break;
            case ("delete"):
                deleteTask(commandArr[1]);
                break;
            case ("done"):
                markComplete(commandArr[1]);
                break;
            default:
                try {
                    addTask(command);
                } catch (DukeException e) {
                    System.out.println(indentWord(e.getMessage()));
                }
                break;
            }
            System.out.println(Bot.demarcation);
        }
    }

    private String indentWord(String word) {
        return Bot.indentation + word;
    }

    private int getNumTasks() {
        int counter = 0;
        for (Task task : tasks) {
            if (!task.isDone()) counter++;
        }
        return counter;
    }

    private void printTasks() {
        int counter = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task todo : tasks) {
            System.out.print(indentWord(counter + ". "));
            System.out.println(todo);
            counter++;
        }
    }

    private void printTask(Task task, Action action) {
        String message = "";
        switch (action) {
        case ADD:
            message = "Got it. I've added this task:";
            break;
        case DONE:
            message = "Nice! I've marked this task as done:";
            break;
        case DELETE:
            message = "Noted. I've removed this task:";
            break;
        default:
            break;
        }
        System.out.println(indentWord(message));
        System.out.println(indentWord(task.toString()));
        System.out.println(indentWord(String.format("Now you have %s tasks in the list.", getNumTasks())));
    }

    private void markComplete(String index) {
        int intIndex = Integer.parseInt(index) - 1;
        tasks.get(intIndex).markDone();

        // Print todo that has been marked done
        printTask(tasks.get(intIndex), Action.DONE);
    }

    private void deleteTask(String index) {
        int intIndex = Integer.parseInt(index) - 1;
        Task removedTask = tasks.remove(intIndex);
        
        // Print response from Duke after deleting task
        printTask(removedTask, Action.DELETE);
    }
    
    private void addTask(String command) throws DukeException {
        String[] taskArr = command.split(" ");
        String taskType = taskArr[0];
        Task task;

        switch (taskType) {
        case ("todo"):
            // Only has the word todo
            if (taskArr.length == 1) throw new EmptyTodoException();
            task = new Todo(command.substring(5));
            break;
        case ("deadline"):
            String deadlineContent = command.substring(9);
            String[] deadlineArr = deadlineContent.split("/");
            task = new Deadline(deadlineArr[0], deadlineArr[1]);
            break;
        case("event"):
            String eventContent = command.substring(6);
            String[] eventArr = eventContent.split("/");
            task = new Event(eventArr[0], eventArr[1]);
            break;
        default:
            throw new DukeException();
        }


        // Add task
        tasks.add(task);
        // Print response from Duke for adding task
        printTask(task, Action.ADD);
    }
}
