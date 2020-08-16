import exceptions.InvalidCommandException;
import exceptions.InvalidDescriptionException;
import exceptions.InvalidDoneIndexException;
import exceptions.MissingTimingException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

public class Output {
    void response(String s, TaskList taskList) throws InvalidCommandException {
        if (s.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        } else if (s.isEmpty()) {
            System.out.println("Please enter a command");
        } else if (s.equals("list")) {
            System.out.println("Here are the tasks in your list:");
            taskList.listAllTasks();
        } else if (s.length() >= 4 && s.startsWith("done")) {
            try {
                doneResponse(taskList, s);
            } catch (InvalidDoneIndexException e) {
                System.out.println(e.getMessage());
            }
        } else if (s.length() >= 4 && s.startsWith("todo")) {
            todoResponse(taskList, s);
        } else if (s.length() >= 8 && s.startsWith("deadline")) {
            deadlineResponse(taskList, s);
        } else if (s.length() >= 5 && s.startsWith("event")) {
            eventResponse(taskList, s);
        } else {
            throw new InvalidCommandException("Please enter a valid command for me!");
        }
    }

    public void doneResponse(TaskList taskList, String command) throws InvalidDoneIndexException {
        int numTodos = taskList.list.size();
        int todoIndex = Integer.parseInt(command.substring(5, 6));
        if (todoIndex <= numTodos && todoIndex > 0) {
            Task selectedTodo = taskList.getTask(todoIndex);
            selectedTodo.setDone();
            System.out.println("Nice! I've marked this task as done:\n " + selectedTodo);
        } else {
            throw new InvalidDoneIndexException("Oh no! Task number does not exist in task list.");
        }
    }

    public void todoResponse(TaskList taskList, String command) {
        try {
            Todo todo = new Todo(command.substring(4));
            taskList.add(todo);
            System.out.println("Got it. I've added this task:\n " + todo + "\nNow you have "
                    + taskList.list.size() + " in the list");
        } catch (InvalidDescriptionException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deadlineResponse(TaskList taskList, String command) {
        try {
            Deadline deadline = new Deadline(command.substring(8));
            taskList.add(deadline);
            System.out.println("Got it. I've added this task:\n" +
                    deadline + "\nNow you have " + taskList.list.size() + " in the list");
        } catch (MissingTimingException | InvalidDescriptionException e) {
            System.out.println(e.getMessage());
        }
    }


    public void eventResponse(TaskList taskList, String command) {
        try {
            Event event = new Event(command.substring(5));
            taskList.add(event);
            System.out.println("Got it. I've added this task:\n" +
                    event + "\nNow you have " + taskList.list.size() + " in the list");
        } catch (MissingTimingException | InvalidDescriptionException e) {
            System.out.println(e.getMessage());
        }
    }
}
