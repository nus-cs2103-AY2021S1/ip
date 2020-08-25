package duke.storage;


import duke.exception.DukeException;
import duke.tasklist.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String path;
    private BufferedWriter writer;

    public Storage(String path) throws IOException {
        this.path = path;
        File file = new File(path);
        if (!Files.exists(Paths.get("./data"))) {
            new File("./data").mkdir();
        }
        file.createNewFile();
        this.writer = new BufferedWriter(new FileWriter(path, true));
    }

    public List<Task> load() throws IOException, DukeException {
        List<Task> list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(path));
        String task = br.readLine();
        while(task != null) {
            if (!task.equals("")) {
                String[] splits = task.split(" ~/~ ");
                Task t = null;
                switch (splits[0]) {
                    case "T":
                        t = new ToDo(splits[2]);
                        break;
                    case "D":
                        if (splits.length == 4) {
                            t = new Deadline(splits[2], LocalDate.parse(splits[3]));
                        } else if (splits.length == 5) {
                            t = new Deadline(splits[2], LocalDate.parse(splits[3]), LocalTime.parse(splits[4]));
                        }
                        break;
                    case "E":
                        if (splits.length == 4) {
                            t = new Event(splits[2], LocalDate.parse(splits[3]));
                        } else if (splits.length == 5) {
                            t = new Event(splits[2], LocalDate.parse(splits[3]), LocalTime.parse(splits[4]));
                        } else if (splits.length == 6) {
                            t = new Event(splits[2], LocalDate.parse(splits[3]), LocalTime.parse(splits[4]),
                                    LocalTime.parse(splits[5]));
                        }
                        break;
                    default:
                        throw new DukeException("Please check the hard disk for any errors");
                }
                if (splits[1].equals("1")) t.markAsDone();
                list.add(t);
            }
            task = br.readLine();
        }
        return list;
    }

    public void add(Task task) throws IOException {
        writer.write(task.txtFileFormat());
        writer.newLine();
        writer.flush();
    }

    public void refresh(TaskList taskList) throws IOException {
        this.writer = new BufferedWriter(new FileWriter(this.path));
        for (Task t : taskList.getList()) {
            writer.write(t.txtFileFormat());
            writer.newLine();
        }
        this.writer.flush();
        this.writer = new BufferedWriter(new FileWriter(this.path, true));
    }
}
