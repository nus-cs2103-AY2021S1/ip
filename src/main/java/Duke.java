import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    private static String DATA_PATHNAME = "data/duke.txt";

    private TaskList tasks;
    private Storage storage;

    private Ui ui = new Ui();

    String convertTaskToText (Task task) {
        if (task instanceof TodoTask) {
            return "T" + " | " + (task.isDone ? "1" : "0") + " | " + task.description;
        } else if (task instanceof DeadlineTask) {
            return "D" + " | " + (task.isDone ? "1" : "0") + " | " + task.description + " | " +
                    ((DeadlineTask) task).deadline.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        } else {
            return "E" + " | " + (task.isDone ? "1" : "0") + " | " + task.description + " | " +
                    ((EventTask) task).timing.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
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
                //int currentIndex = tasks.indexOf(task);
                //fileContent.set(currentIndex, convertTaskToText(task));
                break;
            }
            case DELETE: {
                fileContent.remove(convertTaskToText(task));
            }
        }

        Files.write(Paths.get(DATA_PATHNAME),fileContent);
        fw.close();
    }


    void initializeChatbot() {
        ui.greet();
        storage = new Storage(DATA_PATHNAME);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            e.printStackTrace();
            tasks = new TaskList();
        }
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
                        ui.exit();
                        hasEnded = true;
                        break;
                    }
                    case LIST: {
                        ui.listTasks(tasks);
                        break;
                    }
                    case TODO: {
                        String task = sc.nextLine().trim();
                        if (task.isEmpty()) {
                            throw new InvalidInputException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        tasks.addTask(task,null,TaskType.TODO);
                        System.out.println(tasks);
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
                        tasks.addTask(task[0], task[1], TaskType.DEADLINE);
                        System.out.println(tasks);
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
                        tasks.addTask(task[0], task[1], TaskType.EVENT);
                        System.out.println(tasks);
                        break;
                    }
                    case DONE: {
                        int index = sc.nextInt();
                        if (index > tasks.size() || index < 1) {
                            throw new InvalidIndexException("☹ OOPS!!! There is no such task.");
                        }
                        tasks.completeTask(index);
                        break;
                    }
                    case DELETE: {
                        int index = sc.nextInt();
                        if (index > tasks.size() || index < 1) {
                            throw new InvalidIndexException("☹ OOPS!!! There is no such task.");
                        }
                        tasks.deleteTask(index);
                        System.out.println(tasks);
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
