import Exception.DukeException;
import Exception.EmptyActionException;
import Exception.InvalidActionException;
import Exception.InvalidCommandException;
import Task.DeadlineTask;
import Task.EventTask;
import Task.Task;
import Task.ToDoTask;

import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public class CommandProcessor {
    private List<Task> list;
    private final HashMap<String, Consumer<String>> map = setUpCommandMap();

    CommandProcessor(List<Task> list) {
        this.list = list;
    }

    private HashMap<String, Consumer<String>> setUpCommandMap() {
        HashMap<String, Consumer<String>> map = new HashMap<>();

        map.put("list", (command) -> listCommand());
        map.put("done", (command) -> doneCommand(command));
        map.put("todo", (command) -> toDoCommand(command));
        map.put("deadline", (command) -> deadlineCommand(command));
        map.put("event", (command) -> eventCommand(command));
        map.put("delete",(command) -> deleteCommand(command));
        return map;
    }

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
        System.out.print("    ____________________________________________________________\n");
        System.out.print("     Here are the tasks in your list:\n");
        for (int i = 1; i <= this.list.size(); i++) {
            System.out.println("     " + i + "." + this.list.get(i - 1));
        }
        System.out.println("    ____________________________________________________________\n");
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

                    if (index == 0 || index > this.list.size()) {
                        throw new InvalidActionException(); // "done 0"
                    }
                    this.list.get(index - 1).markAsDone();
                    System.out.println("    ____________________________________________________________\n"
                            + "     Nice! I've marked this task as done:\n"
                            + "     "
                            + this.list.get(index - 1)
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
                this.list.add(task);

                System.out.println("    ____________________________________________________________\n"
                        + "     Got it. I've added this task:\n"
                        + "     "
                        + task
                        + "\n"
                        + "     Now you have "
                        + this.list.size()
                        + " task(s) in the list.\n"
                        + "    ____________________________________________________________\n"
                );
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
                this.list.add(task);

                System.out.println("    ____________________________________________________________\n"
                        + "     Got it. I've added this task:\n"
                        + "     "
                        + task
                        + "\n"
                        + "     Now you have "
                        + this.list.size()
                        + " task(s) in the list.\n"
                        + "    ____________________________________________________________\n"
                );
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
                this.list.add(task);

                System.out.println("    ____________________________________________________________\n"
                        + "     Got it. I've added this task:\n"
                        + "     "
                        + task
                        + "\n"
                        + "     Now you have "
                        + this.list.size()
                        + " task(s) in the list.\n"
                        + "    ____________________________________________________________\n"
                );
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

                    if (index == 0 || index > this.list.size()) {
                        throw new InvalidActionException(); // "delete 0"
                    }
                    Task task = this.list.get(index - 1);
                    this.list.remove(index - 1);
                    System.out.println("    ____________________________________________________________\n"
                            + "     Noted. I've removed this task:\n"
                            + "     "
                            + task
                            + "\n"
                            + "     Now you have "
                            + this.list.size()
                            + " task(s) in the list.\n"
                            + "    ____________________________________________________________\n"
                    );
                } catch (NumberFormatException e) {
                    throw new InvalidActionException(); // "delete 1A" etc
                }
            }
        } catch (DukeException e) {
            System.out.println(e);
        }
    }
}
