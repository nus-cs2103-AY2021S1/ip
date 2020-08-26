package main.java.command;

import main.java.exception.DescriptionException;
import main.java.exception.DukeDateTimeParserException;

import main.java.parser.Parser;
import main.java.storage.Storage;
import main.java.task.EventTask;
import main.java.task.TaskList;
import main.java.ui.Ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;

public class EventCommand extends Command {

    private String command;

    public EventCommand(String command) {
        super();
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DescriptionException, DukeDateTimeParserException {
        try {
            Map<String, String> taskDetails = Parser.findDescriptionParser(this.command);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            EventTask eventTask = new EventTask(taskDetails.get("taskDescription"),
                    LocalDateTime.parse(taskDetails.get("taskTime"), formatter));

            tasks.add(eventTask);

            ui.getTaskMessage(eventTask, tasks.size());

            storage.updateFile(tasks);
        } catch (DescriptionException e){
            throw new DescriptionException();
        } catch (DateTimeParseException e) {
            throw new DukeDateTimeParserException();
        }
    }
}
