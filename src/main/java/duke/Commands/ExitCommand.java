package duke.Commands;

import duke.Storage.Storage;
import duke.Ui.Ui;
import duke.task.TaskList;

import java.io.FileWriter;
import java.io.IOException;

public class ExitCommand extends Command {
    public ExitCommand(String command) {
        super(command);
        super.isExit = true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
            StringBuilder stringBuilder = new StringBuilder();
            taskList.forEach(task -> {
                stringBuilder.append(task.formatTaskForDatabase() + "\n");
            });

            try {
                FileWriter fileWriter = new FileWriter(storage.getDirectory());
                fileWriter.write(stringBuilder.toString());
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while updating database file");
            }
        }

    }
