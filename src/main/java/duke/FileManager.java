package duke;

import duke.Tasks.DeadLineTask;
import duke.Tasks.EventTask;
import duke.Tasks.Task;
import duke.Tasks.TodoTask;
import duke.TextStoreAndPrint.TextPrinter;
import duke.TextStoreAndPrint.TextStore;

import java.io.*;
import java.nio.file.Path;
import java.time.format.DateTimeParseException;

public class FileManager {

    /**
     * Saves the tasks in taskList into a txt file located at the provided path
     *
     * @param taskList tasks to be saved
     * @param path saved location
     */
    public static void saveList(TaskList taskList, Path path) {
        try {
            FileWriter fw = new FileWriter(path.toFile());
            PrintWriter pw = new PrintWriter(fw);

            pw.print(taskList.allSaveString());
            TextPrinter.standardPrint(TextStore.savingToTextFile + "\n" + "file saved at:\n" + path.toString());
            pw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tries to read the file from the given path and create a TaskList object from save file
     * Returns a TaskList object with no tasks if file is empty or not found
     * Prints relevant status messages
     *
     * @param path path to the save file
     * @return TaskList object containing tasks read from the save file
     */
    public static TaskList readFromSave(Path path) {
        TaskList taskList = new TaskList();
        try {
            TextPrinter.printTopGraphic();
            TextPrinter.printStandAlone(TextStore.loadingSave);

            boolean directoryExists = java.nio.file.Files.exists(path);

            if (directoryExists) {
                TextPrinter.printStandAlone(TextStore.saveFound);
                taskList = readSaveToTaskListObject(path);
            } else {
                TextPrinter.printStandAlone(TextStore.saveNotFound);
            }
            TextPrinter.printBottomGraphic();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    private static TaskList readSaveToTaskListObject(Path path) throws IOException {
        TaskList taskList = new TaskList();
        try {
            FileReader fr = new FileReader(path.toFile());
            BufferedReader br = new BufferedReader(fr);

            String str;
            while ((str = br.readLine()) != null) {
                String info[] = str.split("/break/", 4);
                taskList.loadInTask(infoToTask(info));
            }
        } catch (IOException e) {
            throw e;
        }
        return taskList;
    }

    private static Task infoToTask(String info[]) {
        Task task;
        switch (info[0]) {
            case "T":
                task = new TodoTask(info[2]);
                break;
            case "D":
                try {
                    task = new DeadLineTask(info[2], MyDateTime.load(info[3]));
                } catch (DateTimeParseException e) {
                    throw new IllegalStateException("Unexpected time format: " + info[3]);
                }
                break;
            case "E":
                try {
                    task = new EventTask(info[2], MyDateTime.load(info[3]));
                } catch (DateTimeParseException e) {
                    throw new IllegalStateException("Unexpected time format: " + info[3]);
                }
                break;
            default:
                throw new IllegalStateException("Unexpected type: " + info[0]);
        }

        if (info[1].equals("true")) {
            task.markDone();
        }

        return task;
    }
}
