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

    private void printTasks() {
        int counter = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task todo : tasks) {
            System.out.print(indentWord(Integer.toString(counter) + ". "));
            System.out.println(todo);
            counter++;
        }
    }

    private void markComplete(String index) {
        int intIndex = Integer.parseInt(index) - 1;
        tasks.get(intIndex).markDone();

        // Print todo that has been marked done
        System.out.println(indentWord("Nice! I've marked this task as done:"));
        System.out.println(indentWord(tasks.get(intIndex).toString()));
    }

    private void deleteTask(String index) {
        int intIndex = Integer.parseInt(index) - 1;
        Task removedTask = tasks.remove(intIndex);
        
        // Print response from Duke after deleting task
        System.out.println(indentWord("Noted. I've removed this task:"));
        System.out.println(indentWord(removedTask.toString()));
        System.out.println(indentWord(String.format("Now you have %s tasks in the list.", tasks.size())));
    }
    
    private void addTask(String command) throws DukeException {
        String[] taskArr = command.split(" ");
        String taskType = taskArr[0];
        Task task = new Task("");

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
            default:
                throw new DukeException();
        }


        // Add task
        tasks.add(task);
        // Print response from Duke for adding task
        System.out.println(indentWord("Got it. I've added this task:"));
        System.out.println(indentWord(task.toString()));
        System.out.println(indentWord(String.format("Now you have %s tasks in the list.", tasks.size())));
    }
}
