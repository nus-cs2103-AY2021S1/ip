import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private static final String CHATBOT = "Bob: ";
    private static final String SKIPLINE = "\n";
    private static final String USER = SKIPLINE + "You: ";
    private static final String PATHNAME = "tasklist.txt";
    private static TaskList tasks = new TaskList();

    public static void main(String[] args) {
        try {
            File savedTaskList = new File(PATHNAME);
            SavedTaskList.readSavedTaskList(savedTaskList);
        } catch (FileNotFoundException e) {
            System.out.println(CHATBOT + SKIPLINE + "OOPS! Your tasklist has not been created");
            System.out.println("Please enter task commands to create your own tasklist :)");
            System.out.println();
        }
        
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
            boolean deleteTask = description.startsWith("delete");
            boolean showListOfCommands = description.equals("list");

            // Mark indicated task as done
            if (markDone) {
                try {
                    int lengthOfCommand = description.length();
                    int index = Integer.parseInt(description.substring(5, lengthOfCommand));
                    markTaskAsDone(tasks, index);
                } catch (TaskDoesNotExistException e) {
                    System.out.println(e.getMessage());
                } catch (NumberFormatException e) {
                    System.out.println(SKIPLINE + CHATBOT);
                    System.out.println("OOPS! Please enter a numerical number to mark tasks as done :)");
                }
                
            // Delete indicated task    
            } else if (deleteTask) {
                try {
                    int lengthOfCommand = description.length();
                    int index = Integer.parseInt(description.substring(7, lengthOfCommand));
                    deleteTaskFromList(tasks, index);
                } catch (TaskDoesNotExistException e) {
                    System.out.println(e.getMessage());
                } catch (NumberFormatException e) {
                    System.out.println(SKIPLINE + CHATBOT);
                    System.out.println("OOPS! Please enter a numerical number to delete tasks from your list :)");
                }
            }

            // Display list of tasks to user
            else if (showListOfCommands) {
                displayTaskList(tasks);

            // Add a new task (todos, deadlines or events) to the list
            } else {
                try {
                    handleUserCommands(tasks, description);
                } catch (InvalidUserCommandException e) {
                    System.out.println(e.getMessage());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(SKIPLINE + CHATBOT);
                    System.out.println("Sorry I am unable to create this task :(");
                    System.out.println("Please make sure that the task has a description and has the correct format");
                }
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

        // If list is empty, notify users that list if empty
        if (tasks.totalNumberOfTasks() == 0) {
            // Bob's response
            System.out.println("List is empty :(");
            
        // Else display the list to users
        } else {
            // Bob's response
            System.out.println("Here is your current list of tasks:");
            System.out.println(tasks);
        }
    }

    private static void updateTaskList(TaskList tasks, Task newTask) {
        System.out.println(SKIPLINE + CHATBOT);
        tasks.addNewTask(newTask);

        // Bob's response
        System.out.println("Noted! I have added the following task to your list:");
        System.out.println(newTask);
        System.out.println("You now have " + tasks.totalNumberOfTasks() + " task(s) in your list");
        
        System.out.println(SKIPLINE + "Saving updated tasklist...");
        try {
            SavedTaskList.editSavedTaskList(PATHNAME, tasks.toString());
            System.out.println("Successfully saved updated tasklist!");
        } catch (IOException e) {
            System.out.println("Sorry I was unable to find the file with your saved tasklist :(");
        }
    }

    private static void markTaskAsDone(TaskList tasks, int index) throws TaskDoesNotExistException {
        System.out.println(SKIPLINE + CHATBOT);
        
        // Check if index is valid
        if (index > 0 && index <= tasks.totalNumberOfTasks()) {
            Task doneTask = tasks.getTask(index -1);
            if (doneTask.isDone) {
                System.out.println("OOPS. It seems like this task has already been completed:");
                System.out.println(doneTask);
            } else {
                doneTask.markAsDone();

                // Bob's response
                System.out.println("Good job completing this task! I've marked this task as done:");
                System.out.println(doneTask);
                System.out.println("Keep up the good work :)");
            }
        } else {
            throw new TaskDoesNotExistException(index);
        }
    }
    
    private static void deleteTaskFromList(TaskList tasks, int index) throws TaskDoesNotExistException{
        System.out.println(SKIPLINE + CHATBOT);
        
        // Checks if index is valid
        if (index > 0 && index <= tasks.totalNumberOfTasks()) {
            Task deletedTask = tasks.getTask(index - 1);
            tasks.deleteTask(index - 1);

            // Bob's response
            System.out.println("Noted! I have deleted this task from your list:");
            System.out.println(deletedTask);
            System.out.println("You now have " + tasks.totalNumberOfTasks() + " task(s) in your list");
        } else {
            throw new TaskDoesNotExistException(index);
        }
        
    }

    private static void handleUserCommands(TaskList tasks, String command) throws InvalidUserCommandException{
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
        } else {
            System.out.println(SKIPLINE + CHATBOT);
            throw new InvalidUserCommandException("Sorry but I don't understand what this means :(");   
        }
    }
}
