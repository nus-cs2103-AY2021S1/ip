import java.util.Scanner;
import java.util.ArrayList;

enum actionType {
    QUIT,
    LIST,
    MARK_DONE,
    DELETE,
    ADD_TODO,
    ADD_DEADLINE,
    ADD_EVENT,
    WRONG_INPUT
}

public class Duke {
    private static actionType getAction(String input) {
        return input.equalsIgnoreCase("bye")
                ? actionType.QUIT
                : input.equalsIgnoreCase("list")
                ? actionType.LIST
                : input.length() >= 4 && input.substring(0, 4).equalsIgnoreCase("done")
                ? actionType.MARK_DONE
                : input.length() >= 6 && input.substring(0, 6).equalsIgnoreCase("delete")
                ? actionType.DELETE
                : input.length() >= 4 && input.substring(0, 4).equalsIgnoreCase("todo")
                ? actionType.ADD_TODO
                : input.length() >= 5 && input.substring(0, 5).equalsIgnoreCase("event")
                ? actionType.ADD_EVENT
                : input.length() >= 8 && input.substring(0, 8).equalsIgnoreCase("deadline")
                ? actionType.ADD_DEADLINE
                : actionType.WRONG_INPUT;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        System.out.println("Hello, I'm Duke");
        System.out.println("I can help you keep track of all your tasks! ☆*:.｡.o(≧▽≦)o.｡.:*☆");
        System.out.println("How to add tasks to the list:");
        System.out.println("ToDo - type 'todo' followed by the description");
        System.out.println("Deadline - type 'deadline' followed by the description," +
                "then '/by', then due date");
        System.out.println("Event - type 'event' followed by the description, " +
                "then '/at', then timing");
        System.out.println("Type 'done' followed by the task number " +
                "and I'll mark it as done");
        System.out.println("Type 'list' to see the list");
        System.out.println("Type 'bye' to exit");
        String next;
        try {
            while (true) {
                next = sc.nextLine();
                actionType action = getAction(next);
                switch(action) {
                case QUIT:
                    System.out.println("Bye! :>");
                    sc.close();
                    System.exit(0);
                    break;
                case LIST:
                    if (list.size() == 0) {
                        System.out.println("List is empty");
                    } else {
                        System.out.println("Items in list:");
                        for (int i = 0; i < list.size(); i++) {
                            System.out.println((i + 1) + ". " + list.get(i).toString());
                        }
                    }
                    break;
                case MARK_DONE:
                    int taskNo = Integer.parseInt(next.substring(5));
                    if (taskNo > list.size()) {
                        throw new DukeException("Task does not exist _(´ཀ`」 ∠)_");
                    } else {
                        Task completedTask = list.get(taskNo - 1);
                        completedTask.markAsDone();
                        System.out.println("Task marked complete:");
                        System.out.println(completedTask.toString());
                    }
                    break;
                case DELETE:
                    int deleteNo = Integer.parseInt(next.substring(7));
                    if (deleteNo > list.size()) {
                        throw new DukeException("Task does not exist _(´ཀ`」 ∠)_");
                    } else {
                        Task deletedTask = list.remove(deleteNo - 1);
                        System.out.println("Task deleted:");
                        System.out.println(deletedTask.toString());
                    }
                    break;
                case ADD_TODO:
                    if (next.length() < 6) {
                        throw new DukeException("Task cannot be empty _(´ཀ`」 ∠)_");
                    } else {
                        Task newTodo = new ToDo(next.substring(5));
                        list.add(newTodo);
                        System.out.println("Added: " + newTodo.toString());
                        System.out.println("Total tasks: " + list.size());
                    }
                    break;
                case ADD_EVENT:
                    if (next.length() < 7) {
                        throw new DukeException("Event cannot be empty _(´ཀ`」 ∠)_");
                    } else {
                        Task newEvent = new Event(next.substring(6));
                        list.add(newEvent);
                        System.out.println("Added: " + newEvent.toString());
                        System.out.println("Total tasks: " + list.size());
                    }
                    break;
                case ADD_DEADLINE:
                    if (next.length() < 10) {
                        throw new DukeException("Deadline cannot be empty _(´ཀ`」 ∠)_");
                    } else {
                        Task newDeadline = new Deadline(next.substring(9));
                        list.add(newDeadline);
                        System.out.println("Added: " + newDeadline.toString());
                        System.out.println("Total tasks: " + list.size());
                    }
                    break;
                case WRONG_INPUT:
                    throw new DukeException("I have no idea what that means ¯\\_(ツ)_/¯");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
