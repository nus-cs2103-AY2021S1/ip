import Task.DeadlineTask;
import Task.EventTask;
import Task.Task;
import Task.ToDoTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class FileToTaskListConverter {
    public static List<Task> convert(File data) {
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

    private static Task getTaskFromData(String info) {

        char type = info.charAt(1);
        boolean isDone = Character.toString(info.charAt(4)).equals("✓");
        int indexOfOpenBrackets = info.indexOf("(");
        int indexOfCloseBrackets = info.indexOf(")");

        switch (type) {
        case 'T':
            return new ToDoTask(info.substring(7), isDone);
        case 'D':
            return new DeadlineTask(info.substring(7,indexOfOpenBrackets - 1),
                    info.substring(indexOfOpenBrackets + 5, indexOfCloseBrackets), isDone);
        case 'E':
            return new EventTask(info.substring(7,indexOfOpenBrackets - 1),
                    info.substring(indexOfOpenBrackets + 5, indexOfCloseBrackets), isDone);
        default:
            return new Task("No Task !!!!");
        }
    }
}
