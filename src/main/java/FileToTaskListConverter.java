import Exception.DukeException;
import Exception.FileCorruptedException;
import Task.DeadlineTask;
import Task.EventTask;
import Task.Task;
import Task.ToDoTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

public abstract class FileToTaskListConverter {
    public static List<Task> convert(File data) throws FileCorruptedException {
        List<String> dataList = loadStringData(data);
        List<Task> list = new ArrayList<>();

        for (int i = 0; i < dataList.size(); i++) {
            list.add(getTaskFromData(dataList.get(i)));
        }

        return list;
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
                if (directory.exists() && directory.isDirectory()) {
                    data.createNewFile();
                } else {
                    directory.mkdirs();
                    data.createNewFile();
                }
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
            boolean isDone = words[1].equals("✓");
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
        } catch (DukeException de) {
            throw new FileCorruptedException();
        } catch (IndexOutOfBoundsException ioobe) {
            throw new FileCorruptedException();
        } catch (PatternSyntaxException pse) {
            throw new FileCorruptedException();
        }
    }
}
