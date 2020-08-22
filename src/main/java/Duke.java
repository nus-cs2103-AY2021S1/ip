import Exception.DukeException;
import Exception.EmptyActionException;
import Exception.InvalidActionException;
import Exception.InvalidCommandException;
import Task.DeadlineTask;
import Task.EventTask;
import Task.Task;
import Task.ToDoTask;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiConsumer;

public class Duke {
    public static void main(String[] args) {
        introduction();
        Scanner sc  = new Scanner(System.in);
        interact(sc);
        sc.close();
    }

    public static void introduction() {
        String greeting = "    ____________________________________________________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________\n";
        System.out.println(greeting);
    }

    public static HashMap<String, BiConsumer<String, List<Task>>> setUp() {
        HashMap<String, BiConsumer<String, List<Task>>> map = new HashMap<>();

        map.put("list", (command, list) -> listCommand(list));
        map.put("done", (command, list) -> doneCommand(list, command));
        map.put("todo", (command, list) -> toDoCommand(list, command));
        map.put("deadline", (command, list) -> deadlineCommand(list, command));
        map.put("event", (command, list) -> eventCommand(list, command));
        map.put("delete",(command, list) -> deleteCommand(list, command));
        return map;
    }

    public static void interact(Scanner sc) {
        HashMap<String, BiConsumer<String, List<Task>>> map = setUp();
        List<Task> list = FileToTaskListConverter.convert(new File("C:/Users/Jaylen/Downloads/CS2103 IP/data/Duke.txt"));
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            BiConsumer<String, List<Task>> action =  map.get(command.replaceAll(" .*", ""));

            try {
                if (action == null) {
                    throw new InvalidCommandException();
                } else {
                    action.accept(command, list);
                }
            } catch (DukeException e){
                System.out.println(e);
            }
            command = sc.nextLine();
        }

        System.out.println("    ____________________________________________________________\n"
                + "     Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________\n"
        );
    }

    public static void addCommand(List<Task> list, String command) {
        list.add(new Task(command));
        System.out.println("    ____________________________________________________________\n"
                + "     added: "
                + command
                + "\n"
                + "    ____________________________________________________________\n"
        );
    }

    public static void listCommand(List<Task> list) {
        System.out.print("    ____________________________________________________________\n");
        System.out.print("     Here are the tasks in your list:\n");
        for (int i = 1; i <= list.size(); i++) {
            System.out.println("     " + i + "." + list.get(i - 1));
        }
        System.out.println("    ____________________________________________________________\n");
    }

    public static void doneCommand(List<Task> list, String command) {
        try {
            int length  = command.length();
            if (length < 5) {
                throw new EmptyActionException(); // only "done"
            } else {
                try {
                    String num = command.substring(5);
                    int index = Integer.parseInt(num);

                    if (index == 0 || index > list.size()) {
                        throw new InvalidActionException(); // "done 0"
                    }
                    list.get(index - 1).markAsDone();
                    System.out.println("    ____________________________________________________________\n"
                            + "     Nice! I've marked this task as done:\n"
                            + "     "
                            + list.get(index - 1)
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

    public static void toDoCommand(List<Task> list, String command) {
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
                list.add(task);

                System.out.println("    ____________________________________________________________\n"
                        + "     Got it. I've added this task:\n"
                        + "     "
                        + task
                        + "\n"
                        + "     Now you have "
                        + list.size()
                        + " task(s) in the list.\n"
                        + "    ____________________________________________________________\n"
                );
            }
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    public static void deadlineCommand(List<Task> list, String command) {
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
                list.add(task);

                System.out.println("    ____________________________________________________________\n"
                        + "     Got it. I've added this task:\n"
                        + "     "
                        + task
                        + "\n"
                        + "     Now you have "
                        + list.size()
                        + " task(s) in the list.\n"
                        + "    ____________________________________________________________\n"
                );
            }

        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    public static void eventCommand(List<Task> list, String command) {
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
                list.add(task);

                System.out.println("    ____________________________________________________________\n"
                        + "     Got it. I've added this task:\n"
                        + "     "
                        + task
                        + "\n"
                        + "     Now you have "
                        + list.size()
                        + " task(s) in the list.\n"
                        + "    ____________________________________________________________\n"
                );
            }

        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    public static void deleteCommand(List<Task> list, String command) {
        try {
            int length  = command.length();
            if (length < 7) {
                throw new EmptyActionException(); // only "delete"
            } else {
                try {
                    String num = command.substring(7);
                    int index = Integer.parseInt(num);

                    if (index == 0 || index > list.size()) {
                        throw new InvalidActionException(); // "delete 0"
                    }
                    Task task = list.get(index - 1);
                    list.remove(index - 1);
                    System.out.println("    ____________________________________________________________\n"
                            + "     Noted. I've removed this task:\n"
                            + "     "
                            + task
                            + "\n"
                            + "     Now you have "
                            + list.size()
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
