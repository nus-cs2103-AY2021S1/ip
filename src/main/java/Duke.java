import exceptions.DukeException;
import exceptions.DukeInvalidParameterException;
import exceptions.DukeUnrecognisedCommandException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.util.*;
import java.util.stream.IntStream;

public class Duke {

    private static ResourceBundle strings;
    private static Scanner scanner;
    private static ArrayList<Task> tasks;

    private static void initializeDuke() {
        // read the appropriate string resources
        strings = ResourceBundle.getBundle("resources.StringsBundle", Locale.ENGLISH);
        scanner = new Scanner(System.in);
        tasks = new ArrayList<>();
    }

    private static void printWithDecoration(Object object) {
        String lineDecoration = "\t" + "_".repeat(60);
        System.out.println(lineDecoration);
        System.out.println("\t" + object);
        System.out.println(lineDecoration);
    }

    private static HashMap<String, String> parseParameters(String input) {
        HashMap<String, String> parameters = new HashMap<>();

        if (input.equals("")) {
            return parameters;
        }

        List<String> split = Arrays.asList(input.split("/"));
        parameters.put("argument", split.get(0).strip());

        for (String pair : split.subList(1, split.size())) {
            String key = pair.split(" ")[0];
            parameters.put(key, pair.replace(key, "").strip());
        }

        return parameters;
    }

    private static String handleList(HashMap<String, String> parameters) throws DukeException {

        if (!parameters.isEmpty()) {
            throw new DukeInvalidParameterException(strings.getString("error.list"), parameters);
        }

        return IntStream.range(0, tasks.size())
                .mapToObj(index -> (index + 1) + ". " + tasks.get(index) + "\t")
                .reduce("", (x, y) -> x + y)
                .strip();
    }

    private static String handleDone(HashMap<String, String> parameters) throws DukeException {
        if (!parameters.containsKey("argument")) {
            throw new DukeInvalidParameterException(strings.getString("error.done"), parameters);
        }
        try {
            int toMark = Integer.parseInt(parameters.get("argument")) - 1;
            tasks.get(toMark).markAsDone();
            return String.format(strings.getString("output.done"), tasks.get(toMark)).strip();
        } catch (NumberFormatException e) {
            throw new DukeInvalidParameterException(strings.getString("error.doneNum"), parameters);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidParameterException(strings.getString("error.doneOut"), parameters);
        }
    }

    private static String handleTodo(HashMap<String, String> parameters) throws DukeException {
        if (parameters.isEmpty()) {
            throw new DukeInvalidParameterException(strings.getString("error.todo"), parameters);
        }
        if (parameters.size() > 1) {
            throw new DukeInvalidParameterException(strings.getString("error.todoExtra"), parameters);
        }

        Task toAdd = new Todo(parameters.get("argument"));
        tasks.add(toAdd);

        return String.format(strings.getString("output.added"), toAdd, tasks.size());
    }

    private static String handleDeadline(HashMap<String, String> parameters) throws DukeException {
        if (parameters.isEmpty() || !parameters.containsKey(strings.getString("parameter.by"))) {
            throw new DukeInvalidParameterException(strings.getString("error.deadline"), parameters);
        }

        Task toAdd = new Deadline(parameters.get("argument"), parameters.get(strings.getString("parameter.by")));
        tasks.add(toAdd);

        return String.format(strings.getString("output.added"), toAdd, tasks.size());
    }

    private static String handleEvent(HashMap<String, String> parameters) throws DukeException {
        if (parameters.isEmpty() || !parameters.containsKey(strings.getString("parameter.at"))) {
            throw new DukeInvalidParameterException(strings.getString("error.event"), parameters);
        }

        Task toAdd = new Event(parameters.get("argument"), parameters.get(strings.getString("parameter.at")));
        tasks.add(toAdd);

        return String.format(strings.getString("output.added"), toAdd, tasks.size());
    }

    public static void main(String[] args) {

        initializeDuke();

        printWithDecoration(strings.getString("output.greeting"));

        String input, inputMainCommand, output;
        HashMap<String, String> parameters;

        while (scanner.hasNext()) {
            input = scanner.nextLine();
            inputMainCommand = input.split(" ")[0];
            parameters = parseParameters(input.replace(inputMainCommand, "").strip());

            try {
                if (inputMainCommand.equals(strings.getString("command.list"))) {
                    output = handleList(parameters);
                } else if (inputMainCommand.equals(strings.getString("command.done"))) {
                    output = handleDone(parameters);
                } else if (inputMainCommand.equals(strings.getString("command.todo"))) {
                    output = handleTodo(parameters);
                } else if (inputMainCommand.equals(strings.getString("command.deadline"))) {
                    output = handleDeadline(parameters);
                } else if (inputMainCommand.equals(strings.getString("command.event"))) {
                    output = handleEvent(parameters);
                } else if (input.equals(strings.getString("command.bye"))) {
                    break;
                } else {
                    throw new DukeUnrecognisedCommandException("Cannot Recognise Command ", inputMainCommand);
                }

                printWithDecoration(output);
            } catch (DukeException e) {
                printWithDecoration(e.getMessage());
            }

        }

        printWithDecoration(strings.getString("output.bye"));
    }

}
