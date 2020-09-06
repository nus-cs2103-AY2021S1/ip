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
    static final CommandExecutable DEADLINE_COMMAND = (context, args) -> {
        String description = getUnusedArgs(args);
        String time = args.getOptionValue("by");
        String message = context.getTaskList().add(new Deadline(description, time));
        context.getUi().systemMessage(message);
    };
    static final CommandExecutable EVENT_COMMAND = (context, args) -> {
        String description = getUnusedArgs(args);
        String time = args.getOptionValue("at");
        String message = context.getTaskList().add(new Event(description, time));
        context.getUi().systemMessage(message);
    };
    static final CommandExecutable TODO_COMMAND = (context, args) -> {
        String description = getUnusedArgs(args);
        String message = context.getTaskList().add(new Todo(description));
        context.getUi().systemMessage(message);
    };
    static final CommandExecutable DELETE_COMMAND = (context, args) -> {
        int index = parseInt(getUnusedArgs(args));
        if (index > context.getTaskList().size()) {
            throw DukeException.Errors.DELETE_OUT_OF_RANGE.create();
        }
        Task selected = context.getTaskList().deleteItem(index - 1);
        context.getUi().systemMessage("sir this task has been remove sir:\n  " + selected);
    };
    static final CommandExecutable DONE_COMMAND = (context, args) -> {
        int index = parseInt(getUnusedArgs(args));
        if (index > context.getTaskList().size()) {
            throw DukeException.Errors.DONE_OUT_OF_RANGE.create();
        }
        Task selected = context.getTaskList().markItem(index - 1);
        context.getUi().systemMessage("afternoon sir i have mark this task done sir:\n  " + selected);
    };
    static final CommandExecutable LIST_COMMAND = (context, args) ->
        context.getUi().systemMessage(context.getTaskList().toString());
    static final CommandExecutable BYE_COMMAND = (context, args) -> {
        context.getUi().close();
        context.getUi().systemMessage("bye sir thanks for using me sir hope to see you again sir");
    };
    static final CommandExecutable FIND_COMMAND = (context, args) -> {
        List<Task> items = context.getTaskList().getItemsList();
        String keyword = getUnusedArgs(args);
        for (int i = 0; i < items.size(); i++) {
            // if description does not contain string
            if (!items.get(i).getDescription().contains(keyword)) {
                items.set(i, null);
            }
        }
        context.getUi().systemMessage("sir i found your items sir look:");
        context.getUi().systemMessage(TaskList.enumerateItems(items));
    };
    private static String getUnusedArgs(CommandLine args) throws DukeParseException {
        List<String> unconsumedArgs = args.getArgList();
        if (unconsumedArgs.size() == 0) {
            throw new DukeParseException("this command requires non-empty arguments.");
        }
        return String.join(" ", unconsumedArgs);
    }
    private static int parseInt(String input) throws DukeParseException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new DukeParseException("unexpected input, expected integer input.");
        }
    }
}
