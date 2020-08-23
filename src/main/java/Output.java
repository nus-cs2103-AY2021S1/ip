import exceptions.InvalidCommandException;
import exceptions.InvalidDescriptionException;
import exceptions.InvalidTaskIndexException;
import exceptions.MissingTimingException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.util.ArrayList;

public class Output {
    void response(String s, ArrayList<Task> taskList) throws InvalidCommandException {
        if (s.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        } else if (s.isEmpty()) {
            System.out.println("Please enter a command");
        } else if (s.equals("list")) {
            System.out.println("Here are the tasks in your list:");
            taskList.forEach(task -> System.out.print(task));
        } else if (s.length() >= 4 && s.startsWith("done")) {
            try {
                doneResponse(taskList, s);
            } catch (InvalidTaskIndexException e) {
                System.out.println(e.getMessage());
            }
        } else if (s.length() >= 4 && s.startsWith("todo")) {
            todoResponse(taskList, s);
        } else if (s.length() >= 8 && s.startsWith("deadline")) {
            deadlineResponse(taskList, s);
        } else if (s.length() >= 5 && s.startsWith("event")) {
            eventResponse(taskList, s);
        } else if (s.length() >= 6 && s.startsWith("delete")) {
            try {
                deleteTask(taskList, s);
            } catch (InvalidTaskIndexException e) {
                System.out.println(e.getMessage());
            }
        }
        else {
            throw new InvalidCommandException("Please enter a valid command for me!");
        }
    }

    public void doneResponse(ArrayList<Task> taskList, String command) throws InvalidTaskIndexException {
        int numTodos = taskList.size();
        if (command.length() <= 5) throw new InvalidTaskIndexException("Please include the index of the task!");
        try {
            int todoIndex = Integer.parseInt(command.substring(5, 6));
            if (todoIndex <= numTodos && todoIndex > 0) {
                Task selectedTodo = taskList.get(todoIndex - 1);
                selectedTodo.setDone();
                System.out.println("Nice! I've marked this task as done:\n " + selectedTodo);
            } else {
                throw new InvalidTaskIndexException("Oh no! Task number does not exist in task list.");
            }
        } catch (NumberFormatException e) {
            System.out.print("Please enter the index of the task you want to remove!");
        }
    }

    public void todoResponse(ArrayList<Task> taskList, String command) {
        try {
            Todo todo = new Todo(command.substring(4));
            taskList.add(todo);
            System.out.println("Got it. I've added this task:\n " + todo + "\nNow you have "
                    + taskList.size() + " in the list");
        } catch (InvalidDescriptionException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deadlineResponse(ArrayList<Task> taskList, String command) {
        try {
            Deadline deadline = new Deadline(command.substring(8));
            taskList.add(deadline);
            System.out.println("Got it. I've added this task:\n" +
                    deadline + "\nNow you have " + taskList.size() + " in the list");
        } catch (MissingTimingException | InvalidDescriptionException e) {
            System.out.println(e.getMessage());
        }
    }


    public void eventResponse(ArrayList<Task> taskList, String command) {
        try {
            Event event = new Event(command.substring(5));
            taskList.add(event);
            System.out.println("Got it. I've added this task:\n" +
                    event + "\nNow you have " + taskList.size() + " in the list");
        } catch (MissingTimingException | InvalidDescriptionException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTask(ArrayList<Task> taskList, String command) throws InvalidTaskIndexException {
        int numTodos = taskList.size();
        if (command.length() <= 7 ) throw new InvalidTaskIndexException("Please include the index of the task!");
        try {
            int todoIndex = Integer.parseInt(command.substring(7, 8));
            if (todoIndex <= numTodos && todoIndex > 0) {
                Task selectedTodo = taskList.remove(todoIndex - 1);
                System.out.println("Noted. I've removed this task:\n " + selectedTodo
                        + "\nNow you have " + taskList.size() + " in the list.");
            } else {
                throw new InvalidTaskIndexException("Oh no! Task number does not exist in task list.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter the index of the task you want to remove!");
        }

    }
}
