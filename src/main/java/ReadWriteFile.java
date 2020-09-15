import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class ReadWriteFile {
    public static ArrayList<Task> readTaskList() throws DukeException {
        java.nio.file.Path path = java.nio.file.Paths.get("data").resolve("duke.txt");
        boolean dirExists = java.nio.file.Files.exists(path);
        ArrayList<Task> taskList = new ArrayList<>();
        if (dirExists) {
            try {
                File inFile = new File(path.toString());
                Scanner sc = new Scanner(inFile);
                Task t;

                while (sc.hasNext()) {
                    String[] taskInputs = sc.nextLine().split("\\|");
                    switch (taskInputs[0].trim()) {
                    case "T":
                        t = new ToDo(taskInputs[2].trim());
                        break;
                    case "D":
                        t = new Deadline(taskInputs[2].trim(), taskInputs[3].trim());
                        break;
                    case "E":
                        t = new Event(taskInputs[2].trim(), taskInputs[3].trim());
                        break;
                    default:
                        throw new DukeException("☹ Sorry, I don't recognise that command from the text file!");
                    }
                    if (taskInputs[1].trim().equals("1")) {
                        t.markAsDone();
                    }
                    taskList.add(t);
                }
            } catch (FileNotFoundException e) {
                File infile = new File(path.toString());
                return taskList;
            }
            return taskList;
        }
        return null;
    }

    public static void writeToFile(ArrayList<Task> taskList) {
        java.nio.file.Path path = java.nio.file.Paths.get("data").resolve("duke.txt");
        try {
            StringBuilder content = new StringBuilder();
            FileWriter fw = new FileWriter(path.toString());
            for (Task task : taskList) {
                if (task instanceof ToDo) {
                    String taskDetails = ((ToDo) task).saveToDo();
                    content.append(taskDetails).append("\n");
                } else if (task instanceof Deadline) {
                    String taskDetails = ((Deadline) task).saveDeadline();
                    content.append(taskDetails).append("\n");
                } else if (task instanceof Event){
                    String taskDetails = ((Event) task).saveEvent();
                    content.append(taskDetails).append("\n");
                }
            }
            fw.write(content.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}