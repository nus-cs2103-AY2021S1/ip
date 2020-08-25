package viscount.command;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import viscount.*;

import viscount.exception.ViscountDateTimeParseException;
import viscount.exception.ViscountException;

import viscount.task.Task;
import viscount.task.TaskType;

public class ListCommand extends Command {
    private String modifier;
    private String dateString;
    
    public ListCommand(String modifier, String dateString) {
        super();
        this.modifier = modifier;
        this.dateString = dateString;
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ViscountException {
        Predicate<Task> filterByModifier = task -> modifier.isEmpty()
                || task.getTaskType() == TaskType.valueOf(modifier.toUpperCase());
        
        List<Task> tasks = taskList.getTasks();

        if (dateString.isEmpty()) {
            List<Task> filteredTasks = tasks
                    .stream()
                    .filter(filterByModifier)
                    .collect(Collectors.toList());
            
            ui.showList(filteredTasks, modifier, dateString);
        } else {
            try {
                LocalDateTime queriedDateTime = dateString.equals("today")
                        ? LocalDate.now().atStartOfDay()
                        : Parser.parseDateTime(dateString, Parser.INPUT_DATE_TIME_FORMATTER);

                List<Task> filteredTasks = tasks
                        .stream()
                        .filter(Task::hasDateTime)
                        .filter(filterByModifier)
                        .filter(task -> task.getDateTime().toLocalDate().isEqual(queriedDateTime.toLocalDate()))
                        .sorted(Comparator.comparing(Task::getDateTime))
                        .collect(Collectors.toList());

                ui.showList(
                        filteredTasks, 
                        modifier, 
                        dateString.equals("today") 
                                ? dateString 
                                : queriedDateTime.format(Parser.OUTPUT_DATE_FORMATTER));
            } catch (DateTimeParseException e) {
                throw new ViscountDateTimeParseException("date query");
            }
        }
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
}
