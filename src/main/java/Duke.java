import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    private final List<Task> list;
    public static final String divider = "----------------------------------------";

    public Duke() {
        this.list = new ArrayList<>();
    }

    public static void main(String[] args) {
        Duke session = new Duke(); // start a new session with JonasBot
        session.greet(); // greet the user
        session.execute(); // execute the bot to perform intended functions
        session.end(); // end the current session with JonasBot
    }

    public void greet() {
        String greeting = "  Hello! I am JonasBot! Nice to meet you :) \n" +
                "  \n  I am a bot that will keep track of all your tasks. \n" +
                "  \n  To view a list of all my commands, input '/commands' \n" +
                "  \n  Now that you are familiar with the commands, how may I assist you today?";
        System.out.println(Duke.divider);
        System.out.println(greeting);
        System.out.println(Duke.divider);
    }

    public void execute() {
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();
        String function = message.split(" ")[0];

        while (!function.equals("bye")) {
            try {
                System.out.println(Duke.divider);
                if (function.equals("/commands")) {
                    commands();
                } else if (function.isEmpty()) {
                    System.out.println("Please enter something!");
                } else if (function.equals("list")) {
                    retrieveList();
                } else if (function.equals("done")) {
                    completeTask(message);
                } else if (function.equals("todo") || function.equals("deadline") || function.equals("event")){
                    createTask(message);
                } else if (function.equals("delete")) {
                    deleteTask(message);
                } else {
                    handleFailedFunction();
                }
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println(Duke.divider);
            message = sc.nextLine();
            function = message.split(" ")[0];
        }
    }

    public void handleFailedFunction() throws InvalidFunctionException {
        String err = "Invalid Function! Your input has to begin with the following: \n" +
                "1. todo\n" +
                "2. deadline\n" +
                "3. event\n" +
                "4. list\n" +
                "5. done\n" +
                "6. bye\n" +
                "\nInput '/commands' for a list of all my commands. ";
        throw new InvalidFunctionException(err);
    }

    public void retrieveList() {
        if (this.list.isEmpty()) {
            System.out.println("Your list is empty. Add a new task!");
        } else {
            System.out.println("Here is a list of all your tasks:");
            for (int i = 0; i < this.list.size(); i++) {
                int index = i + 1;
                System.out.println("\t" + String.format("%d. %s", index, this.list.get(i)));
            }
        }
    }

    public void completeTask(String message) throws InvalidTaskException, InvalidFunctionException {
        try {
            int index = Integer.parseInt(message.split(" ")[1]);
            if (index > this.list.size() || index <= 0) {
                String err = "Invalid Task! The task does not exist, try again.";
                throw new InvalidTaskException(err);
            } else {
                if (this.list.get(index - 1).isDone) {
                    System.out.println("  This task has already been completed:");
                } else {
                    this.list.get(index - 1).markAsDone();
                    System.out.println("  Nice! I've marked this task as done:");
                }
                System.out.println("\t" + this.list.get(index - 1));
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            String err = "No Task ID found! Please input the ID of the task you wish to mark as completed.";
            throw new InvalidFunctionException(err);
        }
    }

    public void deleteTask(String message) throws InvalidTaskException, InvalidFunctionException {
        try {
            int index = Integer.parseInt(message.split(" ")[1]);
            if (index > this.list.size() || index <= 0) {
                String err = "Invalid Task! The task does not exist, try again.";
                throw new InvalidTaskException(err);
            } else {
                Task toRemove = this.list.get(index - 1);
                System.out.println("  Found it! This task has been successfully deleted:");
                System.out.println("\t" + toRemove);
                this.list.remove(index - 1);
                System.out.println("  You have " + this.list.size() + " tasks in your list now.");
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            String err = "No Task ID found! Please input the ID of the task you wish to delete.";
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
        String description;
        String time;
        Task toAdd = null;
        try {
            if (taskType.equals("todo")) {
                description = message.split("todo")[1];
                toAdd = new Todo(description);
            } else if (taskType.equals("deadline")) {
                String info = message.split("deadline")[1];
                description = info.split("/by")[0];
                time = info.split("/by")[1];
                toAdd = new Deadline(description, time);
            } else if (taskType.equals("event")) {
                String info = message.split("event")[1];
                description = info.split("/at")[0];
                time = info.split("/at")[1];
                toAdd = new Event(description, time);
            }
            this.list.add(toAdd);
            System.out.println("  Success! This task has been added:");
            System.out.println("\t" + toAdd);
            System.out.println("  You have " + list.size() + " tasks in your list now.");
        } catch (ArrayIndexOutOfBoundsException ex) {
            String err = "Your input does not meet the requirements. Input '/commands' to view a list of my commands. ";
            throw new InvalidTaskException(err);
        }
    }

    public void end() {
        String divider = "----------------------------------------";
        System.out.println(divider);
        System.out.println("  GoodBye and I hope to see you soon! Have a fantastic day! ");
        System.out.println(divider);
    }

}
