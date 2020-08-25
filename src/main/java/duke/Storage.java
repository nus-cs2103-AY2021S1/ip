package duke;

import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Storage {
    protected File savefile;
    protected FileWriter writer;
    protected boolean isEmptySave;
    protected static boolean isLoadingError = false;

    // hardcoding the directory? will it work on *nix?
    // try: String home = System.getProperty("user.home");
    protected String location;
    protected final static String filename = "/save.txt";
    protected ArrayList<Task> listFromFile;

    public Storage(String specifiedLocation) {
        this.location = specifiedLocation;
        this.savefile = new File(specifiedLocation + filename);
        run();
    }

    public ArrayList<Task> getListFromFile() {
        return listFromFile;
    }

    public void run() {
        try {
            // Verifies save file directory and loads the save file from disk
            checkIfSaveFileExists();
            this.listFromFile = loadListFromFile();
        } catch (DukeException e) {
            // If error loading save file, reset save file and task list
            e.printStackTrace(System.out);
            this.listFromFile = resetSaveFile();
        }
    }

    public void checkIfSaveFileExists() throws DukeException {
        try {
                // Checks if directory exists, if not creates it.
                Path path = Paths.get(location);
        if (!Files.isDirectory(path)) {
            File dir = new File(location);
            boolean isDirCreated = dir.mkdir();
            if (isDirCreated) {
                System.out.println("Created directory: " + location);
            }
        }
        // Checks if save file exists, if not creates it.
        // if created, isEmptySave will be true
        // else already exists, isEmptySave will be false
            this.isEmptySave = savefile.createNewFile();
        } catch (IOException e) {
            throw new DukeException("\u2639 Oops, error checking if save file exists");
        }
    }

    public ArrayList<Task> loadListFromFile() throws DukeException {
        System.out.println("Loading from save file...");
        try {
            ArrayList<Task> list = new ArrayList<>();
            if (isEmptySave || savefile.length() == 0) {
                // Empty savefile returns empty task list to start
                System.out.println("Current save file is empty.");
                return list;
            }
            Scanner sc = new Scanner(savefile);
            int noOfTasks = Integer.parseInt(sc.nextLine());
            for (int i = 0; i < noOfTasks; i++) {
                // Read all tasks from savefile
                String input = sc.nextLine();
                if (input.startsWith("todo")) {
                    Task newTodo = Parser.parseNewTaskCommand(input, Task.taskType.TODO);
                    list.add(newTodo);
                } else if (input.startsWith("deadline")) {
                    Task newDeadline = Parser.parseNewTaskCommand(input, Task.taskType.DEADLINE);
                    list.add(newDeadline);
                } else if (input.startsWith("event")){
                    Task newEvent = Parser.parseNewTaskCommand(input, Task.taskType.EVENT);
                    list.add(newEvent);
                } else {
                    throw new DukeException("\u2639 Oops, error parsing " + '"' + input + '"' + " in save file");
                }
            }

            // Mark tasks as done
            while(sc.hasNext()) {
                int doneTaskIndex = Integer.parseInt(sc.next());
                list.get(doneTaskIndex).markAsDone();
            }
            System.out.println("Successfully loaded: " + list.size() + " task(s)");
            return list;

        } catch (IOException e1) {
            throw new DukeException("\u2639 Oops, error reading from " + location + filename);
        } catch (IndexOutOfBoundsException |NoSuchElementException | NumberFormatException e) {
            throw new DukeException("\u2639 Oops, save file is corrupted, error encountered: " + e.getLocalizedMessage().toLowerCase());
        } catch (DukeException e) {
            throw new DukeException("\u2639 Oops, save file is corrupted, error loading task");
        }
    }

    public ArrayList<Task> resetSaveFile() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> newList = new ArrayList<>();
        // If fail to load save file, prompts user to reset the savefile
        System.out.println("Type restart to reset task list or anything else to exit.");
        System.out.println();
        if (scanner.nextLine().equals("restart")) {
            try {
                // Resets save file to empty
                this.saveToFile(newList);
                Ui.greet();
            } catch (DukeException e1) {
                e1.printStackTrace(System.out);
            }
            return newList;
        } else {
            isLoadingError = true;
            return newList;
        }
    }

    public void saveToFile(ArrayList<Task> list) throws DukeException {
        try {
            this.writer = new FileWriter(location+filename);
            String doneIndexes;
            // Writes the number of tasks in list
            // System.out.println("Wrote: " + list.size());
            writer.write(list.size() + System.lineSeparator());
            // Saving each task to savefile
            StringBuilder doneIndexesBuilder = new StringBuilder();
            for (int i = 0; i<list.size(); i++) {
                    Task t = list.get(i);
                    if (t.isDone()) {
                        doneIndexesBuilder.append(i).append(" ");
                    }
                    // Writes the task to file
                    // System.out.println("Wrote: " + t.toSaveFormat());
                    writer.write(t.toSaveFormat() + System.lineSeparator());

            }
            doneIndexes = doneIndexesBuilder.toString();
            if (!doneIndexes.equals("")) {
                // Writes the index of completed tasks
                // System.out.println("Wrote: " + doneIndexes);
                writer.write(doneIndexes + System.lineSeparator());

            }
            writer.flush();
        } catch (IOException e) {
            throw new DukeException("\u2639 Oops, error saving to save file");
        }
    }
}
