import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    private final String VERSION_NUMBER = "1.0.0";
    private final Scanner SC = new Scanner(System.in);
    private final Path DUKE_DATA_FILE_PATH = Paths.get("data", "duke.txt");
    private final Path DUKE_DATA_DIR_PATH = Paths.get("data");
    private final String NEW_LINE = "\n";
    private final String HORIZONTAL_LINE =
            "    ____________________________________________________________";
    private final String PADDING = "      ";

    private enum Commands {
        EXIT("BYE"), DELETE("DELETE "), LIST("LIST"),
        DONE("DONE "), TODO("TODO "), EVENT("EVENT "),
        DEADLINE("DEADLINE "), DELETEALL("DELETE ALL");
        private final String str;
        Commands(String str){
            this.str = str;
        }
        public String getString() {
            return this.str;
        }
    }

    private final String MESSAGE_TEMPLATE = HORIZONTAL_LINE + NEW_LINE + PADDING + "%s"
            + NEW_LINE + HORIZONTAL_LINE + NEW_LINE + NEW_LINE;
    private final String MESSAGE_TEMPLATE_VERBAL = HORIZONTAL_LINE + NEW_LINE + PADDING + "Deuk: %s"
            + NEW_LINE + HORIZONTAL_LINE + NEW_LINE + NEW_LINE;
    private final String MESSAGE_TEMPLATE_ERROR = HORIZONTAL_LINE + NEW_LINE + PADDING
            + "☹ OOPS!!! %s"
            + NEW_LINE + HORIZONTAL_LINE + NEW_LINE + NEW_LINE;
    private final List<Task> storageList = new ArrayList<>();

    public void init() {
        sayHello();
        try {
            loadTasksFromDisk();
        }
        catch (DukeDataFolderException ex) {
            printError(ex.getMessage());
            File dir = new File(DUKE_DATA_DIR_PATH.toUri());
            Boolean isCreated = dir.mkdir();
            if (isCreated) {
                echoBack("Successfully created Deuk Data Folder");
                try {
                    FileWriter fw = new FileWriter(DUKE_DATA_FILE_PATH.toString());
                    fw.close();
                }
                catch (IOException err) {
                    printError(err.getMessage());
                }
            }
            else {
                printError("Failed to create Deuk Data Folder");
            }
        }
        catch (DukeException ex) {
            printError(ex.getMessage());
        }
        catch (FileNotFoundException ex) {
            printError("Missing Deuk Data File!" + NEW_LINE + PADDING +
                "Creating new Deuk Data File..."
            );
            try {
                FileWriter fw = new FileWriter(DUKE_DATA_FILE_PATH.toString());
                fw.close();
            }
            catch (IOException err) {
                printError(err.getMessage());
            }

        }


        while(true) {
            if (SC.hasNext()) {
                String input = SC.nextLine().trim();
                if (input.toUpperCase().equals(Commands.EXIT.getString())) { // to make command case-insensitive
                    sayGoodbye();
                    break;
                }
                if (input.toUpperCase().equals(Commands.LIST.getString())) {
                    displayStorageList();
                }
                else if (input.length() >= 5 && input.substring(0,5).toUpperCase().equals(Commands.DONE.getString())) {
                    try {
                        int index = Integer.parseInt(input.substring(5).trim());
                        setTaskDone(index);
                    } catch (NumberFormatException ex) {
                        printError("Please input an Integer for the \"Done\" command.");
                    } catch (DukeException ex) {
                        printError(ex.getMessage());
                    }
                }
                else if (input.toUpperCase().equals(Commands.DELETEALL.getString())) {
                    try {
                        deleteAllTasks();
                    } catch (DukeException ex) {
                        printError(ex.getMessage());
                    }
                }
                else if (input.length() >= 7 && input.substring(0,7).toUpperCase().equals(Commands.DELETE.getString())) {
                    try {
                        int index = Integer.parseInt(input.substring(7).trim());
                        deleteTask(index);
                    } catch (NumberFormatException ex) {
                        printError("Please input an Integer for the \"Delete\" command.");
                    } catch (DukeException ex) {
                        printError(ex.getMessage());
                    }
                }
                else if (input.length() >= 5 && input.substring(0,5).toUpperCase().equals(Commands.TODO.getString())) {
                    try {
                        String name = input.substring(5).trim();
                        addToStorageList(new Todo(name));
                    } catch (DukeException ex) {
                        printError(ex.getMessage());
                    }
                }
                else if (input.length() >= 6 && input.substring(0,6).toUpperCase().equals(Commands.EVENT.getString())) {
                    try {
                        int limiterPosition = input.indexOf(" /at ");
                        String name;
                        String timing;
                        if (limiterPosition != -1) {
                            name = input.substring(6, limiterPosition).trim();
                            timing = input.substring(limiterPosition + 5).trim();
                        } else {
                            throw new DukeException("Missing date/time for Event task");
                        }
                        addToStorageList(new Event(name, timing));
                    } catch (DukeException ex) {
                        printError(ex.getMessage());
                    }
                }
                else if (input.length() >= 9 && input.substring(0,9).toUpperCase().equals(Commands.DEADLINE.getString())) {
                    try {
                        int limiterPosition = input.indexOf(" /by ");
                        String name;
                        String dueDate;

                        if (limiterPosition != -1) {
                            name = input.substring(9, limiterPosition).trim();
                            dueDate = input.substring(limiterPosition + 5).trim();
                        } else {
                            throw new DukeException("Missing deadline for Deadline task");
                        }
                        addToStorageList(new Deadline(name, dueDate));
                    } catch (DukeException ex) {
                        printError(ex.getMessage());
                    }
                }
                else {
                    printError("Sorry I don't know what that means :(");
                }
            }
        }
    }

    private void echoBack(String message) {
        System.out.printf(MESSAGE_TEMPLATE_VERBAL, message);
    }

    private void addToStorageList(Task obj) throws DukeException{
        if (obj.getName().length() == 0) {
            throw new DukeException(obj.missingNameError());
        }
        this.storageList.add(obj);
        String numOfTasks = this.storageList.size() == 1 ? "1 task" : this.storageList.size() + " tasks";
        String message = "Got it. I've added the following task: " + NEW_LINE
                + PADDING + "  " + obj.toString() + NEW_LINE
                + PADDING + "Now you have "  + numOfTasks + " in total.";
        System.out.printf(MESSAGE_TEMPLATE, message);
    }

    private void displayStorageList() {
        if (this.storageList.size() == 0) {
            System.out.printf(MESSAGE_TEMPLATE, "Your list is empty, try adding some tasks to it");
            return;
        }
        String output = "You have the following tasks in your list:" + NEW_LINE;
        int counter = 1;
        for (Task ele: this.storageList) {
            output += PADDING + counter + ". " + ele.toString() + NEW_LINE;
            counter++;
        }
        output = output.substring(0, output.length()-1);
        System.out.printf(MESSAGE_TEMPLATE, output);
    }

    private void setTaskDone(int index) throws DukeException {
        if (index <= 0 || index > this.storageList.size()) {
            throw new DukeException("Invalid index, cannot find task.");
        }
        this.storageList.get(index-1).setDoneness(true);
        String message = "Nice job! I'll mark that as done:" + NEW_LINE + PADDING
                + "  " + this.storageList.get(index-1).toString();
        System.out.printf(MESSAGE_TEMPLATE, message);
    }

    private void deleteTask(int index) throws DukeException {
        if (index <= 0 || index > this.storageList.size()) {
            throw new DukeException("Invalid index, cannot find task.");
        }
        Task task = this.storageList.get(index-1);
        this.storageList.remove(index-1);
        String numOfTasks = this.storageList.size() == 1 ? "1 task" : this.storageList.size() + " tasks";
        String message = "Noted. The following task has been removed:"
                + NEW_LINE + PADDING + "  " + task.toString() + NEW_LINE
                + PADDING + "Now you have "  + numOfTasks + " left.";;
        System.out.printf(MESSAGE_TEMPLATE, message);
    }

    private void deleteAllTasks() throws DukeException {
        if (this.storageList.size() == 0) {
            throw new DukeException("Your list is already empty.");
        }
        this.storageList.clear();
        String message = "Noted. All tasks have been removed.";
        System.out.printf(MESSAGE_TEMPLATE, message);
    }

    private void loadTasksFromDisk() throws FileNotFoundException, DukeException, DukeDataFolderException{
        File dukeDataFile = new File(DUKE_DATA_FILE_PATH.toUri());
        if (Files.notExists(DUKE_DATA_DIR_PATH)) {
            throw new DukeDataFolderException("Missing Deuk Data Folder!" + NEW_LINE + PADDING +
                    "Creating new Deuk Data Folder..."
            );
        }
        Scanner fs = new Scanner(dukeDataFile);
        while (fs.hasNext()) {
            String taskString = fs.nextLine();
            Scanner sc = new Scanner(taskString);
            Boolean isDone = sc.nextInt() == 1;
            String taskType = sc.next();
            String taskName = sc.next();
            Task task;
            if (taskType.equals("T")) {
                task = new Todo(taskName);
            }
            else if (taskType.equals("D")) {
                String dueDate = fs.nextLine();
                task = new Deadline(taskName, dueDate);
            }
            else if (taskType.equals("E")) {
                String timing = fs.nextLine();
                task = new Event(taskName, timing);
            }
            else {
                throw new DukeException("Save file corrupted!");
            }
            task.setDoneness(isDone);
            this.storageList.add(task);
        }
//        System.out.println("full path: " + dukeDataFile.getAbsolutePath());
//        System.out.println("file exists?: " + dukeDataFile.exists());
//        System.out.println("is Directory?: " + dukeDataFile.isDirectory());
    }

    private void saveTasksToDisk() throws IOException {
        FileWriter fw = new FileWriter(DUKE_DATA_FILE_PATH.toString());
        String tasksString = "";
        for (Task task : this.storageList) {
            tasksString += task.toSaveDataFormat() + NEW_LINE;
        }
        fw.write(tasksString);
        fw.close();
    }

    private void printError(String error) {
        System.out.printf(MESSAGE_TEMPLATE_ERROR, error);
    }

    private void sayGoodbye() {
        try {
            saveTasksToDisk();
        }
        catch (IOException ex) {
            printError(ex.getMessage());
        }
        System.out.printf(MESSAGE_TEMPLATE_VERBAL, "Goodbye, hope to see you again!");
    }

    private void sayHello() {
        String logo =
                  "     _____             _    \n"
                + "    |  __ \\           | |   \n"
                + "    | |  | | ___ _   _| | __\n"
                + "    | |  | |/ _ \\ | | | |/ /\n"
                + "    | |__| |  __/ |_| |   < \n"
                + "    |_____/ \\___|\\__,_|_|\\_\\  v" + VERSION_NUMBER + "\n";
        String introMessage = "I'm Deuk, nice to meet you\n" + PADDING +
                "How can I be of service today?";
        System.out.printf(logo + MESSAGE_TEMPLATE_VERBAL, introMessage);
    }

    public static void main(String[] args) {
        new Duke().init();
    }
}
