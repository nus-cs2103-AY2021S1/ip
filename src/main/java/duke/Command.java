package duke;

import duke.exceptions.DukeEmptyMessageException;
import duke.exceptions.DukeInvalidMessageException;
import duke.exceptions.DukeMissingTimeException;

public class Command {

    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for Command.
     *
     * @param taskList taskList
     * @param ui Ui
     */
    public Command(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Marks an item in the list as done and prints the necessary string message.
     *
     * @param toEcho command and description number.
     * @return String string from ui.printDone.
     * @throws DukeEmptyMessageException thrown when description is empty.
     * @throws DukeInvalidMessageException thrown when description is not a valid number.
     */
    public String handleDone(String toEcho) throws DukeEmptyMessageException, DukeInvalidMessageException {
        String[] command = Parser.splitCommandAndDescription(toEcho);
        if (toEcho.length() == 4) {
            throw new DukeEmptyMessageException("Done");
        }
        if (Integer.parseInt(command[1]) > TaskList.getTaskLists().size()) {
            throw new DukeInvalidMessageException();

        }
        assert (toEcho.length() > 4);
        int index = Integer.parseInt(command[1]) - 1;
        taskList.markDone(index);
        return ui.printDone(index);
    }

    /**
     * Adds todo into the list and print the relevant todo message.
     *
     * @param description description of todo.
     * @return String string returned from ui.printTask.
     */
    public String handleTodo(String description) throws DukeEmptyMessageException {
        String[] command = Parser.splitCommandAndDescription(description);
        if (description.length() == 4) {
            throw new DukeEmptyMessageException("Todo");
        }
        assert (description.length() > 4);
        Todo todo = new Todo(command[1]);
        TaskList.getTaskLists().add(todo);
        return ui.printTask(todo);
    }

    /**
     * Adds deadline into the list and print the relevant deadline message.
     *
     * @param toEcho full description of deadline, including the command.
     * @return String string returned from ui.printTask.
     * @throws DukeMissingTimeException thrown when user does not input the timing required.
     */
    public String handleDeadline(String toEcho) throws DukeMissingTimeException {
        try {
            String[] command = Parser.splitCommandAndDescription(toEcho);
            if (toEcho.length() == 8) {
                throw new DukeEmptyMessageException("Deadline");
            }
            assert (toEcho.length() > 8);
            String[] strArr = Parser.splitDeadlineTime(command[1]);
            String todo = strArr[0];
            String time = strArr[1];
            Deadline deadline = new Deadline(todo, time);
            taskList.add(deadline);
            return ui.printTask(deadline);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeMissingTimeException();
        } catch (DukeEmptyMessageException e) {
            return e.getMessage();
        }
    }

    /**
     * Adds event into the list and print the relevant event message.
     *
     * @param toEcho full description of event, including command.
     * @return String string returned from ui.printTask.
     * @throws DukeMissingTimeException thrown when user does not input the timing required.
     */
    public String handleEvent(String toEcho) throws DukeMissingTimeException {
        try {
            String[] command = Parser.splitCommandAndDescription(toEcho);
            if (toEcho.length() == 5) {
                throw new DukeEmptyMessageException("Event");
            }
            assert (toEcho.length() > 5);
            String[] strArr = Parser.splitEventTime(command[1]);
            String todo = strArr[0];
            String time = strArr[1];
            Event event = new Event(todo, time);
            taskList.add(event);
            return ui.printTask(event);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeMissingTimeException();
        } catch (DukeEmptyMessageException e) {
            return e.getMessage();
        }
    }

    /**
     * Deletes an item from the list.
     *
     * @param toEcho delete command and description of type number.
     * @return String string returned from ui.printDelete.
     * @throws DukeEmptyMessageException thrown when description is empty.
     * @throws DukeInvalidMessageException thrown when description is not a valid number.
     */
    public String handleDelete(String toEcho) throws DukeEmptyMessageException, DukeInvalidMessageException {
        String[] command = Parser.splitCommandAndDescription(toEcho);
        if (toEcho.length() == 6) {
            throw new DukeEmptyMessageException("Delete");
        }
        if (Integer.parseInt(command[1]) > TaskList.getTaskLists().size()
                || Integer.parseInt(command[1]) < 0) {
            throw new DukeInvalidMessageException();
        }
        assert (toEcho.length() > 6);
        int indexToDelete = Integer.parseInt(command[1]) - 1;
        return ui.printDelete(indexToDelete);
    }

    /**
     * Finds the keyword passed in argument, and returns the items from list which
     * matches the keyword.
     *
     * @param toEcho command and description.
     * @return String string returned from ui.printFind.
     * @throws DukeEmptyMessageException thrown when keyword is empty
     */
    public String handleFind(String toEcho) throws DukeEmptyMessageException {
        String[] command = Parser.splitCommandAndDescription(toEcho);
        if (toEcho.length() == 4) {
            throw new DukeEmptyMessageException("Find");
        }
        assert (toEcho.length() > 4);
        return ui.printFind(command[1]);
    }

    /**
     * Parses the command with the description and prints the statistics depending on the
     * description.
     *
     * @param toEcho full description
     * @return String string of message
     * @throws DukeEmptyMessageException thrown when message is empty
     */
    public String findStats(String toEcho) throws DukeEmptyMessageException {
        String[] command = Parser.splitCommandAndDescription(toEcho);
        if (toEcho.length() == 5) {
            throw new DukeEmptyMessageException("Statistics");
        }
        assert (toEcho.length() > 5);
        return ui.printStats(command[1]);
    }
}
