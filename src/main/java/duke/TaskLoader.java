package duke;

import java.io.File;
import java.util.Scanner;

public class TaskLoader {
    private static final String SAMPLE_T = "todo visit the moon (sample)";
    private static final String SAMPLE_D = "deadline collect mars rocks (sample) /by 2020-09-23 19:00";
    private static final String SAMPLE_E = "event earth landing (sample) /at NASA";

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
            System.out.println("From loadTasks in TaskLoader");
            System.out.println(e);
        }
    }

    /**
     * loadSampleTasks(): Load pre-determined task for first-time users.
     * @param tasks TaskList object to be populated
     */
    public static void loadSampleTasks(TaskList tasks) {
        try {
            Task todoSample = Parser.parseTask(SAMPLE_T);
            Task deadlineSample = Parser.parseTask(SAMPLE_D);
            Task eventSample = Parser.parseTask(SAMPLE_E);
            tasks.addTask(todoSample);
            tasks.addTask(deadlineSample);
            tasks.addTask(eventSample);
        } catch (Exception e) {
            System.out.println("From loadSample");
            System.out.println(e);
        }
    }
}
