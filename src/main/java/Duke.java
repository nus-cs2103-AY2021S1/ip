import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Duke {
    ArrayList<Task> tasks;
    
    static String ADD_TASK_LINE = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
    static String DONE_TASK_LINE = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    static String LIST_TASK_LINE = "________________________________________________________";
    static String BYE_LINE = "========================================================";
    static String INDENT = "    ";

    Duke() {
        tasks = new ArrayList<>();
        String basePath = System.getProperty("user.dir");
        Path path = Paths.get(basePath, "data", "tasks.txt");
        try {
            if (Files.exists(path)) {
                BufferedReader reader = new BufferedReader(new FileReader(path.toString()));
                String line = reader.readLine();
                while (line != null) {
                    String[] taskData = line.split(" // ");
                    if (taskData[0].equals("T")) {
                        if (taskData.length == 3) {
                            boolean done = Integer.parseInt(taskData[1]) == 1;
                            tasks.add(new ToDo(taskData[2], done));
                        }
                    } else if (taskData[0].equals("E")) {
                        if (taskData.length == 5) {
                            boolean done = Integer.parseInt(taskData[1]) == 1;
                            tasks.add(Event.of(taskData[2], taskData[3], taskData[4], done));
                        }
                    } else if (taskData[0].equals("D")) {
                        if (taskData.length == 5) {
                            boolean done = Integer.parseInt(taskData[1]) == 1;
                            tasks.add(Deadline.of(taskData[2], taskData[3], taskData[4], done));
                        }
                    }
                    line = reader.readLine();
                }
                reader.close();
            } else {
                Path newFilePath = Files.createFile(path);
            }
        } catch (UnsupportedOperationException | IOException | SecurityException e) {
            System.out.println(e);
            System.exit(1);
        }
    }
    
    void writeTasksToFile(String... data) {
        try {
            String basePath = new File("").getAbsolutePath();
            Path path = Paths.get(basePath, "data", "tasks.txt");
            if (Files.exists(path)) {
                BufferedWriter writer = new BufferedWriter(
                    new FileWriter(path.toString(), true));
                for (int i = 0; i < data.length; i++) {
                    writer.append(data[i]);
                    writer.newLine();
                }
                writer.close();
            }
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }
    }
    
    void displayError(String errorMessage) {
        System.out.println(INDENT + errorMessage);
    }
    
    void displayAddTaskSuccess(Task task, int taskCount) {
        System.out.println(
                INDENT + ADD_TASK_LINE + "\n"
                + INDENT + "Added task:" +"\n"
                + INDENT + INDENT + task.toString() + "\n"
                + INDENT + "You now have " + taskCount + " task(s) in the list.\n"
                + INDENT + ADD_TASK_LINE
        );
    }

    public boolean handleInput(String input) {
        String[] processedInput = input.split(" ");
        if (DukeCommand.equalsCommand(input, DukeCommand.EXIT)) {
            exit();
            return true;
        } else if (DukeCommand.equalsCommand(input, DukeCommand.LIST)) {
            System.out.println(INDENT + LIST_TASK_LINE);
            for (Task task : tasks) {
                System.out.println(INDENT + (tasks.indexOf(task) + 1) + "." + task.toString()
                );
            }
            if (tasks.size() == 0) {
                System.out.println(INDENT + "None");
            }
            System.out.println(INDENT + LIST_TASK_LINE);
            return false;
        } else if (DukeCommand.equalsCommand(processedInput[0], DukeCommand.DONE)) {
            if (processedInput.length == 1) {
                DukeException e = new DukeNoItemToMarkDoneException(input);
                displayError(e.toString());
                return false;
            }
            int index = Integer.parseInt(processedInput[1]);
            if (index < 1 || index > tasks.size()) {
                DukeException e = new DukeOutOfBoundsException(input);
                displayError(e.toString());
                return false;
            }
            Task task = tasks.get(index - 1);
            task.setDone();
            System.out.println(
                    INDENT + DONE_TASK_LINE + "\n"
                    + INDENT + "The following task has been marked as done:\n"
                    + INDENT + task.toString()
                    + "\n" + INDENT + DONE_TASK_LINE
            );
            return false;
        } else if (DukeCommand.equalsCommand(processedInput[0], DukeCommand.TODO)) {
            if (processedInput.length == 1) {
                DukeException e = new DukeNoDescriptionException(input);
                displayError(e.toString());
                return false;
            }
            String taskDetails = input.substring(5);
            Task task = new ToDo(taskDetails);
            tasks.add(task);
            displayAddTaskSuccess(task, tasks.size());
            return false;
        } else if (DukeCommand.equalsCommand(processedInput[0], DukeCommand.DEADLINE)) {
            if (processedInput.length < 5) {
                DukeException e = new DukeNoDescriptionException(input);
                displayError(e.toString());
                return false;
            }
            String[] taskDetails = input.substring(9).split(" /by ");
            String[] by = taskDetails[1].split(" ");
            Task task = Deadline.of(taskDetails[0], by[0], by[1]);
            if (task != null) {
                tasks.add(task);
                displayAddTaskSuccess(task, tasks.size());
            } 
            return false;
        } else if (DukeCommand.equalsCommand(processedInput[0], DukeCommand.EVENT)) {
            if (processedInput.length < 5) {
                DukeException e = new DukeNoDescriptionException(input);
                displayError(e.toString());
                return false;
            }
            String[] taskDetails = input.substring(6).split(" /at ");
            String[] at = taskDetails[1].split(" ");
            Task task = Event.of(taskDetails[0], at[0], at[1]);
            if (task != null) {
                tasks.add(task);
                displayAddTaskSuccess(task, tasks.size());
            }
            return false;
        } else if (DukeCommand.equalsCommand(processedInput[0], DukeCommand.DELETE)) {
            if (processedInput.length == 1) {
                DukeException e = new DukeNoItemToDeleteException(input);
                displayError(e.toString());
                return false;
            }
            int index = Integer.parseInt(processedInput[1]);
            if (index < 1 || index > tasks.size()) {
                DukeException e = new DukeOutOfBoundsException(input);
                displayError(e.toString());
                return false;
            }
            Task task = tasks.get(index - 1);
            tasks.remove(index - 1);
            System.out.println(
                    INDENT + DONE_TASK_LINE + "\n"
                            + INDENT + "The following task has been removed:\n"
                            + INDENT + task.toString() + "\n"
                            + INDENT + "You now have " + tasks.size() + " task(s) in the list.\n"
                            + INDENT + DONE_TASK_LINE
            );
            return false;
        } else {
            DukeException e = new DukeUnknownCommandException(input);
            displayError(e.toString());
            return false;
        }
    }

    void exit() {
        try {
            String basePath = System.getProperty("user.dir");
            Path path = Paths.get(basePath, "data", "tasks.txt");
            if (Files.deleteIfExists(path)) {
                Path newFilePath = Files.createFile(path);
                String[] data = new String[tasks.size()];
                for (int i = 0; i < tasks.size(); i++) {
                    data[i] = tasks.get(i).toDataString();
                }
                writeTasksToFile(data);
            }
            System.out.println(
                    INDENT + BYE_LINE + "\n"
                            + INDENT + "Goodbye\n"
                            + INDENT + BYE_LINE
            );
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner scanner = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello my name\n" + logo + "\nHow may I help?");

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            boolean exit = duke.handleInput(input);
            if (exit) {
                scanner.close();
                System.exit(0);
            }
        }
    }
}
