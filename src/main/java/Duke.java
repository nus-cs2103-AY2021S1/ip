import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws DukeException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();
        TaskHandler handler = new TaskHandler();

        String greetText = "Hey There! What can I do for you?";
        say(greetText);
        String input = scanner.nextLine();

        while (!handler.isBye(input)) {
            if (handler.isList(input)) {
                printList(taskList);
            } else if (handler.isDone(input) && taskList.size() > 0) {
                if (handler.isValidSize(input, taskList)) markAsDone(input, handler, taskList);
                else {
                    say("Please specify a correct task number!");
                    throw new DukeException("Invalid task number");
                }
            } else if (handler.isDelete(input)) {
                if (handler.isValidSize(input, taskList)) deleteTask(input, handler, taskList);
                else {
                    say("Please specify a correct task number!");
                    throw new DukeException("Invalid task number");
                }
            } else if (handler.isValid(input)) {
                Task.Type type = handler.getType(input);
                boolean valid = false;
                switch (type) {
                case TODO:
                    valid = handler.checkTodo(input);
                    if (valid) addToList(Task.Type.TODO, input, handler, taskList);
                    break;
                case DEADLINE:
                    valid = handler.checkDeadline(input);
                    if (valid) addToList(Task.Type.DEADLINE, input, handler, taskList);
                    break;
                case EVENT:
                    valid = handler.checkEvent(input);
                    if (valid) addToList(Task.Type.EVENT, input, handler, taskList);
                    break;
                case UNKNOWN: // Fallthrough
                default: // Fallthrough
                }
                if (!valid) {
                    say("Task found but please follow the format!");
                    throw new DukeException("Wrong task format");
                }
            } else {
                say("Please key in a task!");
                throw new DukeException("Not a task");
            }
            input = scanner.nextLine();
        }

        String byeText = "Bye! Hope to see you again.";
        say(byeText);
    }



    // === helper methods ===
    private static void say(String text) {
        System.out.println("Duke: " + text);
    }

    private static void printList(ArrayList<Task> list) {
        if (list.size() == 0) say("Your task list is currently empty.");
        else {
            say("Here is your task list.");
            int count = 1;
            for (Task task : list) {
                System.out.println(count + ". " + task);
                count++;
            }
        }
    }

    private static void markAsDone(String input, TaskHandler handler, ArrayList<Task> list) {
        int number = handler.getNumber(input);
        Task task = list.get(number - 1);
        task.markDone();
        say("I have marked it as done!");
        System.out.println(task);
    }

    private static void deleteTask(String input, TaskHandler handler, ArrayList<Task> list) {
        int number = handler.getNumber(input);
        Task task = list.get(number - 1);
        list.remove(number - 1);
        say("I have deleted this task!");
        System.out.println(task);
        say("You have " + list.size() + " items in your task list now.");
    }

    private static void addToList(Task.Type type, String input, TaskHandler handler, ArrayList<Task> list) {
        switch (type) {
        case TODO:
            say("Added to-do '" + handler.getTodo(input) + "' to the task list.");
            Todo todo = new Todo(handler.getTodo(input));
            list.add(todo);
            break;
        case DEADLINE:
            say("Added deadline '" + handler.getDTask(input) + "' to the task list.");
            Deadline deadline = new Deadline(handler.getDTask(input), handler.getDTime(input));
            list.add(deadline);
            break;
        case EVENT:
            say("Added event '" + handler.getETask(input) + "' to the task list.");
            Event event = new Event(handler.getETask(input), handler.getETime(input));
            list.add(event);
            break;
        default: //
        }
        say("You have " + list.size() + " items in your task list now.");
    }
}