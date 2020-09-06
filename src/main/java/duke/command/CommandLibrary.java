package duke.command;

import java.util.List;

import org.apache.commons.cli.CommandLine;

import duke.exception.DukeException;
import duke.exception.DukeParseException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

class CommandLibrary {
    static final CommandExecutable DEADLINE_COMMAND = (taskList, ui, args) -> {
        String description = getUnusedArgs(args);
        String time = args.getOptionValue("by");
        ui.systemMessage(taskList.add(new Deadline(description, time)));
    };
    static final CommandExecutable EVENT_COMMAND = (taskList, ui, args) -> {
        String description = getUnusedArgs(args);
        String time = args.getOptionValue("at");
        ui.systemMessage(taskList.add(new Event(description, time)));
    };
    static final CommandExecutable TODO_COMMAND = (taskList, ui, args) -> {
        String description = getUnusedArgs(args);
        ui.systemMessage(taskList.add(new Todo(description)));
    };
    static final CommandExecutable DELETE_COMMAND = (taskList, ui, args) -> {
        int index = parseInt(getUnusedArgs(args));
        if (index > taskList.size()) {
            throw DukeException.Errors.DELETE_OUT_OF_RANGE.create();
        }
        Task selected = taskList.deleteItem(index - 1);
        ui.systemMessage("sir this task has been remove sir:\n  " + selected);
    };
    static final CommandExecutable DONE_COMMAND = (taskList, ui, args) -> {
        int index = parseInt(getUnusedArgs(args));
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
        String keyword = getUnusedArgs(args);
        for (int i = 0; i < items.size(); i++) {
            // if description does not contain string
            if (!items.get(i).getDescription().contains(keyword)) {
                items.set(i, null);
            }
        }
        ui.systemMessage("sir i found your items sir look:");
        ui.systemMessage(TaskList.enumerateItems(items));
    };
    private static String getUnusedArgs(CommandLine args) throws DukeParseException {
        List<String> unconsumedArgs = args.getArgList();
        if (unconsumedArgs.size() == 0) {
            throw new DukeParseException("this command requires non-empty arguments.");
        }
        return String.join("", unconsumedArgs);
    }
    private static int parseInt(String input) throws DukeParseException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new DukeParseException("unexpected input, expected integer input.");
        }
    }
}
