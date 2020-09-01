import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

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
            UI.printFormattedMessage("ERROR: File Loading error!");
        }
    }

    /**
     * Runs the whole duke program
     */
    public static void main(String[] args) {
        Duke dukeMessager = new Duke();
        dukeMessager.run();
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
    public void run() {
        Scanner sc = new Scanner(System.in);
        UI.printGreeting();
        if (sc.hasNext()) {
            String input = sc.nextLine();
            while (!Parser.isBye(input)) {
                try {
                    if (Parser.isList(input)) {
                        ListCommand list = new ListCommand(input);
                        list.execute(this.tasks, this.ui);
                    } else if (Parser.isDone(input)) {
                        DoneCommand done = new DoneCommand(input);
                        done.execute(this.tasks, this.ui);
                    } else if (Parser.isToDo(input)) {
                        ToDoCommand todo = new ToDoCommand(input);
                        todo.execute(this.tasks, this.ui);
                    } else if (Parser.isDeadline(input)) {
                        DeadlineCommand deadline = new DeadlineCommand(input);
                        deadline.execute(this.tasks, this.ui);
                    } else if (Parser.isEvent(input)) {
                        EventCommand event = new EventCommand(input);
                        event.execute(this.tasks, ui);
                    } else if (Parser.isDelete(input)) {
                        DeleteCommand delete = new DeleteCommand(input);
                        delete.execute(this.tasks, ui);
                    } else if (Parser.isFind(input)) {
                        FindCommand find = new FindCommand(input);
                        find.execute(this.tasks, this.ui);
                    } else {
                        throw new DukeUnknownInputException(input);
                    }
                } catch (DukeUnknownInputException e) {
                    UI.printFormattedMessage("OOPS!!! I'm sorry, but I don't know what that means :-(");
                } catch (DukeEmptyDeadlineException e) {
                    UI.printFormattedMessage("OOPS!!! The description of a deadline cannot be empty.");
                } catch (DukeInvalidDoneNumException e) {
                    UI.printFormattedMessage("OOPS!!! The invalid done number.");
                } catch (DukeEmptyDeadlineTimeException e) {
                    UI.printFormattedMessage("OOPS!!! The description of a deadline time cannot be empty.");
                } catch (DukeDeleteException e) {
                    UI.printFormattedMessage("OOPS!!! The invalid delete number.");
                } catch (DateTimeParseException e) {
                    UI.printFormattedMessage("OOPS!!! The invalid date format has "
                            + "been keyed in. PLease enter in dd-MM-yyyy HH:mm format");
                } catch (DukeEmptyFindException e) {
                    UI.printFormattedMessage("ERROR: Empty find body!");
                } catch (DukeEmptyEventTimeException e) {
                    UI.printFormattedMessage("OOPS!!! The description of a event time cannot be empty.");
                } catch (DukeEmptyEventException e) {
                    UI.printFormattedMessage("OOPS!!! The description of a event cannot be empty.");
                } catch (DukeEmptyToDoException e) {
                    UI.printFormattedMessage("OOPS!!! The description of a todo cannot be empty.");
                } catch (DukeEmptyTaskListException e) {
                    ui.printFormattedMessage("OOPS!!! There are no tasks entered!.");
                } catch (DukeNoMatchesExcpetion e) {
                    ui.printFormattedMessage("ERROR: No matches found!");
                }
                if (sc.hasNext()) {
                    input = sc.nextLine();
                }
            }
            UI.printByeMessage();
        }
        storage.save(storage.convertArrayToSaveFormat(this.tasks));
    }
}
