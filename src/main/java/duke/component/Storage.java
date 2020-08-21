package duke.component;

import duke.command.InvalidCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final String filePath;
    public final TaskList list;

    public Storage(String filePath) throws FileNotFoundException, InvalidCommandException {
        this.filePath = filePath;
        list = new TaskList();
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            String taskType = sc.next();
            String description = sc.next();
            int done = sc.nextInt();
            description = sc.next();
            description = sc.nextLine();
            int i = 0;
            while (description.charAt(i) == ' ') {
                i++;
            }
            description = description.substring(i);
            Task toAdd;
            if (taskType.charAt(0) == 'T') {
                toAdd = new ToDo(description);
            } else {
                int position = description.indexOf('|');
                if (position < 0) {
                    System.out.println(taskType + description + "error!");
                }
                String time = description.substring(position + 6);
                description = description.substring(0, position - 1);
                if (taskType.charAt(0) == 'D') {
                    toAdd = new Deadline(description, time);
                } else if (taskType.charAt(0) == 'E') {
                    toAdd = new Event(description, time);
                } else {
                    throw new InvalidCommandException("Invalid input file format");
                }
            }
            if (done == 1) {
                toAdd.markAsDone();
            }
            list.add(toAdd);
            list.count++;
        }
    }

    public void addToList(Task task) throws InvalidCommandException {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(task.output());
            fw.close();
        } catch (IOException e) {
            throw new InvalidCommandException(e.getMessage());
        }
    }

    public void deleteTask(TaskList list) throws InvalidCommandException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : list) {
                fw.write(task.output());
            }
            fw.close();
        } catch (IOException e) {
            throw new InvalidCommandException(e.getMessage());
        }
    }
}
