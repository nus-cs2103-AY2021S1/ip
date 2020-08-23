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

    // hardcoding the directory? will it work on *nix?
    // try: String home = System.getProperty("user.home");
    protected static String location = "src/main/data";
    protected static String filename = "/save.txt";

    public Storage() {
        this.savefile = new File(location + filename);
    }

    public void checkSaveFileExists() throws IOException {
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
    }

    public ArrayList<Task> loadFromFile() throws DukeException {
        try {
            checkSaveFileExists();
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
                    Task newTodo = TaskHandler.processNewTask(input, Task.taskType.TODO);
                    list.add(newTodo);
                } else if (input.startsWith("deadline")) {
                    Task newDeadline = TaskHandler.processNewTask(input, Task.taskType.DEADLINE);
                    list.add(newDeadline);
                } else {
                    Task newEvent = TaskHandler.processNewTask(input, Task.taskType.EVENT);
                    list.add(newEvent);
                }
            }

            // Mark tasks as done
            while(sc.hasNext()) {
                int doneTaskIndex = Integer.parseInt(sc.next());
                list.get(doneTaskIndex).markAsDone();
            }
            System.out.println("Successfully loaded from save file: " + list.size() + " task(s)");
            return list;

        } catch (IOException | NoSuchElementException | NumberFormatException e) {
            throw new DukeException("\u2639 Oops, save file is corrupted!");
        }
    }

    public void saveToFile(ArrayList<Task> list) throws DukeException{
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
            throw new DukeException("\u2639 Oops, error finding savefile to write to.");
        }
    }
}
