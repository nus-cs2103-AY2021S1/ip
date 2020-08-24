import exceptions.InvalidCommandException;
import exceptions.InvalidDescriptionException;
import exceptions.InvalidTaskIndexException;
import exceptions.MissingTimingException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Output {
    void response(String s, ArrayList<Task> taskList) throws InvalidCommandException {
        if (s.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        } else if (s.isEmpty()) {
            System.out.println("Please enter a command");
        } else if (s.equals("list")) {
            listResponse(taskList);
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
        } else {
            throw new InvalidCommandException("Please enter a valid command for me!");
        }
    }

    public void listResponse(ArrayList<Task> taskList) {
        System.out.println("Here are the tasks in your list");
        for (int i = 0; i < taskList.size(); i++) {
            String output = (i + 1) + "." + taskList.get(i);
            System.out.println(output);
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
            String detail = formatTimingInput("/by", command.substring(8))[0];
            String timing = formatTimingInput("/by", command.substring(8))[1].trim();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(timing, formatter);

            Deadline deadline = new Deadline(detail, dateTime);
            taskList.add(deadline);

            System.out.println("Got it. I've added this task:\n" +
                    deadline + "\nNow you have " + taskList.size() + " in the list");
        } catch (MissingTimingException | InvalidDescriptionException e) {
            System.out.println(e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("Please enter timing in '/by 12-30-2020 23:59' format");
        }
    }


    public void eventResponse(ArrayList<Task> taskList, String command) {
        try {
            String detail = formatTimingInput("/at", command.substring(5))[0];
            String timing = formatTimingInput("/at", command.substring(5))[1].trim();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(timing, formatter);

            Event event = new Event(detail, dateTime);
            taskList.add(event);

            System.out.println("Got it. I've added this task:\n" +
                    event + "\nNow you have " + taskList.size() + " in the list");
        } catch (MissingTimingException | InvalidDescriptionException e) {
            System.out.println(e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("Please enter timing in '/by 12-30-2020 23:59' format");
        }
    }

    public void deleteTask(ArrayList<Task> taskList, String command) throws InvalidTaskIndexException {
        int numTodos = taskList.size();
        if (command.length() <= 7) throw new InvalidTaskIndexException("Please include the index of the task!");
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

    public String[] formatTimingInput(String format, String input) throws MissingTimingException {
        if (!input.contains(format)) {
            String message = "Don't forget to add a timing in '"
                    + format + " 12-12-2020 23:59' format";
            throw new MissingTimingException(message);
        }
        String[] parts = input.trim().split(format);
        return parts;
    }
}
