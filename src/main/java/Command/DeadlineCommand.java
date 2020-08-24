package main.java.Command;


import main.java.Exception.DescriptionException;
import main.java.Exception.DukeDateTimeParserException;
import main.java.Parser.Parser;
import main.java.Storage.Storage;
import main.java.Task.DeadlineTask;
import main.java.Task.TaskList;
import main.java.Ui.Ui;

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
            Map<String, String> taskDetails = Parser.taskDescriptionParser(this.command);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            DeadlineTask deadlineTask = new DeadlineTask(taskDetails.get("taskDescription"),
                    LocalDateTime.parse(taskDetails.get("taskTime"), formatter));

            tasks.add(deadlineTask);

            ui.taskMessage(deadlineTask, tasks.size());

            storage.updateFile(tasks);
        } catch (DateTimeParseException e) {
            throw new DukeDateTimeParserException();
        }
    }
}
