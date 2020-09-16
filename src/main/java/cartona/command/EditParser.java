package cartona.command;

import cartona.DateParser;
import cartona.exception.CartonaException;
import cartona.exception.InvalidEditFieldException;
import cartona.exception.InvalidTaskTimeException;

import cartona.task.Deadline;
import cartona.task.Event;
import cartona.task.Task;
import cartona.task.TaskDate;
import cartona.task.TaskList;
import cartona.task.Todo;

public class EditParser {

    private Task parseTodo(String[] editFields) throws InvalidEditFieldException {

        // Start from 1 because the first element will include the part of the string before the '/'
        String newField = editFields[1];

        // Check that field of the Todo to be edited is the name
        if (!newField.split(" ")[0].equals("name")) {
            throw new InvalidEditFieldException("Error: Unrecognized field name in Todo");
        }

        return new Todo(newField.substring(5, newField.length() - 2), false);
    }

    private Deadline parseDeadline(String[] editFields, Deadline taskToEdit) throws InvalidEditFieldException,
            InvalidTaskTimeException {

        String name = taskToEdit.getName();
        TaskDate dueTime = taskToEdit.getDueTime();

        // Iterate across the fields to be changed
        for (int i = 1; i < editFields.length; i++) {
            String nextField = editFields[i];
            String fieldName = nextField.split(" ")[0];

            // Iterate through the fields the user wants to change, updating accordingly
            switch (fieldName) {

            case ("name"):
                name = nextField.substring(5, nextField.length() - 2);
                break;
            case ("due"):
                String dateString = nextField.substring(4, nextField.length() - 2);
                dueTime = DateParser.parseDate(dateString);
                break;
            default:
                throw new InvalidEditFieldException("Error: Unrecognised field name in Deadline");
            }
        }

        return new Deadline(name, false, dueTime);
    }

    private Event parseEvent(String[] editFields, Event taskToEdit) throws InvalidTaskTimeException,
            InvalidEditFieldException {
        String name = taskToEdit.getName();
        TaskDate startDate = taskToEdit.getStartDate();
        TaskDate endDate = taskToEdit.getEndDate();

        for (int i = 1; i < editFields.length; i++) {
            String nextField = editFields[i];
            String fieldName = nextField.split(" ")[0];

            switch (fieldName) {

            case ("name"):
                name = nextField.substring(5, nextField.length() - 2);
                break;
            case ("start"):
                String startString = nextField.substring(6, nextField.length() - 2);
                startDate = DateParser.parseDate(startString);
                break;
            case ("end"):
                String endString = nextField.substring(4, nextField.length() - 2);
                endDate = DateParser.parseDate(endString);
                break;
            case ("range"):
                String rangeString = nextField.substring(6, nextField.length() - 2);
                break;
            default:
                throw new InvalidEditFieldException("Error: Unrecognised field name in Event");
            }
        }

        return new Event(name, false, startDate, endDate);
    }

    Task parseReplacement(TaskList taskList, int taskIdToEdit, String editString) throws CartonaException,
            InvalidEditFieldException, InvalidTaskTimeException {
        Task taskToEdit = taskList.getTask(taskIdToEdit);

        String taskType = taskToEdit.getType();

        Task replacementTask = null;

        String[] editFields = editString.split("/");

        switch (taskType) {

        case ("T"):
            replacementTask = parseTodo(editFields);
            break;
        case ("D"):
            replacementTask = parseDeadline(editFields, (Deadline) taskToEdit);
            break;
        case ("E"):
            replacementTask = parseEvent(editFields, (Event) taskToEdit);
            break;
        default:
            throw new CartonaException("Invalid task type");
        }

        return replacementTask;
    }
}
