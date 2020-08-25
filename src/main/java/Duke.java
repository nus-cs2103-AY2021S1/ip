import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Main class where Duke program is run.
 */
public class Duke {
    private TaskList tasks;
    private Storage storage;


    /**
     * Constructs the duke object.
     */
    public Duke() {
        try {
            this.storage = new Storage();
            this.tasks = storage.load();

        } catch (IOException e) {
            UI.printFormattedMessage("ERROR: File Loading error!");
        }
    }


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
                        UI.printListOfTasks(this.tasks.getTasks());
                    } else if (Parser.isDone(input)) {
                        int doneTask = Integer.parseInt(input.split(" ")[1]) - 1;
                        if (doneTask + 1 > tasks.numOfTasks() || doneTask < 0) {
                            throw new DukeInvalidDoneNumException(input);
                        }
                        tasks.markAsDone(doneTask);
                    } else if (Parser.isToDo(input)) {
                        if (input.split(" ").length == 1) {
                            throw new DukeEmptyToDoException(input);
                        }
                        String tasker = Parser.stringBuilder(input.split(" "), 1, input.split(" ").length - 1);
                        ToDo todoTask = new ToDo(tasker);
                        tasks.addTask(todoTask);
                        UI.printTaskAdd(todoTask, tasks.numOfTasks());
                    } else if (Parser.isDeadline(input)) {
                        if (input.split(" ").length == 1) {
                            throw new DukeEmptyDeadlineException(input);
                        }
                        String deadliner = Parser.stringBuilder(input.split(" "), 1, input.split(" ").length - 1);
                        String[] deadlinerparts = deadliner.split(" /by ");
                        if (deadlinerparts.length == 1) {
                            throw new DukeEmptyDeadlineTimeException(input);
                        }
                        Deadline deadlineTask = new Deadline(deadlinerparts[0], deadlinerparts[1]);
                        tasks.addTask(deadlineTask);
                        UI.printTaskAdd(deadlineTask, tasks.numOfTasks());
                    } else if (Parser.isEvent(input)) {
                        if (input.split(" ").length == 1) {
                            throw new DukeEmptyEventException(input);
                        }
                        String eventer = Parser.stringBuilder(input.split(" "), 1, input.split(" ").length - 1);
                        String[] eventParts = eventer.split(" /at ");
                        if (eventParts.length == 1) {
                            throw new DukeEmptyEventTimeException(input);
                        }
                        Event eventTask = new Event(eventParts[0], eventParts[1]);
                        tasks.addTask(eventTask);
                        UI.printTaskAdd(eventTask, this.tasks.numOfTasks());
                    } else if (Parser.isDelete(input)) {
                        int deleteTask = Integer.parseInt(input.split(" ")[1]) - 1;
                        if (deleteTask + 1 > tasks.numOfTasks() || deleteTask < 0) {
                            throw new DukeDeleteException(input);
                        }
                        UI.printDeleteMessage(tasks.getTask(deleteTask), tasks.numOfTasks() - 1);
                        tasks.deleteTask(deleteTask);
                    } else if (Parser.isFind(input)) {
                        String[] findParts = input.split(" ");
                        if (findParts.length == 1) {
                            throw new DukeEmptyFindException(input);
                        }
                        UI.printKeywordTasks(findParts[1], this.tasks.getTasks());
                    } else {
                        throw new DukeUnknownInputException(input);
                    }
                } catch (DukeUnknownInputException e) {
                    UI.printFormattedMessage("OOPS!!! I'm sorry, but I don't know what that means :-(");
                } catch (DukeEmptyToDoException e) {
                    UI.printFormattedMessage("OOPS!!! The description of a todo cannot be empty.");
                } catch (DukeEmptyEventException e) {
                    UI.printFormattedMessage("OOPS!!! The description of a event cannot be empty.");
                } catch (DukeEmptyDeadlineException e) {
                    UI.printFormattedMessage("OOPS!!! The description of a deadline cannot be empty.");
                } catch (DukeInvalidDoneNumException e) {
                    UI.printFormattedMessage("OOPS!!! The invalid done number.");
                } catch (DukeEmptyDeadlineTimeException e) {
                    UI.printFormattedMessage("OOPS!!! The description of a deadline time cannot be empty.");
                } catch (DukeEmptyEventTimeException e) {
                    UI.printFormattedMessage("OOPS!!! The description of a event time cannot be empty.");
                } catch (DukeDeleteException e) {
                    UI.printFormattedMessage("OOPS!!! The invalid delete number.");
                } catch (DateTimeParseException e) {
                    UI.printFormattedMessage("OOPS!!! The invalid date format has been keyed in. PLease enter in dd-MM-yyyy HH:mm format");
                } catch (DukeNoMatchesExcpetion e) {
                    UI.printFormattedMessage("ERROR: No matches found!");
                } catch (DukeEmptyFindException e) {
                    UI.printFormattedMessage("ERROR: Empty find body!");
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
