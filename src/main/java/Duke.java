import java.io.IOException;
import java.time.format.DateTimeParseException;


/**
 * Main class where Duke program is run.
 */
public class Duke {
    private TaskList tasks;
    private Storage storage;
    private UI ui;

    /**
     * Constructs the duke object.
     */
    public Duke() {
        try {
            this.storage = new Storage();
            this.tasks = storage.load();
            this.ui = new UI();

        } catch (IOException e) {
            ui.printFormattedMessage("OOPS!!! File Loading error!");
        }
    }


    /**
     * Runs the duke program and will terminate upon input "bye"
     *
     * @throws DukeUnknownInputException      If users inputs invalid input.
     * @throws DukeEmptyToDoException         If todo is empty.
     * @throws DukeEmptyEventException        If event is empty.
     * @throws DukeEmptyDeadlineException     If deadline is empty.
     * @throws DukeInvalidDoneNumException    If done number entered is invalid.
     * @throws DukeEmptyDeadlineTimeException If the deadline time is empty.
     * @throws DukeEmptyEventTimeException    If the event time is empty.
     * @throws DukeDeleteException            If there are any other exceptions.
     * @throws DateTimeParseException         If there is time passed in the wrong format.
     */
    public String run(String input) {
        String message = "";
        try {
            if (Parser.isList(input)) {
                ListCommand list = new ListCommand(input);
                message = list.execute(this.tasks, this.ui);
            } else if (Parser.isDone(input)) {
                DoneCommand done = new DoneCommand(input);
                message = done.execute(this.tasks, this.ui);
            } else if (Parser.isToDo(input)) {
                ToDoCommand todo = new ToDoCommand(input);
                message = todo.execute(this.tasks, this.ui);
            } else if (Parser.isDeadline(input)) {
                DeadlineCommand deadline = new DeadlineCommand(input);
                message = deadline.execute(this.tasks, this.ui);
            } else if (Parser.isEvent(input)) {
                EventCommand event = new EventCommand(input);
                message = event.execute(this.tasks, ui);
            } else if (Parser.isDelete(input)) {
                DeleteCommand delete = new DeleteCommand(input);
                message = delete.execute(this.tasks, ui);
            } else if (Parser.isFind(input)) {
                FindCommand find = new FindCommand(input);
                message = find.execute(this.tasks, this.ui);
            } else {
                throw new DukeUnknownInputException(input);
            }
        } catch (DukeUnknownInputException e) {
            message = e.getMessage();
        } catch (DukeEmptyDeadlineException e) {
            message = e.getMessage();
        } catch (DukeInvalidDoneNumException e) {
            message = e.getMessage();
        } catch (DukeEmptyDeadlineTimeException e) {
            message = e.getMessage();
        } catch (DukeDeleteException e) {
            message = e.getMessage();
        } catch (DateTimeParseException e) {
            message = e.getMessage();
        } catch (DukeEmptyFindException e) {
            message = e.getMessage();
        } catch (DukeEmptyEventTimeException e) {
            message = e.getMessage();
        } catch (DukeEmptyEventException e) {
            message = e.getMessage();
        } catch (DukeEmptyToDoException e) {
            message = e.getMessage();
        } catch (DukeEmptyTaskListException e) {
            message = e.getMessage();
        } catch (DukeNoMatchesExcpetion e) {
            message = e.getMessage();
        } catch (DukeTimeParseException e) {
            message = e.getMessage();
        }

        if (Parser.isBye(input)) {
            message = ui.printByeMessage();
        }
        storage.save(storage.convertArrayToSaveFormat(this.tasks));
        return message;
    }
}
