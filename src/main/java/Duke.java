import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> todoList = new ArrayList<Task>();

        String greetText = "Hey There! What can I do for you?";
        say(greetText);

        String input = scanner.nextLine();
        TaskHandler handler = new TaskHandler();

        while (!handler.isBye(input)) {
            if (handler.isList(input)) {
                printList(todoList);
            } else if (handler.isDone(input)) {
                markAsDone(input, handler, todoList);
            } else if (handler.isValid(input)) {
                Task.Type type = handler.getType(input);
                boolean valid = false;
                switch (type) {
                    case TODO:
                        valid = true;
                        say("Added to-do '" + handler.getTodo(input) + "' to the task list.");
                        Todo todo = new Todo(handler.getTodo(input));
                        todoList.add(todo);
                        break;
                    case DEADLINE:
                        valid = handler.checkDeadline(input);
                        if (valid) {
                            say("Added deadline '" + handler.getDTask(input) + "' to the task list.");
                            Deadline deadline = new Deadline(handler.getDTask(input), handler.getDTime(input));
                            todoList.add(deadline);
                        }
                        break;
                    case EVENT:
                        valid = handler.checkEvent(input);
                        if (valid) {
                            say("Added event '" + handler.getETask(input) + "' to the task list.");
                            Event event = new Event(handler.getETask(input), handler.getETime(input));
                            todoList.add(event);
                        }
                        break;
                    default:
                }
                if (!valid) {
                    say("Invalid deadline/event input. Please follow the format!");
                }
            } else {
                say("Please key in something valid!");
            }
            input = scanner.nextLine();
        }

        String byeText = "Bye! Hope to see you again.";
        say(byeText);
    }

    public static void say(String text) {
        System.out.println("Duke: " + text);
    }

    private static void printList(ArrayList<Task> list) {
        int count = 1;
        for (Task task : list) {
            System.out.println(count + ". " + task);
            count++;
        }
    }

    private static void markAsDone(String input, TaskHandler handler, ArrayList<Task> list) {
        int number = handler.getNumber(input);
        Task task = list.get(number - 1);
        task.markDone();
        say("I have marked it as done!");
        System.out.println(task);
    }
}
