import java.io.*;
import java.util.Arrays;
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
        this.tasks = readFromFile();
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
            saveToFile();
        }
    }

    private ArrayList<Task> readFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        // Check if file exists and create file otherwise
        try {
            File savedFile = new File("ip_data.txt");
            if (!savedFile.exists()) {
                try {
                    savedFile.createNewFile();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
            String currLine;
            // Read from file
            BufferedReader reader = new BufferedReader(new FileReader(savedFile));
            while ((currLine = reader.readLine()) != null) {
                String[] taskArr = currLine.split("\\|");
                String taskType = taskArr[0];
                boolean done = taskArr[1] == "1";

                switch (taskType) {
                case ("T"):
                    tasks.add(new Todo(taskArr[2], done));
                    break;
                case ("D"):
                    tasks.add(new Deadline(taskArr[2], taskArr[3], done));
                    break;
                case ("E"):
                    tasks.add(new Event(taskArr[2], taskArr[3], done));
                    break;
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return tasks;
    }

    private void saveToFile() {
        File savedFile = new File("ip_data.txt");

        // Write to file
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(savedFile));
            for (Task task : tasks) {
                writer.write(task.getInfo());
                writer.newLine();
                writer.flush();
            }
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
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
            task = new Todo(command.substring(5).trim(), false);
            break;
        case ("deadline"):
            String deadlineContent = command.substring(9);
            String[] deadlineArr = deadlineContent.split("/");
            task = new Deadline(deadlineArr[0].trim(),  deadlineArr[1].substring(3).trim(), false);
            break;
        case("event"):
            String eventContent = command.substring(6);
            String[] eventArr = eventContent.split("/");
            task = new Event(eventArr[0].trim(), eventArr[1].substring(3).trim(), false);
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
