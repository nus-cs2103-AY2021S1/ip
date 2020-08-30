import duke.Task.Task;
import duke.TaskList;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void addTask() {
        try {
            File savedFile = new File("./data/save.txt");
            if (savedFile.exists()) {
                FileWriter writer = new FileWriter("./data/save.txt", false);
                writer.write("");
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        TaskList taskList = new TaskList();
        Task newTask = new Task("sample task");
        taskList.addTask(newTask);
        assertEquals(1, taskList.getAllTasks().size());
    }
    @Test
    public void finishTask() {
        try {
            File savedFile = new File("./data/save.txt");
            if (savedFile.exists()) {
                FileWriter writer = new FileWriter("./data/save.txt", false);
                writer.write("");
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        TaskList taskList = new TaskList();
        Task newTask = new Task("sample task");
        taskList.addTask(newTask);
        taskList.completeTask(0);
        assertEquals("\u2713", newTask.getStatusIcon());
    }
    @Test
    public void deleteTask() {
        try {
            File savedFile = new File("./data/save.txt");
            if (savedFile.exists()) {
                FileWriter writer = new FileWriter("./data/save.txt", false);
                writer.write("");
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        TaskList taskList = new TaskList();
        Task newTask = new Task("sample task");
        taskList.addTask(newTask);
        taskList.deleteTask(0);
        assertEquals(0, taskList.getAllTasks().size());

    }

}