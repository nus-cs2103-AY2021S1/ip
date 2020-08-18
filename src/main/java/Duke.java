import java.util.Scanner;

public class Duke {
    private static final String CHATBOT = "Bob: ";
    private static final String SKIPLINE = "\n";
    private static final String USER = SKIPLINE + "You: ";

    public static void main(String[] args) {
        TaskList tasks = new TaskList();

        // Greetings
        System.out.println(CHATBOT + "Hey there! I'm Bob" + SKIPLINE + "What can I do for you today?");
        System.out.println(USER);

        Scanner sc = new Scanner(System.in);

        while (true) {
            String description = sc.nextLine();

            boolean exit = description.equals("bye");

            // Exit chatbot
            if (exit) {
                break;
            }

            boolean markDone = description.startsWith("done");
            boolean showListOfCommands = description.equals("list");

            // Mark indicated task as done
            if (markDone) {
                int lengthOfCommand = description.length();
                int index = Integer.parseInt(description.substring(5, lengthOfCommand));
                markTaskAsDone(tasks, index - 1);
            }

            // Display list of tasks to user
            else if (showListOfCommands) {
                displayTaskList(tasks);

            // Add a new task (todos, deadlines or events) to the list
            } else {
                handleUserCommands(tasks, description);
            }

            // Prompt user commands
            System.out.println(USER);

        }

        sc.close();

        // Exit chatbot
        System.out.println(SKIPLINE + CHATBOT + "Goodbye! Have a nice day :D");
    }

    private static void displayTaskList(TaskList tasks) {
        System.out.println(SKIPLINE + CHATBOT);

        if (tasks.totalNumberOfTasks() == 0) {
            // Bob's response
            System.out.println("List is empty :(");
        } else {
            // Bob's response
            System.out.println("Here is your current list of tasks:");
            System.out.println(tasks);
        }
    }

    private static void updateTaskList(TaskList tasks, Task newTask) {
        tasks.addNewTask(newTask);

        // Bob's response
        System.out.println();
        System.out.println(CHATBOT + SKIPLINE + "Noted! I have added the following task to your list:");
        System.out.println(newTask);
        System.out.println("You now have " + tasks.totalNumberOfTasks() + " task(s) in your list");
    }

    private static void markTaskAsDone(TaskList tasks, int index) {
        System.out.println(SKIPLINE + CHATBOT);

        Task doneTask = tasks.getTask(index);
        doneTask.markAsDone();

        // Bob's response
        System.out.println("Good job completing this task! I've marked this task as done:");
        System.out.println(doneTask);
        System.out.println("Keep up the good work :)");
    }

    private static void handleUserCommands(TaskList tasks, String command) {
        if (command.startsWith("deadline")) {
            String[] deadlineInformation;

            // Retrieve deadline description and deadline date
            deadlineInformation = command.split("/by ");
            String description = deadlineInformation[0].substring(9);
            String deadline = deadlineInformation[1];

            Deadline newDeadlineTask = new Deadline(description, deadline);
            updateTaskList(tasks, newDeadlineTask);
        } else if (command.startsWith("event")) {
            String[] eventInformation;

            // Retrieve event description and event date
            eventInformation = command.split("/at ");
            String description = eventInformation[0].substring(6);
            String event = eventInformation[1];

            Event newEventTask = new Event(description, event);
            updateTaskList(tasks, newEventTask);
        } else if (command.startsWith("todo")) {
            // Retrieve todos description
            String description = command.substring(5);

            Todo newTodoTask = new Todo(description);
            updateTaskList(tasks, newTodoTask);
        }
    }
}
