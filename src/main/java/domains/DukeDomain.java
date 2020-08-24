package domains;

import constants.DukeConstants;
import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.Task;
import tasks.ToDoTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class DukeDomain {

    private static final List<Task> TASK_LIST = loadList();

    private static final String LIST_FILE_PATH = "./data/duke.txt";

    public void markTaskDone(int idx) {
        Task task = TASK_LIST.get(idx);
        task.markDone();
        String response = String.format("%s\n%s%s[%s] %s", DukeConstants.DONE_OUTPUT,
                DukeConstants.IDENT, DukeConstants.IDENT,
                task.getStatusIcon(), task.getTitle());
        printResponse(response);
    }


    public void printResponseWithListSize(String response) {
        System.out.println(DukeConstants.LINE);
        System.out.printf("%s%s\n", DukeConstants.IDENT, response);
        System.out.printf("%sNow you have %d tasks in the list.%n",
                DukeConstants.IDENT, TASK_LIST.size());
        System.out.println(DukeConstants.LINE);
    }

    public void deleteTask(int idx) {
        Task task = TASK_LIST.remove(idx);
        String response = String.format("%s\n%s%s%s", DukeConstants.DELETE_OUTPUT,
                DukeConstants.IDENT, DukeConstants.IDENT, task);
        printResponseWithListSize(response);
    }

    public void printResponse(String response) {
        System.out.println(DukeConstants.LINE);
        System.out.printf("%s%s\n", DukeConstants.IDENT, response);
        System.out.println(DukeConstants.LINE);
    }

    public void outputTask(Task task) {
        System.out.println(DukeConstants.LINE);
        System.out.println(DukeConstants.IDENT + DukeConstants.ADD_TASK_OUTPUT);
        System.out.printf("%s%s%s\n", DukeConstants.IDENT, DukeConstants.IDENT, task);
        System.out.printf("%sNow you have %d tasks in the list.%n",
                DukeConstants.IDENT, TASK_LIST.size());
        System.out.println(DukeConstants.LINE);
    }


    public void addToList(Task task) {
        TASK_LIST.add(task);
    }

    public void printList() {
        System.out.println(DukeConstants.LINE);
        System.out.println(DukeConstants.IDENT + DukeConstants.LIST_OUTPUT);
        int num = 0;
        for (Task output : TASK_LIST) {
            num++;
            System.out.printf("%s%d.%s\n", DukeConstants.IDENT, num, output);
        }
        System.out.println(DukeConstants.LINE);
    }

    public void saveList() {
        File file = new File(LIST_FILE_PATH);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdir();
        }
        try (FileWriter fileWriter = new FileWriter(file)) {
            for (Task task : TASK_LIST) {
                fileWriter.write(task.getSaveFormat());
                fileWriter.write("\n");
            }
        } catch (IOException e) {
            System.out.println("OOPS! some error saving the list." );
            e.printStackTrace();
        }
    }

    private static List<Task> loadList() {
        File file = new File(LIST_FILE_PATH);
        File parentFile = file.getParentFile();
        List<Task> list = new ArrayList<>();
        if (!parentFile.exists()) {
            return list;
        }

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] components = line.split("\\|");
                for (int i = 0 ; i < components.length ; i++) {
                    components[i] = components[i].trim();
                }
                Task task;
                boolean isDone = Integer.parseInt(components[1]) == 1;
                switch(components[0]) {
                    case "T":
                        task = new ToDoTask(components[2], isDone);
                        break;
                    case "D":
                        task = new DeadlineTask(components[2], components[3], isDone);
                        break;
                    default:
                        task = new EventTask(components[2], components[3], isDone);
                }
                list.add(task);

            }
        } catch (FileNotFoundException e) {
            System.out.println("OOPS! some error saving the list.");
            e.printStackTrace();
        }
        return list;
    }
}
