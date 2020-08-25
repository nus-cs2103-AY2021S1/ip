package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.task.*;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;

public class AddCommand extends Command {
    private final TaskType type;
    private final String name;
    private final String time;

    public AddCommand(TaskType type, String name) {
        this.type = type;
        this.name = name;
        this.time = "";
    }

    public AddCommand(TaskType type, String name, String time) {
        this.type = type;
        this.name = name;
        this.time = time;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (type == TaskType.TODO) {
                tasks.addTask(new ToDo(this.name));
            } else if (this.type == TaskType.DEADLINE) {
                LocalDate by = LocalDate.parse(this.time);
                tasks.addTask(new Deadline(this.name, by));
            } else if (this.type == TaskType.EVENT) {
                LocalDate at = LocalDate.parse(this.time);
                tasks.addTask(new Event(name, at));
            }
            storage.updateDataFile(tasks.getList());
        } catch (IOException error) {
            error.printStackTrace();
        } catch (DateTimeException error) {
            System.out.println("Please provide a valid date and time");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}