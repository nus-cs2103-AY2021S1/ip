package duke.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a Storage system where the tasks lists and its content are
 * being stored in.
 */
public class Storage {

    private static String rtfPath = "data/duke.rtf";
    private static String newLine = System.lineSeparator();


    public Storage(TaskList tasksLs) throws FileNotFoundException {
        readFromFile(rtfPath, tasksLs);
    }

    /**
     * Stores the list of tasks in a rtf file before Duke is terminated.
     * @param filePath location of rtf file.
     * @param textToAdd tasks to be added to the rtf file.
     * @throws IOException file may not be found in the path.
     */
    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        assert textToAdd != null : "List of tasks cannot be empty";
        File f = new File(filePath);
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Loads the list of tasks from the rtf file when Duke is launched.
     * @param filePath location of rtf file.
     * @param taskLs tasks lists where tasks are being loaded into.
     * @throws FileNotFoundException file may not be found in the path.
     */
    public static void readFromFile(String filePath, TaskList taskLs) throws FileNotFoundException {
        checkDirectory(new File("data"));
        File f = new File(rtfPath);
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            String eachTask = s.nextLine();
            if (eachTask.startsWith("[T]")) {
                String[] description = eachTask.split("%");
                Todo todoTask = new Todo(description[2]);
                markTaskDone(todoTask, description[1]);
                taskLs.add(todoTask);
            } else if (eachTask.startsWith("[D]")) {
                String[] description = eachTask.split("%");
                Deadline deadlineTask = new Deadline(description[2], description[3]);
                markTaskDone(deadlineTask, description[1]);
                taskLs.add(deadlineTask);
            } else if (eachTask.startsWith("[E]")) {
                String[] description = eachTask.split("%");
                Event eventTask = new Event(description[2], description[3]);
                markTaskDone(eventTask, description[1]);
                taskLs.add(eventTask);
            } else { }
        }
    }

    /**
     * Checks if task is done and marks it complete.
     * @param addToList task to be added to task list.
     * @param done string containing true or false.
     */
    public static void markTaskDone(Task addToList, String done) {
        if (done.equalsIgnoreCase("true")) {
            addToList.markAsDone();
        }
    }

    /**
     * Checks if the file exists else create the data file.
     * @param directory location of file.
     */
    private static void checkDirectory(File directory) {
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    /**
     * Generates the format for the type of tasks to be displayed in a list format.
     * @param itemsLs list of tasks to be made into a string.
     * @return string containing the tasks in the list of tasks.
     */
    public static String genList(ArrayList<Task> itemsLs) {
        String s = "";
        int i = 1;
        for (Task n : itemsLs) {
            if (n instanceof Todo) {
                s = s + "[T]" + "%" + n.isDone + "%" + n.description + newLine;
            } else if (n instanceof Deadline) {
                s = s + "[D]" + "%" + n.isDone + "%" + n.description + "%" + ((Deadline) n).by + newLine;
            } else if (n instanceof Event) {
                s = s + "[E]" + "%" + n.isDone + "%" + n.description + "%" + ((Event) n).at + newLine;
            } else { }
        }

        return s;
    }

}
