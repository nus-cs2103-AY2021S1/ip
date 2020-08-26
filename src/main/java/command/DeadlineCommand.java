package main.java.command;


import main.java.exception.DescriptionException;
import main.java.exception.DukeDateTimeParserException;
import main.java.parser.Parser;
import main.java.storage.Storage;
import main.java.task.DeadlineTask;
import main.java.task.TaskList;
import main.java.ui.Ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;

public class DeadlineCommand extends Command {

    private String command;

    public DeadlineCommand(String command) {
        super();
        this.command =command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DescriptionException, DukeDateTimeParserException {
          try {
            Map<String, String> taskDetails = Parser.findDescriptionParser(this.command);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            DeadlineTask deadlineTask = new DeadlineTask(taskDetails.get("taskDescription"),
                    LocalDateTime.parse(taskDetails.get("taskTime"), formatter));

            tasks.add(deadlineTask);

            ui.getTaskMessage(deadlineTask, tasks.size());

            storage.updateFile(tasks);
        } catch (DateTimeParseException e) {
            throw new DukeDateTimeParserException();
        }
    }
}
