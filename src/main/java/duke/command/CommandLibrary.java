package duke.command;

import java.util.List;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

class CommandLibrary {
    static final CommandExecutable DEADLINE_COMMAND = (taskList, ui, args) -> {
        String description = args[0].trim();
        if (args.length != 2 || "".equals(description)) {
            throw DukeException.Errors.DEADLINE_BAD_FORMAT.create();
        }
        String time = args[1];
        ui.systemMessage(taskList.add(new Deadline(description, time)));
    };
    static final CommandExecutable EVENT_COMMAND = (taskList, ui, args) -> {
        String description = args[0].trim();
        if (args.length != 2 || "".equals(description)) {
            throw DukeException.Errors.EVENT_BAD_FORMAT.create();
        }
        String time = args[1];
        ui.systemMessage(taskList.add(new Event(description, time)));
    };
    static final CommandExecutable TODO_COMMAND = (taskList, ui, args) -> {
        String description = args[0];
        if ("".equals(description)) {
            throw DukeException.Errors.TODO_EMPTY_DESCRIPTION.create();
        }
        ui.systemMessage(taskList.add(new Todo(description)));
    };
    static final CommandExecutable DELETE_COMMAND = (taskList, ui, args) -> {
        int index = Integer.parseInt(args[0]);
        if (index > taskList.size()) {
            throw DukeException.Errors.DELETE_OUT_OF_RANGE.create();
        }
        Task selected = taskList.deleteItem(index - 1);
        ui.systemMessage("sir this task has been remove sir:\n  " + selected);
    };
    static final CommandExecutable DONE_COMMAND = (taskList, ui, args) -> {
        int index = Integer.parseInt(args[0]);
        if (index > taskList.size()) {
            throw DukeException.Errors.DONE_OUT_OF_RANGE.create();
        }
        Task selected = taskList.markItem(index - 1);
        ui.systemMessage("afternoon sir i have mark this task done sir:\n  " + selected);
    };
    static final CommandExecutable LIST_COMMAND = (taskList, ui, args) ->
        ui.systemMessage(taskList.toString());
    static final CommandExecutable BYE_COMMAND = (taskList, ui, args) -> {
        ui.close();
        ui.systemMessage("bye sir thanks for using me sir hope to see you again sir");
    };
    static final CommandExecutable FIND_COMMAND = (taskList, ui, args) -> {
        List<Task> items = taskList.getItemsList();
        for (int i = 0; i < items.size(); i++) {
            // if description does not contain string
            if (!items.get(i).getDescription().contains(args[0])) {
                items.set(i, null);
            }
        }
        ui.systemMessage("sir i found your items sir look:");
        ui.systemMessage(TaskList.enumerateItems(items));
    };
}
