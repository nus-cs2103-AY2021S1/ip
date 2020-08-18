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
                switch (splitCommand[0]) {
                    case "list": {
                        for (int i = 0; i < todoList.size(); i++) {
                            int index = i + 1;
                            System.out.println(index + ". " + todoList.get(i).toString());
                        }
                        // break is necessary to prevent fall-through
                        break;
                    }
                    case "bye": {
                        TodoList.terminate = true;
                        System.out.println("Bye. Hope to see you again soon!");
                        break;
                    }
                    case "done": {
                        int index = Integer.parseInt(splitCommand[1]);
                        Task targetTask = todoList.get(index - 1);
                        String message = targetTask.completeTask();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(message);
                        break;
                    }
                    case "deadline": {
                        String[] splitDeadline = splitCommand[1].split(" /by ");
                        System.out.println("Got it. I've added this task:");
                        TodoList.addDeadline(splitDeadline[0], splitDeadline[1]);
                        int size = todoList.size();
                        System.out.println("Now you have " + size + " tasks in the list.");
                        break;
                    }
                    case "event": {
                        String[] splitEvent = splitCommand[1].split(" /at ");
                        System.out.println("Got it. I've added this task:");
                        TodoList.addEvent(splitEvent[0], splitEvent[1]);
                        int size = todoList.size();
                        System.out.println("Now you have " + size + " tasks in the list.");
                        break;
                    }
                    case "todo": {
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
                    case "delete": {
                        int index = Integer.parseInt(splitCommand[1]);
                        Task targetTask = todoList.get(index - 1);
                        todoList.remove(index - 1);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(targetTask.toString());
                        int size = todoList.size();
                        System.out.println("Now you have " + size + " tasks in the list.");
                        break;
                    }
                    default: {
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
//                } else if (!splitCommand[0].equals("done") && !splitCommand[0].equals("todo") &&
//                        !splitCommand[0].equals("deadline") && !splitCommand[0].equals("event")) {
//                    System.out.println(splitCommand[0]);
//                    TodoList.addTask(command);
//                } else {
// try {
//                        if (splitCommand[0].equals("done")) {
//                            int index = Integer.parseInt(splitCommand[1]);
//                            Task targetTask = todoList.get(index - 1);
//                            String message = targetTask.completeTask();
//                            System.out.println("Nice! I've marked this task as done:");
//                            System.out.println(message);
//                        } else if (splitCommand[0].equals("deadline")) {
//                            String[] splitDeadline = splitCommand[1].split(" /by ");
//                            System.out.println("Got it. I've added this task:");
//                            TodoList.addDeadline(splitDeadline[0], splitDeadline[1]);
//                            int size = todoList.size();
//                            System.out.println("Now you have " + size + " tasks in the list.");
//                        } else if (splitCommand[0].equals("todo")) {
//                            System.out.println("Got it. I've added this task:");
//                            TodoList.addTodo(splitCommand[1]);
//                            int size = todoList.size();
//                            System.out.println("Now you have " + size + " tasks in the list.");
//                        } else if (splitCommand[0].equals("event")) {
//                            String[] splitEvent = splitCommand[1].split(" /at ");
//                            System.out.println("Got it. I've added this task:");
//                            TodoList.addEvent(splitEvent[0], splitEvent[1]);
//                            int size = todoList.size();
//                            System.out.println("Now you have " + size + " tasks in the list.");
//                        }
//                    } catch (NumberFormatException error) {
//                        // When "done" is not followed by a valid number
//                        System.out.println("Please enter a valid index!");
//                    } catch (ArrayIndexOutOfBoundsException error) {
//                        // When "done" is not followed by any number
//                        System.out.println("Please let me know which task you are referring to!");
//                    }
//                }