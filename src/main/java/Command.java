import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.function.Function;

public class Command {

    private HashMap<String, Cmd> commands = new HashMap<>();
    private TaskList taskList;
    private Storage storage;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    class Cmd {
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
     * Basically all the commands are here lol
     */
    public Command(Storage storage) {
        System.out.println("Initialized commands");
        this.taskList = new TaskList(storage.loadFile());
        this.storage = storage;

        //init all the duke commands

        //add to-do
        Function<String, String> addTodo = (input) -> taskList.add(new ToDo(input));
        commands.put("todo", new Cmd(addTodo, "todo_(task description)"));

        //add deadline
        Function<String, String> addDeadline = (input) -> {
            String[] arr = input.split("/");
            LocalDateTime ldt = LocalDateTime.parse(arr[1], formatter);
            return taskList.add(new Deadline(arr[0], ldt));
        };
        commands.put("deadline", new Cmd(addDeadline, "deadline_(task description)/(dd/MM/yyyy HH:mm)"));

        //add event
        Function<String, String> addEvent = (input) -> {
            String[] arr = input.split("/");
            LocalDateTime ldt = LocalDateTime.parse(arr[1], formatter);
            return taskList.add(new Event(arr[0], ldt));
        };
        commands.put("event", new Cmd(addEvent, "event_(event description)/(dd/MM/yyyy HH:mm)"));

        //done
        commands.put("done", new Cmd(taskList::done, "done_(indexOfTask)"));

        //delete
        commands.put("delete", new Cmd(taskList::delete, "delete_(indexOfTask)"));

        //find
        commands.put("find", new Cmd(taskList::find, "find_(task description)"));

        //list
        commands.put("list", new Cmd(taskList::displayList, "list"));
    }

    /**
     *
     * @param input
     * @return
     */
    public String parseToCommand(String input) {
        //for single-word commands e.g list
        input += " ";
        String[] arr = input.split(" ", 2);
        String cmd = arr[0];
        System.out.println("cmd is " + cmd);
        if (commands.containsKey(cmd)) {
            Cmd fn = commands.get(cmd);
            String output = fn.runCommand(arr[1].trim());
            storage.saveFile(taskList);
            return output;
        }
        return "Sorry, Poco did not understand";
    }
}
