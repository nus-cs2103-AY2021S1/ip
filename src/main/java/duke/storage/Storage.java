package duke.storage;

import duke.exception.DukeException;
import duke.tasks.*;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    String dirPath;
    String filePath;

    public Storage(String filePath, String dirPath) {
        this.dirPath = dirPath;
        this.filePath = filePath;
    }

    public List<Task> readFromFile() throws DukeException {
        List<Task> tasks = new ArrayList<>();

        try {
            File dir = new File(dirPath);
            File file = new File(filePath);
            dir.mkdir();
            file.createNewFile();
            Scanner sc = new Scanner(file);

            while (sc.hasNext()) {
                Task temp;
                String line = sc.nextLine();
                String[] instructions = line.split(" \\| ");
                int num = instructions.length;
                String type = instructions[0].strip();
                if (type.equals("T")) {
                    if (num != 3) {
                        throw new DukeException("The data format of the file is incorrect\n");
                    }
                    temp = new ToDo(instructions[2].strip());
                    if (instructions[1].strip().equals("1")) {
                        temp.markAsDone();
                    }
                    tasks.add(temp);
                } else if (type.equals("D")) {
                    if (num != 4) {
                        throw new DukeException("The data format of the file is incorrect\n");
                    }
                    LocalDateTime dtDeadline = LocalDateTime.parse(instructions[3].strip());
                    temp = new Deadlines(instructions[2].strip(), dtDeadline);
                    if (instructions[1].strip().equals("1")) {
                        temp.markAsDone();
                    }
                    tasks.add(temp);
                } else if (type.equals("E")) {
                    if (num != 4) {
                        throw new DukeException("The data format of the file is incorrect\n");
                    }
                    LocalDateTime dtEvent = LocalDateTime.parse(instructions[3].strip());
                    temp = new Events(instructions[2].strip(), dtEvent);
                    if (instructions[1].strip().equals("1")) {
                        temp.markAsDone();
                    }
                    tasks.add(temp);
                }
            }
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }

        return tasks;

    }

    public void storeToFile(TaskList tasks) throws DukeException {
        try {
            String breaker = " | ";
            FileWriter fileWriter = new FileWriter(filePath);
            for (int i = 0; i < tasks.getSize(); i++) {
                Task temp = tasks.get(i);
                String state = "0";
                if (temp instanceof ToDo) {
                    if (temp.isDone()) {
                        state = "1";
                    }
                    fileWriter.write("T" + breaker + state + breaker + temp.getDescription() + "\n");
                } else if (temp instanceof Deadlines) {
                    Deadlines deadline = (Deadlines) temp;
                    if (deadline.isDone()) {
                        state = "1";
                    }
                    fileWriter.write("D" + breaker + state + breaker + deadline.getDescription()
                            + breaker + deadline.getBy() + "\n");
                } else if (temp instanceof Events) {
                    Events event = (Events) temp;
                    if (event.isDone()) {
                        state = "1";
                    }
                    fileWriter.write("E" + breaker + state + breaker + event.getDescription()
                            + breaker + event.getStart() + "\n");
                }
            }
            fileWriter.close();
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

}
