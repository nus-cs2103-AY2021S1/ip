package duke;

import static duke.DukeCommandType.DEADLINE;
import static duke.DukeCommandType.DELETE;
import static duke.DukeCommandType.DONE;
import static duke.DukeCommandType.EVENT;
import static duke.DukeCommandType.TODO;

import java.time.format.DateTimeParseException;

/**
 * CommandHandler class will handle all the related mechanisms initiated by user's command.
 */
public class CommandHandler {
    /** Input's command type */
    protected DukeCommandType commandType;

    /** User's input */
    private String input;

    /**
     * Initialises CommandHandler with user's input and its command type.
     *
     * @param input
     * @param commandType
     */
    public CommandHandler(String input, DukeCommandType commandType) {
        this.input = input;
        this.commandType = commandType;
    }

    /**
     * Handles user's inputs depending on command type.
     *
     * @param input
     * @param commandType
     * @param tasks
     */
    public static String handleCommands(String input, DukeCommandType commandType, TaskList tasks) {
        String task;
        String output = "";
        switch (commandType) {
        case TODO:
            try {
                task = input.split("todo ")[1];
                assert !task.isEmpty() : "Task shouldn't be empty!";
                Task newTask = new ToDo(task);
                output += tasks.addTask(newTask);
            } catch (ArrayIndexOutOfBoundsException exception) {
                try {
                    throw new DukeException("", DukeExceptionType.MISSING_DESCRIPTION, TODO);
                } catch (DukeException e) {
                    output += e;
                }
            }
            break;
        case DEADLINE:
            try {
                if (input.split("deadline ").length < 2) {
                    throw new DukeException("", DukeExceptionType.WRONG_FORMAT, DEADLINE);
                } else if (input.contains("/at")) {
                    throw new DukeException("", DukeExceptionType.WRONG_FORMAT, DEADLINE);
                } else if (!input.contains("/by ")) {
                    if (input.equals("deadline /by")) {
                        throw new DukeException("", DukeExceptionType.WRONG_FORMAT, DEADLINE);
                    } else {
                        throw new DukeException("", DukeExceptionType.MISSING_TIMING, DEADLINE);
                    }
                } else if (input.split("/by ").length < 2) {
                    throw new DukeException("", DukeExceptionType.WRONG_FORMAT, DEADLINE);
                } else {
                    try {
                        task = input.split("deadline ")[1].split("/by ")[0];
                        assert !task.isEmpty() : "Task shouldn't be empty!";
                        String due = input.split("deadline ")[1].split("/by ")[1];
                        assert !due.isEmpty() : "Deadline shouldn't be empty!";
                        if (task.equals("") && due.equals("")) {
                            throw new DukeException("", DukeExceptionType.WRONG_FORMAT, DEADLINE);
                        } else if (task.equals("")) {
                            throw new DukeException("", DukeExceptionType.MISSING_DESCRIPTION, DEADLINE);
                        } else if (due.equals("")) {
                            throw new DukeException("", DukeExceptionType.MISSING_TIMING, DEADLINE);
                        } else {
                            try {
                                Task newTask = new Deadline(task, due);
                                output += tasks.addTask(newTask);
                            } catch (DateTimeParseException e) {
                                DukeException.wrongTimeFormat();
                            }
                        }
                    } catch (DukeException e) {
                        output += e;
                    }
                }
            } catch (DukeException e) {
                output += e;
            }
            break;
        case EVENT:
            try {
                if (input.split("event ").length < 2) {
                    throw new DukeException("", DukeExceptionType.WRONG_FORMAT, EVENT);
                } else if (input.contains("/by")) {
                    throw new DukeException("", DukeExceptionType.WRONG_FORMAT, EVENT);
                } else if (!input.contains("/at ")) {
                    if (input.equals("event /at")) {
                        throw new DukeException("", DukeExceptionType.WRONG_FORMAT, EVENT);
                    } else {
                        throw new DukeException("", DukeExceptionType.MISSING_TIMING, EVENT);
                    }
                } else if (input.split("/at ").length < 2) {
                    throw new DukeException("", DukeExceptionType.WRONG_FORMAT, EVENT);
                } else {
                    try {
                        task = input.split("event ")[1].split("/at ")[0];
                        assert !task.isEmpty() : "Event shouldn't be empty!";
                        String due = input.split("event ")[1].split("/at ")[1];
                        assert !due.isEmpty() : "Scheduled date shouldn't be empty!";
                        if (task.equals("") && due.equals("")) {
                            throw new DukeException("", DukeExceptionType.WRONG_FORMAT, EVENT);
                        } else if (task.equals("")) {
                            throw new DukeException("", DukeExceptionType.MISSING_DESCRIPTION, DEADLINE);
                        } else if (due.equals("")) {
                            throw new DukeException("", DukeExceptionType.MISSING_TIMING, DEADLINE);
                        } else {
                            Task newTask = new Event(task, due);
                            output += tasks.addTask(newTask);
                        }
                    } catch (DukeException e) {
                        output += e;
                    } catch (DateTimeParseException e) {
                        DukeException.wrongTimeFormat();
                    }
                }
            } catch (DukeException e) {
                output += e;
            }
            break;
        case LIST:
            output += tasks.getListOfTasks();
            break;
        case FIND:
            String keyword = input.split(" ")[1];
            assert !keyword.isEmpty() : "Keyword shouldn't be empty!";
            System.out.println(keyword);
            output += tasks.findTasks(keyword);
            break;
        case DONE:
            try {
                String indexString = input.split(" ")[1];
                assert !indexString.isEmpty() : "Index shouldn't be empty!";
                int index = Integer.parseInt(indexString);
                output += tasks.markDone(index);
            } catch (IndexOutOfBoundsException exception) {
                try {
                    throw new DukeException("", DukeExceptionType.INVALID_INDEX, DONE);
                } catch (DukeException e) {
                    output += e;
                }
            }
            break;
        case DELETE:
            try {
                String indexString = input.split(" ")[1];
                assert !indexString.isEmpty() : "Index shouldn't be empty!";
                int index = Integer.parseInt(indexString);
                output += tasks.deleteTask(index);
            } catch (IndexOutOfBoundsException exception) {
                try {
                    throw new DukeException("", DukeExceptionType.INVALID_INDEX, DELETE);
                } catch (DukeException e) {
                    output += e;
                }
            }
            break;
        case HELP:
            output += Ui.getListOfCommands();
            break;
        case EXIT:
            output += Ui.exit();
            break;
        default:
            try {
                throw new DukeException("", DukeExceptionType.UNKNOWN);
            } catch (DukeException e) {
                output += e;
            }
            break;
        }
        assert !output.equals(null) : "Output should not be null.";
        return output;
    }
}
