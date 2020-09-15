package ekud.commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ekud.exceptions.DukeException;
import ekud.exceptions.DukeInvalidParameterException;
import ekud.exceptions.DukeIoException;
import ekud.exceptions.DukeUnrecognisedCommandException;
import ekud.tasks.Deadline;
import ekud.tasks.Event;
import ekud.tasks.Task;
import ekud.tasks.TaskList;
import ekud.tasks.Todo;
import ekud.utils.Storage;
import ekud.utils.memento.Originator;


/**
 * The enum of all valid Commands
 */
public enum Command {
    LIST {
        @Override
        public String execute(HashMap<String, String> parameters) throws DukeInvalidParameterException {
            if (!parameters.isEmpty()) {
                throw new DukeInvalidParameterException(strings.getString("error.list"), parameters);
            }

            System.out.println(tasks);

            afterExecute(LIST);

            return IntStream.range(0, tasks.size())
                    .mapToObj(index -> (index + 1) + ". " + tasks.get(index))
                    .reduce("", (x, y) -> x + y)
                    .strip();
        }
    },
    /**
     * The Done.
     */
    DONE {
        @Override
        public String execute(HashMap<String, String> parameters) throws DukeException {
            if (!parameters.containsKey("argument")) {
                throw new DukeInvalidParameterException(strings.getString("error.done"), parameters);
            }
            try {
                int toMark = Integer.parseInt(parameters.get("argument")) - 1;
                tasks.markAsDone(toMark);
                afterExecute(DONE);
                return String.format(strings.getString("output.done"), tasks.get(toMark)).strip();
            } catch (NumberFormatException e) {
                throw new DukeInvalidParameterException(strings.getString("error.doneNum"), parameters);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeInvalidParameterException(strings.getString("error.doneOut"), parameters);
            }
        }
    },
    TODO {
        @Override
        public String execute(HashMap<String, String> parameters) throws DukeException {
            if (parameters.isEmpty()) {
                throw new DukeInvalidParameterException(strings.getString("error.todo"), parameters);
            }
            if (parameters.size() > 1) {
                throw new DukeInvalidParameterException(strings.getString("error.todoExtra"), parameters);
            }

            Task toAdd = new Todo(parameters.get("argument"));
            tasks.add(toAdd);

            afterExecute(TODO);
            return String.format(strings.getString("output.added"), toAdd, tasks.size());
        }
    },
    DEADLINE {
        @Override
        public String execute(HashMap<String, String> parameters) throws DukeException {
            if (parameters.isEmpty() || !parameters.containsKey(strings.getString("parameter.by"))) {
                throw new DukeInvalidParameterException(strings.getString("error.deadline"), parameters);
            }

            try {
                LocalDate deadline = LocalDate.parse(parameters.get(strings.getString("parameter.by")));

                Task toAdd = new Deadline(parameters.get("argument"), deadline);
                tasks.add(toAdd);

                afterExecute(DEADLINE);
                return String.format(strings.getString("output.added"), toAdd, tasks.size());

            } catch (DateTimeParseException e) {
                throw new DukeInvalidParameterException(strings.getString("error.dateFormat"), parameters);
            }
        }
    },
    EVENT {
        @Override
        public String execute(HashMap<String, String> parameters) throws DukeException {
            if (parameters.isEmpty() || !parameters.containsKey(strings.getString("parameter.at"))) {
                throw new DukeInvalidParameterException(strings.getString("error.event"), parameters);
            }

            try {
                LocalDate eventDate = LocalDate.parse(parameters.get(strings.getString("parameter.at")));

                Task toAdd = new Event(parameters.get("argument"), eventDate);
                tasks.add(toAdd);
                afterExecute(EVENT);
                return String.format(strings.getString("output.added"), toAdd, tasks.size());
            } catch (DateTimeParseException e) {
                throw new DukeInvalidParameterException(strings.getString("error.dateFormat"), parameters);
            }
        }
    },
    DELETE {
        @Override
        public String execute(HashMap<String, String> parameters) throws DukeException {
            if (!parameters.containsKey("argument")) {
                throw new DukeInvalidParameterException(strings.getString("error.delete"), parameters);
            }
            try {
                int toDelete = Integer.parseInt(parameters.get("argument")) - 1;
                String ret = String.format(strings.getString("output.delete"), tasks.remove(toDelete), tasks.size())
                        .strip();
                afterExecute(DELETE);
                return ret;
            } catch (NumberFormatException e) {
                throw new DukeInvalidParameterException(strings.getString("error.deleteNum"), parameters);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeInvalidParameterException(strings.getString("error.deleteOut"), parameters);
            }
        }
    },
    FIND {
        @Override
        public String execute(HashMap<String, String> parameters) throws DukeException {
            if (!parameters.containsKey("argument")) {
                throw new DukeInvalidParameterException(strings.getString("error.find"), parameters);
            }
            String searchTerm = parameters.get("argument");
            List<Task> matches = tasks
                    .getList()
                    .stream()
                    .filter(item -> item.getDescription().contains(searchTerm))
                    .collect(Collectors.toList());

            String output = IntStream.range(0, matches.size())
                    .mapToObj(index -> (index + 1) + ". " + matches.get(index) + "\t\t")
                    .reduce("", (x, y) -> x + y)
                    .strip();
            afterExecute(FIND);
            if (output.equals("")) {
                return strings.getString("output.findEmpty");
            } else {
                return String.format(strings.getString("output.find"), output);
            }
        }
    },
    UNDO {
        @Override
        public String execute(HashMap<String, String> parameters) throws DukeException {
            if (!parameters.containsKey("argument")) {
                parameters.put("argument", "1");
            }
            try {
                int numToUndo = Integer.parseInt(parameters.get("argument"));
                originator.restoreFromMemento(savedStates.get(savedStates.size() - numToUndo - 1));
                tasks = originator.getState();
                tasks.notifyObserver();

                String output = savedStates
                        .subList(savedStates.size() - numToUndo, savedStates.size())
                        .stream()
                        .map(Originator.Memento::getCommand)
                        .reduce("", (x, y) -> x + ", " + y);
                output = output.substring(1).strip();
                savedStates = savedStates.subList(0, savedStates.size() - numToUndo);
                return String.format(strings.getString("output.undo"), output);

            } catch (NumberFormatException e) {
                throw new DukeInvalidParameterException(strings.getString("error.doneNum"), parameters);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeInvalidParameterException(strings.getString("error.doneOut"), parameters);
            }

        }
    },
    BYE {
        @Override
        public String execute(HashMap<String, String> parameters) {
            return strings.getString("output.bye");
        }
    };

    private static final ResourceBundle strings = ResourceBundle.getBundle("StringsBundle", Locale.ENGLISH);

    private static TaskList tasks = new TaskList(new ArrayList<>());
    private static final Originator originator = new Originator();
    private static List<Originator.Memento> savedStates = new ArrayList<>();

    public static void setupCommands() throws DukeIoException {
        Storage storage = Storage.createStorage("database", "ekud.tasks.ser");
        tasks = new TaskList(storage.load());
        tasks.setObserver(storage);
        originator.set(tasks, "");
        savedStates.add(originator.saveToMemento());
    }

    /**
     * Factory method to choose the appropriate <code>Command</code> from a given <code>String</code>
     *
     * @param command the command as a <code>String</code>
     * @return the command as a <code>Command</code>
     * @throws DukeUnrecognisedCommandException thrown when command is invalid
     */
    public static Command createCommand(String command) throws DukeUnrecognisedCommandException {
        Command ret;
        if (command.equals(strings.getString("command.list"))) {
            ret = LIST;
        } else if (command.equals(strings.getString("command.done"))) {
            ret = DONE;
        } else if (command.equals(strings.getString("command.todo"))) {
            ret = TODO;
        } else if (command.equals(strings.getString("command.deadline"))) {
            ret = DEADLINE;
        } else if (command.equals(strings.getString("command.event"))) {
            ret = EVENT;
        } else if (command.equals(strings.getString("command.delete"))) {
            ret = DELETE;
        } else if (command.equals(strings.getString("command.find"))) {
            ret = FIND;
        } else if (command.equals(strings.getString("command.bye"))) {
            ret = BYE;
        } else if (command.equals(strings.getString("command.undo"))) {
            ret = UNDO;
        } else {
            throw new DukeUnrecognisedCommandException("Cannot Recognise Command ", command);
        }

        return ret;
    }

    /**
     * Cleans up command after execution. Common code relating to
     * saving current state after command has executed.
     *
     * @param command the command which was executed
     */
    public static void afterExecute(Command command) {
        originator.set(tasks, strings.getString("command." + command.name().toLowerCase()));
        savedStates.add(originator.saveToMemento());
    }


    /**
     * Executes the logic of the corresponding command.
     *
     * @param parameters the parameters
     * @return the output of the command as a <code>String</code>
     * @throws DukeException general parent exception
     */
    public abstract String execute(HashMap<String, String> parameters) throws DukeException;
}
