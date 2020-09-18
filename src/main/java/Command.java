import java.util.HashMap;
import java.util.function.Function;

/**
 * in charge of parsing user input into
 * the corresponding command and
 * returning the correct output
 */
public class Command {

    private HashMap<String, Cmd> commands = new HashMap<>();
    private TaskList taskList;
    private Storage storage;

    private String listOfCommands = " todo \n deadline \n event \n done \n delete \n find"
                                        + " \n list \n postpone \n format \n bye";

    static class Cmd {
        private Function<String, String> method;
        private String format;

        public Cmd(Function<String, String> method, String format) {
            this.method = method;
            this.format = format;
        }

        public String runCommand(String input) {
            return method.apply(input);
        }

        public String displayFormat() {
            return format;
        }
    }

    /**
     * constructor, initializes references to commands
     */
    public Command(Storage storage) {
        this.taskList = new TaskList(storage.loadFile());
        this.storage = storage;

        //init all the duke commands
        commands.put("todo", new Cmd((input) -> taskList.add(input, TaskType.TODO),
                "todo (task description)"));
        commands.put("deadline", new Cmd((input) -> taskList.add(input, TaskType.DEADLINE),
                "deadline (task description)/(dd/MM/yyyy HH:mm)"));
        commands.put("event", new Cmd((input) -> taskList.add(input, TaskType.EVENT),
                "event (event description)/(dd/MM/yyyy HH:mm)"));
        commands.put("done", new Cmd(taskList::done, "done (indexOfTask)"));
        commands.put("delete", new Cmd(taskList::delete, "delete (indexOfTask)"));
        commands.put("find", new Cmd(taskList::find, "find (task description)"));
        commands.put("list", new Cmd(taskList::displayList, "list"));
        commands.put("postpone", new Cmd(taskList::postpone, "postpone (indexOfTask)/(dd/MM/yyyy HH:mm)"));
        commands.put("bye", new Cmd((input) -> "See you again!", "bye"));
        commands.put("help", new Cmd((input) -> listOfCommands, "help"));
        commands.put("format", new Cmd(this::displayFormat, "format (cmd name)"));
    }

    /**
     * parses to command
     * and runs command if found
     * @param input user input
     * @return output
     */
    public String parseToCommand(String input) {
        //for single-word commands e.g list
        input += " ";
        String[] arr = input.split(" ", 2);
        String cmd = arr[0];
        if (commands.containsKey(cmd)) {
            Cmd fn = commands.get(cmd);
            String output = fn.runCommand(arr[1].trim());
            //saves after each command
            storage.saveFile(taskList);
            return output;
        }
        return "Sorry, Poco did not understand";
    }

    /**
     * displays format for the given cmd
     * @param cmd cmd to check format
     * @return format
     */
    public String displayFormat(String cmd) {
        if (commands.containsKey(cmd)) {
            Cmd fn = commands.get(cmd);
            return fn.displayFormat();
        }
        return "Sorry, Poco did not understand";
    }
}
