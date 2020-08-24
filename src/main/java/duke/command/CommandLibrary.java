package duke.command;

import java.util.List;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

class CommandLibrary {
    public static final CommandExecutable deadlineCommand = (taskList, ui, args) -> {
        String description = args[0].trim();
        if (args.length != 2 || description.equals("")) {
            throw DukeException.Errors.DEADLINE_BAD_FORMAT.create();
        }
        String time = args[1];
        ui.systemMessage(taskList.add(new Deadline(description, time)));
    };
    public static final CommandExecutable eventCommand = (taskList, ui, args) -> {
        String description = args[0].trim();
        if (args.length != 2 || description.equals("")) {
            throw DukeException.Errors.EVENT_BAD_FORMAT.create();
        }
        String time = args[1];
        ui.systemMessage(taskList.add(new Event(description, time)));
    };
    public static final CommandExecutable todoCommand = (taskList, ui, args) -> {
        String description = args[0];
        if (description.equals("")) {
            throw DukeException.Errors.TODO_EMPTY_DESCRIPTION.create();
        }
        ui.systemMessage(taskList.add(new Todo(description)));
    };
    public static final CommandExecutable deleteCommand = (taskList, ui, args) -> {
        int idx = Integer.parseInt(args[0]);
        if (idx > taskList.size()) {
            throw DukeException.Errors.DELETE_OUT_OF_RANGE.create();
        }
        Task selected = taskList.deleteItem(idx - 1);
        ui.systemMessage("sir this task has been remove sir:\n  " + selected);
    };
    public static final CommandExecutable doneCommand = (taskList, ui, args) -> {
        int idx = Integer.parseInt(args[0]);
        if (idx > taskList.size()) {
            throw DukeException.Errors.DONE_OUT_OF_RANGE.create();
        }
        Task selected = taskList.markItem(idx - 1);
        ui.systemMessage("afternoon sir i have mark this task done sir:\n  " + selected);
    };
    public static final CommandExecutable listCommand = (taskList, ui, args) ->
        ui.systemMessage(taskList.toString());
    public static final CommandExecutable byeCommand = (taskList, ui, args) -> {
        ui.close();
        ui.systemMessage("bye sir thanks for using me sir hope to see you again sir");
    };
    public static final CommandExecutable findCommand = (taskList, ui, args) -> {
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
