package duke.parser;

import java.time.LocalDateTime;
import java.util.function.Function;

import duke.exception.DukeParseException;
import duke.expense.Expense;
import duke.list.ExpenseList;
import duke.list.ListManager;
import duke.list.TaskList;
import duke.operation.DeleteOperation;
import duke.operation.DoneOperation;
import duke.operation.ExitOperation;
import duke.operation.FindOperation;
import duke.operation.ListOperation;
import duke.operation.Operation;
import duke.operation.addexpenseoperation.AddPayableOperation;
import duke.operation.addexpenseoperation.AddReceivableOperation;
import duke.operation.addtaskoperation.AddDeadlineTaskOperation;
import duke.operation.addtaskoperation.AddEventTaskOperation;
import duke.operation.addtaskoperation.AddTodoTaskOperation;
import duke.storage.StorageManager;
import duke.task.Deadline;
import duke.task.Event;
import duke.utils.Datetime;
import duke.utils.Utils;

/** The class that converts commands passed into Duke into <code>Operations</code>.*/
public class CommandParser {
    private static final String TASK = "task";
    private static final String EXPENSE = "expense";

    private ExitOperation createExitOp(
            StorageManager storageManager, ListManager listManager) {
        return new ExitOperation(storageManager, listManager);
    }

    private ListOperation createListOp(
            String[] commands, ListManager listManager) throws DukeParseException {
        if (CommandType.LIST.isNotValidLength(commands.length)) {
            throw new DukeParseException(
                    "Ensure either 'task' or 'expense' is input after 'list'.");
        }
        if (commands[1].equals(TASK)) {
            return new ListOperation(listManager.getTaskList());
        } else if (commands[1].equals(EXPENSE)) {
            return new ListOperation(listManager.getExpenseList());
        } else {
            throw new DukeParseException(
                    "Ensure either 'task' or 'expense' is spelled correctly.");
        }
    }

    private DoneOperation createDoneOp(String[] commands, TaskList list) throws DukeParseException {
        if (CommandType.DONE.isNotValidLength(commands.length)) {
            throw new DukeParseException("Ensure a number is passed after a done command.");
        }
        if (!Utils.hasInteger(commands, 1)) {
            throw new DukeParseException("Ensure a number is passed after a done command.");
        }
        int index = Integer.parseInt(commands[1]);
        return new DoneOperation(list, index);
    }

    private AddTodoTaskOperation createTodoOp(String[] commands, TaskList list) throws DukeParseException {
        if (CommandType.TODO.isNotValidLength(commands.length)) {
            throw new DukeParseException("Ensure there is description for a todo item.");
        }
        String description = Utils.concatenate(commands, 1, commands.length);
        return new AddTodoTaskOperation(description, list);
    }

    private AddDeadlineTaskOperation createDeadlineOp(
            String[] commands, TaskList list) throws DukeParseException {
        if (CommandType.DEADLINE.isNotValidLength(commands.length)) {
            throw new DukeParseException(
                    "Ensure there is a description and a datetime for a deadline command.");
        }

        int splitIndex = Utils.getIndexOf(commands, Deadline.DEADLINE_BREAK);
        if (splitIndex == Utils.INDEX_NOT_FOUND) {
            throw new DukeParseException("Ensure an indication of '/by' after a deadline command.");
        }

        String description = Utils.concatenate(commands, 1, splitIndex);
        String datetime = Utils.concatenate(commands, splitIndex + 1, commands.length);
        LocalDateTime parsedDateTime = Datetime.parseDateTimeString(datetime, Deadline.DATE_FORMAT_INPUT);
        return new AddDeadlineTaskOperation(description, parsedDateTime, list);
    }

    private AddEventTaskOperation createEventOp(String[] commands, TaskList list) throws DukeParseException {
        if (CommandType.EVENT.isNotValidLength(commands.length)) {
            throw new DukeParseException("Ensure there is a description and a time for an event command.");
        }

        int splitIndex = Utils.getIndexOf(commands, Event.EVENT_BREAK);
        if (splitIndex == Utils.INDEX_NOT_FOUND) {
            throw new DukeParseException("Ensure an indication of '/at' after an event command.");
        }

        String description = Utils.concatenate(commands, 1, splitIndex);
        String time = Utils.concatenate(commands, splitIndex + 1, commands.length);
        LocalDateTime parsedTime = Datetime.parseTimeString(time, Event.TIME_FORMAT_INPUT);
        return new AddEventTaskOperation(description, parsedTime, list);
    }

    private DeleteOperation createDeleteOp(
            String[] commands, ListManager listManager) throws DukeParseException {
        if (CommandType.DELETE.isNotValidLength(commands.length)) {
            throw new DukeParseException("Ensure a 'task' or 'expense' and number is passed.");
        }
        if (!Utils.hasInteger(commands, 2)) {
            throw new DukeParseException("Ensure a number is present in a delete command.");
        }
        int index = Integer.parseInt(commands[2]);

        if (commands[1].equals(TASK)) {
            return new DeleteOperation(listManager.getTaskList(), index);
        } else if (commands[1].equals(EXPENSE)) {
            return new DeleteOperation(listManager.getExpenseList(), index);
        } else {
            throw new DukeParseException("Ensure a 'task' or 'expense' is passed in.");
        }
    }

    private FindOperation createFindOp(
            String[] commands, ListManager listManager) throws DukeParseException {
        if (CommandType.FIND.isNotValidLength(commands.length)) {
            throw new DukeParseException(
                    "Ensure a 'task' or 'expense' and search word is entered.");
        }
        String searchWord = Utils.concatenate(commands, 2, commands.length);

        if (commands[1].equals(TASK)) {
            return new FindOperation(listManager.getTaskList(), searchWord);
        } else if (commands[1].equals(EXPENSE)) {
            return new FindOperation(listManager.getExpenseList(), searchWord);
        } else {
            throw new DukeParseException("Ensure a 'task' or 'expense' is passed in.");
        }
    }

    private AddPayableOperation createPayableOp(
            String[] commands, ExpenseList list) throws DukeParseException {
        if (CommandType.PAY.isNotValidLength(commands.length)) {
            throw new DukeParseException("Ensure a description, value and date are input.");
        }

        int splitIndex = Utils.getIndexOf(commands, Expense.EXPENSE_BREAK);
        if (splitIndex == Utils.INDEX_NOT_FOUND) {
            throw new DukeParseException("Ensure an indication of '/on' after a pay command.");
        }

        int moneyIndex = splitIndex - 1;
        if (!Utils.isMoney(commands[moneyIndex])) {
            throw new DukeParseException("Ensure money passed in is of the format: $30 or $30.00");
        }

        String description = Utils.concatenate(commands, 1, moneyIndex);
        double value = Utils.convertMoneyToValue(commands[moneyIndex]);
        String dateString = Utils.concatenate(commands, splitIndex + 1, commands.length);
        LocalDateTime date = Datetime.parseDateString(dateString, Expense.DATE_FORMAT_INPUT);

        return new AddPayableOperation(description, value, date, list);
    }

    private AddReceivableOperation createReceivableOp(
            String[] commands, ExpenseList list) throws DukeParseException {
        if (CommandType.RECEIVE.isNotValidLength(commands.length)) {
            throw new DukeParseException("Ensure a description, value and date are input.");
        }

        int splitIndex = Utils.getIndexOf(commands, Expense.EXPENSE_BREAK);
        if (splitIndex == Utils.INDEX_NOT_FOUND) {
            throw new DukeParseException("Ensure an indication of '/on' after a receivable command.");
        }

        int moneyIndex = splitIndex - 1;
        if (!Utils.isMoney(commands[moneyIndex])) {
            throw new DukeParseException("Ensure money passed in is of the format: $30 or $30.00");
        }

        String description = Utils.concatenate(commands, 1, moneyIndex);
        double value = Utils.convertMoneyToValue(commands[moneyIndex]);
        String dateString = Utils.concatenate(commands, splitIndex + 1, commands.length);
        LocalDateTime date = Datetime.parseDateString(dateString, Expense.DATE_FORMAT_INPUT);

        return new AddReceivableOperation(description, value, date, list);
    }

    /**
     * Parses the String given into an <code>Operation</code>.
     *
     * @param commandString the <code>String</code> that has been input by the user into Duke.
     * @param listManager the <code>ListManager</code> of Duke to be operated on.
     * @param storageManager the <code>TaskStorage</code> to be operated on,
     *                    if the <code>Operation</code> requires a save of the <code>TaskList</code>.
     * @return the parsed <code>Operation</code>.
     * @throws DukeParseException if the command cannot be recognised or is erroneous.
     */
    public Operation parse(
            String commandString, ListManager listManager, StorageManager storageManager)
            throws DukeParseException {
        String[] commands = commandString.split(" ");
        assert commands.length > 0 : "There is an error in the splitting of the command";
        Function<CommandType, Boolean> isCommand = commandType ->
                commandType.getCommand().equals(commands[0]);

        if (isCommand.apply(CommandType.BYE)) {
            return createExitOp(storageManager, listManager);
        } else if (isCommand.apply(CommandType.LIST)) {
            return createListOp(commands, listManager);
        } else if (isCommand.apply(CommandType.DONE)) {
            return createDoneOp(commands, listManager.getTaskList());
        } else if (isCommand.apply(CommandType.TODO)) {
            return createTodoOp(commands, listManager.getTaskList());
        } else if (isCommand.apply(CommandType.DEADLINE)) {
            return createDeadlineOp(commands, listManager.getTaskList());
        } else if (isCommand.apply(CommandType.EVENT)) {
            return createEventOp(commands, listManager.getTaskList());
        } else if (isCommand.apply(CommandType.DELETE)) {
            return createDeleteOp(commands, listManager);
        } else if (isCommand.apply(CommandType.FIND)) {
            return createFindOp(commands, listManager);
        } else if (isCommand.apply(CommandType.RECEIVE)) {
            return createReceivableOp(commands, listManager.getExpenseList());
        } else if (isCommand.apply(CommandType.PAY)) {
            return createPayableOp(commands, listManager.getExpenseList());
        } else {
            throw new DukeParseException("This command is not recognised unfortunately.");
        }
    }
}
