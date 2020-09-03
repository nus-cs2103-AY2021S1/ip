package duke;

import java.io.File;
import java.util.Scanner;

public class TaskLoader {
    /**
     * loadTasks is used to populate TaskList object, with tasks from a txt
     * file
     * @param prevTasks File object that has all previous tasks
     * @param tasks TaskList object to be populated
     */
    public static void loadTasks(File prevTasks, TaskList tasks) {
        try {
            Scanner scFile = new Scanner(prevTasks);
            while (scFile.hasNextLine()) {
                String[] savedTask = scFile.nextLine().split("~");

                Task newTask = Parser.parseTask(savedTask[1]);
                if (savedTask[0].compareTo("T") == 0) {
                    newTask.done();
                }
                tasks.addTask(newTask);
            }
            scFile.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
