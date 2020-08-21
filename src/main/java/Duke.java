import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    private final List<Task> tasks;
    public static final String DIVIDER = "----------------------------------------";

    public Duke() {
        this.tasks = new ArrayList<>();
    }

    public static void main(String[] args) {
        Duke session = new Duke(); // start a new session with JonasBot
        session.greet(); // greet the user
        session.execute(); // execute the bot to perform intended functions
        session.end(); // end the current session with JonasBot
    }

    public void greet() {
        String text = "  ______  _____  __  ____     __     _______      \n" +
                " |__  __||  __ | | \\ |  |    /_ \\    |   ___|     \n" +
                "    | |  | | | | |  \\|  |   //_\\ \\   |  |___          \n" +
                " _  | |  | | | | | |\\   |  / ____ \\  |____  |   \n" +
                "| |_| |  | |_| | | | \\  | / /    \\ \\ _____| |         \n" +
                "|_____|  |_____| |_|  \\_|/_/      \\_\\|______|       \n" +
                "                  _____   ______  ________                            \n" +
                "                  |  _ \\ |  _  | |__   __|                  \n" +
                "                  | |_| || | | |    | |                      \n" +
                "                  |    / | | | |    | |         \n" +
                "                  |  _ \\ | |_| |    | |       \n" +
                "                  | |_| ||     |    | |         \n" +
                "                  |_____/|_____|    |_|                 \n";
        String greeting = "  Hello! I am JonasBot! Nice to meet you :) \n" +
                text +
                "  \n  I am a task manager bot that will keep track of all your tasks. \n" +
                "  \n  To view a list of all my commands, input '/commands' \n" +
                "  \n  Now that you are familiar with the commands, how may I assist you today?";
        System.out.println(Duke.DIVIDER);
        System.out.println(greeting);
        System.out.println(Duke.DIVIDER);
    }

    public void execute() {
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine().trim();
        String function = message.split(" ")[0];

        while (!message.equals("bye")) {
            try {
                System.out.println(Duke.DIVIDER);
                if (message.equals("/commands")) {
                    commands();
                } else if (message.isEmpty()) {
                    System.out.println("Please enter something!");
                } else if (message.equals("list")) {
                    retrieveList();
                } else if (function.equals("done")) {
                    int index = Integer.parseInt(message.split(" ")[1]);
                    completeTask(index);
                } else if (function.equals("todo") || function.equals("deadline") || function.equals("event")){
                    createTask(message);
                } else if (function.equals("delete")) {
                    int index = Integer.parseInt(message.split(" ")[1]);
                    deleteTask(index);
                } else {
                    handleFailedFunction();
                }
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println(Duke.DIVIDER);
            message = sc.nextLine().trim();
            function = message.split(" ")[0];
        }
        sc.close();
    }

    public void handleFailedFunction() throws InvalidFunctionException {
        String err = "Invalid Function! Input '/commands' for a list of all my commands. ";
        throw new InvalidFunctionException(err);
    }

    public void retrieveList() {
        if (this.tasks.isEmpty()) {
            System.out.println("Your list is empty. Add a new task!");
        } else {
            System.out.println("Here is a list of all your tasks:");
            for (int i = 0; i < this.tasks.size(); i++) {
                int index = i + 1;
                System.out.println("\t" + String.format("%d. %s", index, this.tasks.get(i)));
            }
        }
    }

    public void completeTask(int index) throws InvalidTaskException, InvalidFunctionException {
        try {
            if (index > this.tasks.size()) {
                String err = "Invalid Task! The task does not exist, try again.";
                throw new InvalidTaskException(err);
            } else if (index <= 0) {
                String err = "The task ID you provided is not valid. Input '/commands' to view a list of my commands. ";
                throw new InvalidFunctionException(err);
            } else {
                if (this.tasks.get(index - 1).isDone) {
                    System.out.println("  This task has already been completed:");
                } else {
                    this.tasks.get(index - 1).markAsDone();
                    System.out.println("  Nice! I've marked this task as done:");
                }
                System.out.println("\t" + this.tasks.get(index - 1));
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            String err = "No Task ID provided! Please input the ID of the task you wish to mark as completed.";
            throw new InvalidFunctionException(err);
        } catch (NumberFormatException ex) {
            String err = "Your input is not a recognised command. Input '/commands' to view a list of my commands. ";
            throw new InvalidFunctionException(err);
        }
    }

    public void deleteTask(int index) throws InvalidTaskException, InvalidFunctionException {
        try {
            if (index > this.tasks.size()) {
                String err = "Invalid Task! The task does not exist, try again.";
                throw new InvalidTaskException(err);
            } else if (index <= 0) {
                String err = "The task ID you provided is not valid. Input '/commands' to view a list of my commands. ";
                throw new InvalidFunctionException(err);
            } else {
                Task toRemove = this.tasks.get(index - 1);
                System.out.println("  Found it! This task has been successfully deleted:");
                System.out.println("\t" + toRemove);
                this.tasks.remove(index - 1);
                System.out.println("  You have " + this.tasks.size() + " tasks in your list now.");
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            String err = "No Task ID provided! Please input the ID of the task you wish to delete.";
            throw new InvalidFunctionException(err);
        } catch (NumberFormatException ex) {
            String err = "Your input is not a recognised command. Input '/commands' to view a list of my commands. ";
            throw new InvalidFunctionException(err);
        }
    }

    public void commands() {
        String commands = "  Below is a list of all the commands for my functions: \n" +
                "  1. Create a new task: \n" +
                "\t  1.1 Todo: 'todo' {task description}. For eg, todo eat \n" +
                "\t  1.2 Deadline: 'deadline' {task description} '/by' {due date}." +
                " For eg, deadline return book /by Sunday \n" +
                "\t  1.3 Event: 'event' {task description} '/at' {event time}." +
                " For eg, event project meeting /at Mon 2-4pm \n" +
                "  \n  2. To display all tasks in your list: 'list' \n" +
                "  \n  3. To mark a task as completed: 'done' {task ID}. For eg, 'done 2' \n" +
                "  \n  4. To delete a task: 'delete' {task ID}. For eg, 'delete 2' \n" +
                "  \n  5. To end this chat: 'bye' \n";
        System.out.println(commands);
    }

    public void createTask(String message) throws InvalidTaskException {
        String taskType = message.split(" ")[0];
        String description = "";
        String time = "";
        try {
            if (taskType.equals("todo")) {
                description = message.split("todo")[1];
                if (taskChecker("todo", description, time)) {
                    Task toAdd = new Todo(description);
                    this.tasks.add(toAdd);
                    System.out.println("  Success! This todo task has been added:");
                    System.out.println("\t" + toAdd);
                    System.out.println("  You have " + this.tasks.size() + " tasks in your list now.");
                } else {
                    String err = "Your todo task does not have a description. \n" +
                            "Type '/commands' to view the correct command for task creation! ";
                    throw new InvalidTaskException(err);
                }
            } else if (taskType.equals("deadline")) {
                String taskInfo = message.split("deadline")[1];
                String[] taskArray = taskInfo.split("/by");
                description = taskArray[0];
                time = taskArray[1];
                if (taskChecker("deadline", description, time)) {
                    Task toAdd = new Deadline(description, time);
                    this.tasks.add(toAdd);
                    System.out.println("  Success! This deadline task has been added:");
                    System.out.println("\t" + toAdd);
                    System.out.println("  You have " + this.tasks.size() + " tasks in your list now.");
                } else {
                    String err = "Your deadline task is missing a description. Please try again! \n" +
                            "Type '/commands' to view the correct command for task creation! ";
                    throw new InvalidTaskException(err);
                }
            } else if (taskType.equals("event")) {
                String taskInfo = message.split("event")[1];
                String[] taskArray = taskInfo.split("/at");
                description = taskArray[0];
                time = taskArray[1];
                if (taskChecker("event", description, time)) {
                    Task toAdd = new Event(description, time);
                    this.tasks.add(toAdd);
                    System.out.println("  Success! This event task has been added:");
                    System.out.println("\t" + toAdd);
                    System.out.println("  You have " + this.tasks.size() + " tasks in your list now.");
                } else {
                    String err = "Your event task is missing a description. Please try again! \n" +
                            "Type '/commands' to view the correct command for task creation! ";
                    throw new InvalidTaskException(err);
                }
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            String err = "Your " + taskType + " task description is empty and/or is missing a time stamp. " +
                    "The task cannot be created. \n " + "Type '/commands' to view the correct command for task creation.";
            throw new InvalidTaskException(err);
        }
    }

    public boolean taskChecker(String taskType, String description, String time) {
        if (taskType.equals("todo")) {
            return !description.isBlank();
        } else {
            return !description.isBlank() && !time.isBlank();
        }
    }

    public void end() {
        String divider = "----------------------------------------";
        System.out.println(divider);
        System.out.println("  GoodBye and I hope to see you soon! Have a fantastic day! ");
        System.out.println(divider);
    }

}
