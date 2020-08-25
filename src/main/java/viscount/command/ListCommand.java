package viscount.command;

import viscount.*;

import viscount.exception.ViscountDateTimeParseException;
import viscount.exception.ViscountException;

import viscount.task.Task;
import viscount.task.TaskType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ListCommand extends Command {
    private String taskTypeModifier;
    private String dateString;
    private String findString;
    
    public ListCommand(String modifier, String dateString, String findString) {
        super();
        this.taskTypeModifier = modifier;
        this.dateString = dateString;
        this.findString = findString;
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ViscountException {
        Predicate<Task> filterByModifier = task -> taskTypeModifier.isEmpty()
                || task.getTaskType() == TaskType.valueOf(taskTypeModifier.toUpperCase());
        
        Predicate<Task> filterByDescription = task -> task.getDescription().contains(findString);
        
        List<Task> tasks = taskList.getTasks();

        if (dateString.isEmpty()) {
            List<Task> filteredTasks = tasks
                    .stream()
                    .filter(filterByModifier)
                    .filter(filterByDescription)
                    .collect(Collectors.toList());
            
            ui.showList(filteredTasks, taskTypeModifier, dateString);
        } else {
            try {
                LocalDateTime queriedDateTime = dateString.equals("today")
                        ? LocalDate.now().atStartOfDay()
                        : Parser.parseDateTime(dateString, Parser.INPUT_DATE_TIME_FORMATTER);

                List<Task> filteredTasks = tasks
                        .stream()
                        .filter(Task::hasDateTime)
                        .filter(filterByModifier)
                        .filter(filterByDescription)
                        .filter(task -> task.getDateTime().toLocalDate().isEqual(queriedDateTime.toLocalDate()))
                        .sorted(Comparator.comparing(Task::getDateTime))
                        .collect(Collectors.toList());

                ui.showList(
                        filteredTasks,
                        taskTypeModifier, 
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
