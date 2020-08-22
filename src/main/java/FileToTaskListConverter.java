import Task.DeadlineTask;
import Task.EventTask;
import Task.Task;
import Task.ToDoTask;

import java.io.File;
import java.io.FileNotFoundException;
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
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    public static Task getTaskFromData(String info) {
        String[] arr = info.split("/");
        char type = arr[0].charAt(0);
        boolean isDone = arr[1].equals("1");

        switch (type) {
        case 'T':
            return new ToDoTask(arr[2], isDone);
        case 'D':
            return new DeadlineTask(arr[2], arr[3], isDone);
        case 'E':
            return new EventTask(arr[2], arr[3], isDone);
        default:
            return new Task("No Task");
        }
    }
}
