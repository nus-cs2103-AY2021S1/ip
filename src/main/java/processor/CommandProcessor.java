package processor;

import exception.DukeException;
import exception.EmptyActionException;
import exception.InvalidActionException;
import exception.InvalidCommandException;
import task.DeadlineTask;
import task.EventTask;
import task.Task;
import task.TaskList;
import task.ToDoTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

/**
 * Processes command entered by user.
 *
 * <p>The 'CommandProcessor' supports operators, supported include: </p>
 *
 * <p> (i) processing the String command and run the operation </p>
 */
public class CommandProcessor {
    private TaskList taskList;
    private final HashMap<String, Consumer<String>> map = setUpCommandMap();

    CommandProcessor(TaskList taskList) {
        this.taskList = taskList;
    }

    private HashMap<String, Consumer<String>> setUpCommandMap() {
        HashMap<String, Consumer<String>> map = new HashMap<>();

        map.put("list", (command) -> listCommand());
        map.put("done", (command) -> doneCommand(command));
        map.put("todo", (command) -> toDoCommand(command));
        map.put("deadline", (command) -> deadlineCommand(command));
        map.put("event", (command) -> eventCommand(command));
        map.put("delete",(command) -> deleteCommand(command));
        map.put("find",(command) -> findCommand(command));
        return map;
    }

    /**
     * Run command enter by user.
     * Prints error message if command is invalid.
     * @param command the command entered by user.
     */
    public void runCommand(String command) {
        Consumer<String> action =  map.get(command.replaceAll(" .*", ""));
        try {
            if (action == null) {
                throw new InvalidCommandException();
            } else {
                action.accept(command);
            }
        } catch (DukeException e){
            System.out.println(e);
        }
    }

    private void listCommand() {
        this.taskList.showList();
    }

    private void doneCommand(String command) {
        try {
            int length  = command.length();
            if (length < 5) {
                throw new EmptyActionException(); // only "done"
            } else {
                try {
                    String num = command.substring(5);
                    int index = Integer.parseInt(num);

                    if (index == 0 || index > this.taskList.size()) {
                        throw new InvalidActionException(); // "done 0"
                    }
                    this.taskList.getTask(index - 1).markAsDone();
                    System.out.println("    ____________________________________________________________\n"
                            + "     Nice! I've marked this task as done:\n"
                            + "     "
                            + this.taskList.getTask(index - 1)
                            + "\n"
                            + "    ____________________________________________________________\n"
                    );
                } catch (NumberFormatException e) {
                    throw new InvalidActionException(); // "done 1A" etc
                }
            }
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    private void toDoCommand(String command) {
        try {
            int spaceIndex = command.indexOf(" ");

            if (spaceIndex == -1) {
                throw new EmptyActionException(); // "todo"
            }

            String action = command.substring(spaceIndex + 1);

            if (action.toLowerCase().contains("/by") || action.toLowerCase().contains("/at")) {
                throw new InvalidActionException(); // "todo borrow book /by Sunday" etc
            } else if (action.replaceAll(" ", "").equals("")) {
                throw new EmptyActionException(); // "todo     "
            } else {
                Task task = new ToDoTask(command.substring(spaceIndex + 1));
                this.taskList.addToTaskList(task);
            }
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    private void deadlineCommand(String command) {
        try {
            int spaceIndex = command.indexOf(" ");
            int slashIndex = command.indexOf("/by");

            if (spaceIndex == -1) {
                throw new EmptyActionException(); // "deadline"
            } else if (slashIndex == -1 || spaceIndex + 1 == slashIndex || slashIndex + 4 > command.length()) {
                throw new InvalidActionException(); // "deadline project submission", "deadline /by Sunday", "deadline return book /by"
            } else {
                String description = command.substring(spaceIndex + 1, slashIndex - 1);
                String time = command.substring(slashIndex + 4);

                Task task = new DeadlineTask(description, time);
                this.taskList.addToTaskList(task);
            }

        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    private void eventCommand(String command) {
        try {
            int spaceIndex = command.indexOf(" ");
            int slashIndex = command.indexOf("/at");

            if (spaceIndex == -1) {
                throw new EmptyActionException(); // "event"
            } else if (slashIndex == -1 || spaceIndex + 1 == slashIndex || slashIndex + 4 > command.length()) {
                throw new InvalidActionException(); // "event project submission", "event /at 1-2pm", "deadline meeting /at"
            } else {
                String description = command.substring(spaceIndex + 1, slashIndex - 1);
                String time = command.substring(slashIndex + 4);

                Task task = new EventTask(description, time);
                this.taskList.addToTaskList(task);
            }

        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    private void deleteCommand(String command) {
        try {
            int length  = command.length();
            if (length < 7) {
                throw new EmptyActionException(); // only "delete"
            } else {
                try {
                    String num = command.substring(7);
                    int index = Integer.parseInt(num);

                    if (index == 0 || index > this.taskList.size()) {
                        throw new InvalidActionException(); // "delete 0"
                    }
                    this.taskList.deleteFromTaskList(index - 1);
                } catch (NumberFormatException e) {
                    throw new InvalidActionException(); // "delete 1A" etc
                }
            }
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    private void findCommand(String command) {
        try {
            if (command.length() < 6) { // find
                throw new EmptyActionException();
            }
            String searchWord = command.substring(5);
            if (searchWord.equals(" ") || searchWord.equals("")) {
                throw new EmptyActionException();
            }

            int size = this.taskList.size();
            List<Task> list = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                Task task = taskList.getTask(i);
                String taskInfo = task.toString();
                if (taskInfo.contains(searchWord)) {
                    list.add(task);
                }
            }

            TaskList filterList = new TaskList(list);
            filterList.showList();
        } catch (DukeException e) {
            System.out.println(e);
        }
    }
}
