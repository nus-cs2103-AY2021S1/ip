package processor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

import exception.DukeException;
import exception.FileCorruptedException;
import task.DeadlineTask;
import task.EventTask;
import task.Task;
import task.TaskList;
import task.ToDoTask;

/**
 * Converts data from file to TaskList class.
 *
 * <p>The 'FileToTaskListConverter' supports operators, supported include: </p>
 *
 * <p> (i) converting file to TaskList object </p>
 *
 * <p> (ii) saving TaskList data into file provided </p>
 */
public abstract class FileToTaskListConverter {

    /**
     * Converts file to TaskList object.
     *
     * @param data the file loaded to be converted.
     * @return a TaskList containing all tasks inscribed in file
     * @throws FileCorruptedException If file is not formatted in the correct order. ie
     *  not in [type]//[status]//[task description]//[date if applicable]//[time if applicable]
     */
    public static TaskList convert(File data) throws FileCorruptedException {
        List<String> dataList = loadStringData(data);
        List<Task> list = new ArrayList<>();

        for (int i = 0; i < dataList.size(); i++) {
            list.add(getTaskFromData(dataList.get(i)));
        }

        return new TaskList(list);
    }

    /**
     * Saves TaskList to file provided.
     * Data is stored in [type]//[status]//[task description]//[date if applicable]//[time if applicable]
     *
     * @param list the TaskList to be saved.
     * @param file the file to be saved in.
     * @return save status of TaskList. True if successful, false otherwise.
     */
    public static boolean saveToFile(TaskList list, File file) {
        try {
            FileWriter fw = new FileWriter(file);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                Task task = list.getTask(i);
                sb.append((task.getDetails()));
                sb.append("\n");
            }
            fw.write(sb.toString());
            fw.close();
            return true;
        } catch (IOException e) {
            System.out.println("Unable to save");
            return false;
        }
    }

    private static List<String> loadStringData(File data) {
        try {
            List<String> list = new ArrayList<>();
            Scanner sc = new Scanner(data);
            while (sc.hasNext()) {
                list.add(sc.nextLine());
            }
            sc.close();
            return list;
        } catch (FileNotFoundException e) {
            File directory = data.getParentFile();

            try {
                if (!directory.exists() || !directory.isDirectory()) {
                    directory.mkdirs();
                }
                data.createNewFile();
            } catch (IOException ioe) {
                System.out.println("Unable to open/create file");
            }
            return new ArrayList<>();
        }
    }

    private static Task getTaskFromData(String info) throws FileCorruptedException {
        try {
            String[] words = info.split("//");
            char type = words[0].charAt(0);
            boolean isDone = words[1].equals("O");
            String description = words[2];

            switch (type) {
            case 'T':
                return new ToDoTask(description, isDone);
            case 'D':
                return new DeadlineTask(description, words[3] + " " + words[4], isDone);
            case 'E':
                return new EventTask(description, words[3] + " " + words[4], isDone);
            default:
                return new Task("No Task !!!!");
            }
        } catch (DukeException | IndexOutOfBoundsException | PatternSyntaxException e) {
            throw new FileCorruptedException();
        }
    }
}
