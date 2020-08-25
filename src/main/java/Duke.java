import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * Duke, more commonly known as Duck, is a Personal Assistant Chat Bot that
 * helps a person to keep track of various tasks.
 * Contains static attribute stored_task which stores task input from user.
 **/
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private final String line = "____________________________________________________________";

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(storage.load(), storage);
        } catch (DukeException e) {
            System.out.println(line);
            System.out.println(e.getMessage());
            System.out.println(line);
        }
    }

    //Represents command inputs as constants
    private enum Command {
        LIST("list"), BYE("bye"), DELETE("delete"), DONE("done"),
        TODO("todo"), DEADLINE("deadline"), EVENT("event");

        private final String input;

        Command(String input) {
            this.input = input;
        }

        public String getInput() {
            return input;
        }
    }

    /**
     * Checks if string contains only numbers.
     *
     * @param input input string to check
     * @return if string contains only numbers
     **/
    public static boolean isNumber(String input) {
        return input.matches("[0-9]+");
    }

    public String getStringFromArray(String[] inputArr, int indexFrom, int indexTo) {
        String output = "";
        for (int i = indexFrom; i < indexTo; i++){
            output += inputArr[i] + " ";
        }
        return output.substring(0, output.length() - 1);
    }

    public void run() {
        boolean isInProgram = true;
        String greeting_message = line +
                "\n Quack! I am Duck" +
                "\n How can I help you today?\n" + line;
        String exit_message = line +
                "\n Waddling off now. See you soon! \n" + line;
        Scanner sc = new Scanner(System.in);

        System.out.println(greeting_message);
        while (isInProgram) {
            try {
                String input = sc.nextLine();
                String[] inputInformation = input.split(" ");
                if (inputInformation[0].equals(Command.BYE.getInput())) {
                    System.out.println(exit_message);
                    isInProgram = false;
                } else if (inputInformation[0].equals(Command.LIST.getInput())) {
                    taskList.listStoredTasks();
                } else if (inputInformation[0].equals(Command.DONE.getInput())) {
                    if (inputInformation.length > 1 && isNumber(inputInformation[1])) {
                        int taskNumber = Integer.parseInt(inputInformation[1]);
                        taskList.markTaskAsDone(taskNumber);
                    } else {
                        throw new DukeException("You need to include your task number to mark done...");
                    }
                } else if (inputInformation[0].equals(Command.DELETE.getInput())) {
                    if (inputInformation.length > 1 && isNumber(inputInformation[1])) {
                        int taskNumber = Integer.parseInt(inputInformation[1]);
                        taskList.deleteTask(taskNumber);
                    } else {
                        throw new DukeException("You need to include your task number to delete...");
                    }
                } else if (inputInformation[0].equals(Command.TODO.getInput())) {
                    if (inputInformation.length > 1) {
                        String description = getStringFromArray(inputInformation, 1, inputInformation.length);
                        ToDo newTask = new ToDo(description);
                        taskList.addTask(newTask);
                    } else {
                        throw new DukeException("Your todo description can't be empty...");
                    }
                } else if (inputInformation[0].equals(Command.DEADLINE.getInput())) {
                    if (inputInformation.length > 3) {
                        int indexOfBy = Arrays.asList(inputInformation).indexOf("/by");
                        if (indexOfBy == -1) {
                            throw new DukeException("Did you include /by?");
                        } else if (indexOfBy == 1) {
                            throw new DukeException("Did you include a description?");
                        } else {
                            String description = getStringFromArray(inputInformation, 1, indexOfBy);
                            String by = getStringFromArray(inputInformation, indexOfBy + 1, inputInformation.length);
                            Deadline newTask = new Deadline(description, by);
                            taskList.addTask(newTask);
                        }
                    } else {
                        throw new DukeException("Your deadline description or deadline can't be empty...");
                    }
                } else if (inputInformation[0].equals(Command.EVENT.getInput())) {
                    if (inputInformation.length > 3) {
                        int indexOfAt = Arrays.asList(inputInformation).indexOf("/at");
                        if (indexOfAt == -1) {
                            throw new DukeException("Did you include /at?");
                        } else if (indexOfAt == 1) {
                            throw new DukeException("Did you include a description?");
                        } else {
                            String description = getStringFromArray(inputInformation, 1, indexOfAt);
                            String at = getStringFromArray(inputInformation, indexOfAt + 1, inputInformation.length);
                            Event newTask = new Event(description, at);
                            taskList.addTask(newTask);
                        }
                    } else {
                        throw new DukeException("Your event description or event period can't be empty...");
                    }
                } else {
                    throw new DukeException("My duck instincts tell me your input makes no sense...");
                }
            } catch (DukeException e) {
                System.out.println(line);
                System.out.println(e.getMessage());
                System.out.println(line);
            }
        }
        sc.close();
    }

    /**
     * Prints greeting message.
     * Scans for commands entered by the user, then stores input task into stored_task for 3 types of tasks:
     * ToDo, Deadline and Event.
     * Upon user command input "done " followed by the task number, task will be marked as done.
     * Upon user command input "list", stored task will be listed.
     * Upon user command input "bye", system is exited.
     * Upon user command input "delete", task is deleted.
     **/
    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.run();
    }
}
