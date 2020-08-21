import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;


public class Duke {

    private ArrayList<Task> taskList;

    // Constructor for the bot
    public Duke(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    // Method to intiate the bot
    private static void startBot() throws DukeException, IOException {
        String welcome = "Hello I am Duke!\nHow can I help you?\n";
        System.out.println(welcome);

        Duke newBot = new Duke(new ArrayList<Task>());

        Storage storage = new Storage("/Users/nigelng/Desktop/Y2S1/CS2103T/IndivProj/data/duke.txt");

        newBot.taskList = storage.loadData();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String message = sc.next();
            if (message.equals("done")) {
                message += sc.nextLine();
                DoneCommand newDoneCommand = new DoneCommand(message, newBot.taskList.size());
                int taskIndex = newDoneCommand.getTaskIndex();
                newBot.markDone(newBot.taskList.get(taskIndex - 1));
            } else if (message.equals("todo")) {
                message += sc.nextLine();
                TodoCommand newTodoCommand = new TodoCommand(message);
                String description = newTodoCommand.getTodoDescription();
                Todo newTodo = new Todo(description);
                newBot.addTask(newTodo);
            } else if (message.equals("event")) {
                message += sc.nextLine();
                EventCommand newEventCommand = new EventCommand(message);
                String at = newEventCommand.getDateForTask();
                String description = newEventCommand.getDescriptionForTask();
                Event newEvent = new Event(description, at);
                newBot.addTask(newEvent);
            } else if (message.equals("deadline")) {
                message += sc.nextLine();
                DeadlineCommand newDeadlineCommand = new DeadlineCommand(message);
                String by = newDeadlineCommand.getDateForTask();
                String description = newDeadlineCommand.getDescriptionForTask();
                Deadline newDeadline = new Deadline(description, by);
                newBot.addTask(newDeadline);
            } else if (message.equals("list")) {
                message += sc.nextLine();
                ListCommand newListCommmand = new ListCommand(message);
                newListCommmand.checkCommandValidity();
                if (newBot.taskList.isEmpty()) {
                    System.out.println("\nThere are currently no tasks stored!\n");
                } else {
                    newBot.displayTasks();
                }
            } else if (message.equals("bye")) {
                System.out.println("\nBye! Have a nice day!\n");
                break;
            } else if (message.equals("delete")) {
                message += sc.nextLine();
                DeleteCommand newDeleteCommand = new DeleteCommand(message);
                int taskIndex = newDeleteCommand.getDeletedTaskIndex();
                if (taskIndex > newBot.taskList.size() || taskIndex < 1) {
                    throw new InvalidTaskNumberException();
                } else {
                    newBot.deleteTask(newBot.taskList.get(taskIndex - 1));
                }
            } else {
                throw new InvalidCommandException();
            }
        }
    }

    // Method to display all messages
    private void displayTasks() {
        int index = 1;
        System.out.println("\n");
        System.out.println("Here are the tasks in your tasklist:");
        for (Task task: this.taskList) {
            System.out.println(index + "." + task);
            index++;
        }
        System.out.println("\n");
    }


    // Method to mark task as done
    private void markDone(Task completedTask) {
        if (!completedTask.getStatus()) {
            System.out.println("\nNice! I have completed this task!");
            completedTask.markAsDone();
            System.out.println(" " + completedTask + "\n");
            int indexOfTask = this.taskList.indexOf(completedTask);
            this.taskList.get(indexOfTask).markAsDone();
        } else {
            System.out.println("\nThis task has already been completed!\n");
        }
    }

    // Method to add an impending task to the bot's task list
    private void addTask(Task newTask) {
        this.taskList.add(newTask);
        System.out.println("\nGot it. This task is now added.");
        System.out.println(" " + newTask);
        int tasksLeft = checkTasksLeft();
        System.out.println("You have " + tasksLeft + " tasks left in your list!\n");
    }

    // Method to check how many tasks are not completed
    private int checkTasksLeft() {
        int index = 0;
        for (Task task: this.taskList) {
            if (!task.getStatus()) {
                index++;
            }
        }
        return index;
    }

    private void deleteTask(Task deletedTask) {
        this.taskList.remove(deletedTask);
        System.out.println("\nGot it. Deleting task.....");
        System.out.println(" " + deletedTask);
        int tasksLeft = checkTasksLeft();
        System.out.println("You have " + tasksLeft  + " tasks left in your list!\n");

    }



    public static void main(String[] args) throws Exception {
        startBot();
    }
}