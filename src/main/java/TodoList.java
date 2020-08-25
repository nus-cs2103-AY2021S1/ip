import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class TodoList {
    private static ArrayList<Task> todoList = new ArrayList<>();
    private static boolean terminate = false;

    public static void initialise() {
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        while (!TodoList.terminate) {
            String command = sc.nextLine();  // Read user input
            TodoList.handleCommand(command); // Output user input
        }
        sc.close();
    }

    private static void handleCommand(String command) {
        String[] splitCommand = command.split(" ", 2);
        try {
            try {
                Command enumCommand = Command.stringToEnum(splitCommand[0]);
                switch (enumCommand) {
                    case LIST: {
                        for (int i = 0; i < todoList.size(); i++) {
                            int index = i + 1;
                            System.out.println(index + ". " + todoList.get(i).toString());
                        }
                        // break is necessary to prevent fall-through
                        break;
                    }
                    case BYE: {
                        TodoList.terminate = true;
                        System.out.println("Bye. Hope to see you again soon!");
                        break;
                    }
                    case DONE: {
                        int index = Integer.parseInt(splitCommand[1]);
                        Task targetTask = todoList.get(index - 1);
                        String message = targetTask.completeTask();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(message);
                        break;
                    }
                    case DEADLINE: {
                        String[] splitDeadline = splitCommand[1].split(" /by ");
                        System.out.println("Got it. I've added this task:");
                        TodoList.addDeadline(splitDeadline[0], splitDeadline[1]);
                        int size = todoList.size();
                        System.out.println("Now you have " + size + " tasks in the list.");
                        break;
                    }
                    case EVENT: {
                        String[] splitEvent = splitCommand[1].split(" /at ");
                        System.out.println("Got it. I've added this task:");
                        TodoList.addEvent(splitEvent[0], splitEvent[1]);
                        int size = todoList.size();
                        System.out.println("Now you have " + size + " tasks in the list.");
                        break;
                    }
                    case TODO: {
                        if (splitCommand.length <= 1) {
                            throw new DukeException("OOPS!!! The description of a todo cannot be empty");
                        } else {
                            System.out.println("Got it. I've added this task:");
                            TodoList.addTodo(splitCommand[1]);
                            int size = todoList.size();
                            System.out.println("Now you have " + size + " tasks in the list.");
                        }
                        break;
                    }
                    case DELETE: {
                        int index = Integer.parseInt(splitCommand[1]);
                        Task targetTask = todoList.get(index - 1);
                        todoList.remove(index - 1);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(targetTask.toString());
                        int size = todoList.size();
                        System.out.println("Now you have " + size + " tasks in the list.");
                        break;
                    }
                    case INVALID: {
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
            } catch (NumberFormatException error) {
                // When "done" is not followed by a valid number
                throw new DukeException("OOPS!!! Please enter a valid index!");
            } catch (ArrayIndexOutOfBoundsException error) {
                // When "done" is not followed by any number
                throw new DukeException("OOPS!!! Please enter a valid index!");
            } catch (IndexOutOfBoundsException error) {
                // When "done is followed by a number that is out of range
                throw new DukeException("OOPS!!! That index is out of range!");
            } catch (DateTimeParseException e) {
                throw new DukeException("OOPS!!! Please enter the date in yyyy-mm-dd format!");
            }
        } catch (DukeException error) {
            System.out.println(error.getMessage());
        }
    }

    private static void addTask(String task) {
        Task newTask = new Task(task);
        todoList.add(newTask);
        System.out.println("added: " + task);
    }

    private static void addTodo(String task) {
        Todo newTodo = new Todo(task);
        todoList.add(newTodo);
    }

    private static void addDeadline(String task, String deadline) {
        Deadline newDeadline = new Deadline(task, deadline);
        todoList.add(newDeadline);
    }

    private static void addEvent(String task, String eventDate) {
        Event newEvent = new Event(task, eventDate);
        todoList.add(newEvent);
    }
}