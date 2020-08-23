import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    private static String DATA_PATHNAME = "data/duke.txt";

    private List<Task> tasks = new ArrayList<>();

    void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    String convertTaskToText (Task task) {
        if (task instanceof TodoTask) {
            return "T" + " | " + (task.isDone ? "1" : "0") + " | " + task.description;
        } else if (task instanceof DeadlineTask) {
            return "D" + " | " + (task.isDone ? "1" : "0") + " | " + task.description + " | " + ((DeadlineTask) task).deadline;
        } else {
            return "E" + " | " + (task.isDone ? "1" : "0") + " | " + task.description + " | " + ((EventTask) task).timing;
        }
    }

    void writeToFile(FileWriterCommand command, Task task) throws IOException {
        FileWriter fw = new FileWriter(DATA_PATHNAME, true);
        List<String> fileContent = Files.readAllLines(Paths.get(DATA_PATHNAME));
        switch (command) {
            case APPEND: {
                fileContent.add(convertTaskToText(task));
                break;
            }
            case UPDATE: {
                int currentIndex = tasks.indexOf(task);
                fileContent.set(currentIndex, convertTaskToText(task));
                break;
            }
            case DELETE: {
                fileContent.remove(convertTaskToText(task));
            }
        }

        Files.write(Paths.get(DATA_PATHNAME),fileContent);
        fw.close();
    }

    void addTask(String task, String date, TaskType taskType) throws DukeException {
        try {
            Task t;
            switch (taskType) {
                case TODO: {
                    t = new TodoTask(task);
                    tasks.add(t);
                    break;
                }
                case DEADLINE: {
                    t = new DeadlineTask(task, date);
                    tasks.add(t);
                    break;
                }
                case EVENT: {
                    t = new EventTask(task, date);
                    tasks.add(t);
                    break;
                }
                default:
                    throw new DukeException("Invalid Task Type");
            }

            writeToFile(FileWriterCommand.APPEND, t);
            System.out.println("Got it. I've added this task:\n " + t);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (IOException exception) {
            System.out.println(exception);
        }

    }

    void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i ++) {
            System.out.println(i + ". " + tasks.get(i - 1));
        }
    }

    void completeTask(int index) {
        try {
            Task completedTask = tasks.get(index - 1);
            completedTask.markAsDone();
            writeToFile(FileWriterCommand.UPDATE, completedTask);
            System.out.println("Nice! I've marked this task as done:\n " + completedTask);
        } catch (IOException exception) {
            System.out.println(exception);
        }
    }

    void deleteTask (int index) {
        try  {
        Task deletedTask = tasks.get(index - 1);
        tasks.remove(index - 1);

        writeToFile(FileWriterCommand.DELETE, deletedTask);

        System.out.println("Noted. I've removed this task:\n" + deletedTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");

        } catch (IOException exception) {
            System.out.println(exception);
        }
    }

    void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    void loadTasks() {
        try {
            File localTasks = new File(DATA_PATHNAME);
            // Create Scanner using file as source
            Scanner sc = new Scanner(localTasks);
            while (sc.hasNext()) {
                String[] details = sc.nextLine().split(" \\| ");

                switch (details[0]) {
                    case "T": {
                        TodoTask todoTask = new TodoTask(details[2]);
                        if (details[1].equals("1")) {
                            todoTask.markAsDone();
                        }
                        tasks.add(todoTask);
                        break;
                    }
                    case "D": {
                        DeadlineTask deadlineTask = new DeadlineTask(details[2], details[3]);
                        if (details[1].equals("1")) {
                            deadlineTask.markAsDone();
                        }
                        tasks.add(deadlineTask);
                        break;
                    }
                    case "E": {
                        EventTask eventTask = new EventTask(details[2], details[3]);
                        if (details[1].equals("1")) {
                            eventTask.markAsDone();
                        }
                        tasks.add(eventTask);
                        break;
                    }
                }
            }
        } catch (FileNotFoundException exception) {
            System.out.println(exception);
        }
    }


    void initializeChatbot() {
        greet();
        loadTasks();
        Scanner sc = new Scanner(System.in);
        boolean hasEnded = false;
        while (!hasEnded) {
            try {
                Command command = Command.getCommand(sc.next());
                //Check if command is invalid
                if (command == null ) {
                    //ignore remaining words after invalid command
                    sc.nextLine();
                    throw new InvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(.");
                }

                switch(command) {
                    case BYE: {
                        exit();
                        hasEnded = true;
                        break;
                    }
                    case LIST: {
                        listTasks();
                        break;
                    }
                    case TODO: {
                        String task = sc.nextLine().trim();
                        if (task.isEmpty()) {
                            throw new InvalidInputException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        addTask(task,null,TaskType.TODO);
                        break;
                    }
                    case DEADLINE: {
                        String[] task = sc.nextLine().trim().split(" /by ");
                        if (task[0].isEmpty()) {
                            throw new InvalidInputException("☹ OOPS!!! The description of a deadline task cannot be empty.");
                        }
                        if (task.length < 2) {
                            throw new InvalidInputException("☹ OOPS!!! The deadline of a deadline task cannot be empty.");
                        }
                        addTask(task[0], task[1], TaskType.DEADLINE);
                        break;
                    }
                    case EVENT: {
                        String[] task = sc.nextLine().trim().split(" /at ");
                        if (task[0].isEmpty()) {
                            throw new InvalidInputException("☹ OOPS!!! The description of an event task cannot be empty.");
                        }
                        if (task.length < 2) {
                            throw new InvalidInputException("☹ OOPS!!! The timing of an event task cannot be empty.");
                        }
                        addTask(task[0], task[1], TaskType.EVENT);
                        break;
                    }
                    case DONE: {
                        int index = sc.nextInt();
                        if (index > tasks.size() || index < 1) {
                            throw new InvalidIndexException("☹ OOPS!!! There is no such task.");
                        }
                        completeTask(index);
                        break;
                    }
                    case DELETE: {
                        int index = sc.nextInt();
                        if (index > tasks.size() || index < 1) {
                            throw new InvalidIndexException("☹ OOPS!!! There is no such task.");
                        }
                        deleteTask(index);
                        break;
                    }
                    default: {
                        break;
                    }
                }
            } catch (DukeException exception) {
                System.out.println(exception);
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();

        duke.initializeChatbot();

    }
}
