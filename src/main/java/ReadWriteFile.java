import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadWriteFile {

    public static void readTaskList(ArrayList<Task> taskList) throws DukeException {
        java.nio.file.Path path = java.nio.file.Paths.get("data").resolve("duke.txt");
        boolean dirExists = java.nio.file.Files.exists(path);

        if (dirExists) {
            try {
                File inFile = new File(path.toString());
                Scanner sc = new Scanner(inFile);
                Task t;

                while (sc.hasNext()) {
                    String[] task = sc.nextLine().split("\\|");
                    switch (task[0].trim()) {
                        case "T":
                            t = new ToDo(task[2].trim());
                            break;
                        case "D":
                            String[] dDescription = task[2].split(" /by ");
                            t = new Deadline(dDescription[0].trim(), dDescription[1].trim());
                            break;
                        case "E":
                            String[] eDescription = task[2].split(" /at ");
                            t = new Event(eDescription[0].trim(), eDescription[1].trim());
                            break;
                        default:
                            throw new DukeException("☹ Sorry, I don't recognise that command from the text file!");
                    }
                    if (task[1].equals("1")) {
                        t.markAsDone();
                    }
                    taskList.add(t);
                }
            } catch (FileNotFoundException e) {
                File infile = new File(path.toString());
            }
        }
    }

    public static void writeToFile(ArrayList<Task> taskList) {
        java.nio.file.Path path = java.nio.file.Paths.get("data").resolve("duke.txt");
        try {
            StringBuilder content = new StringBuilder();
            FileWriter fw = new FileWriter(path.toString());
            for (Task task : taskList) {
                if (task instanceof ToDo) {
                    String taskDetails = String.format("T | %d | %s", task.isDone ? 1 : 0, task.toString());
                    content.append(taskDetails).append("\n");
                } else if (task instanceof Deadline) {
                    String taskDetails = String.format("D | %d | %s",
                            task.isDone ? 1 : 0, task.toString());
                    content.append(taskDetails).append("\n");
                } else {
                    String taskDetails = String.format("E | %d | %s",
                            task.isDone ? 1 : 0, task.toString());
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