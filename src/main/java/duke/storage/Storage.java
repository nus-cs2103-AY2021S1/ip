package duke.storage;

import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.List;

public class Storage {

    String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public List<Task> load() {
        return TextToArrayListConverter.readFile(filepath);
    }

    public void save(TaskList tasks) {
        ArrayListToTextConverter.convertArrayListToText(tasks.getTaskList(),filepath);
    }
}
