import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Luke {
    public static void main(String[] args) {
        File tlFile = new File("./data/", "luke.txt");
        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList();
        taskList.readTasks(tlFile);
        System.out.printf("Luke:\n\tHey there! I'm Luke.\n\tPlease tell me what to add to your list.\nYou:\n");
        while (true) {
            String input = sc.nextLine().toLowerCase();
            if (Pattern.matches("^(todo) *.*$", input)) {
                try {
                    Todo newTodo = createTodo(input);
                    taskList.writeTasks(tlFile, newTodo);
                } catch (EmptyTodoException e) {
                    System.out.printf("Luke:%s\nYou:\n", e.getMessage());
                }
            } else if (Pattern.matches("^(deadline) *.*$", input)){
                try {
                    Deadline newDeadline = createDeadline(input);
                    taskList.writeTasks(tlFile, newDeadline);
                } catch (EmptyDeadlineException e) {
                    System.out.printf("Luke:%s\nYou:\n", e.getMessage());
                } catch (InvalidDeadlineException e) {
                    System.out.printf("Luke:%s\nYou:\n", e.getMessage());
                }
            } else if (Pattern.matches("^(event) *.*$", input)) {
                try {
                    Event newEvent = createEvent(input);
                    taskList.writeTasks(tlFile, newEvent);
                } catch (EmptyEventException e) {
                    System.out.printf("Luke:%s\nYou:\n", e.getMessage());
                } catch (InvalidEventException e) {
                    System.out.printf("Luke:%s\nYou:\n", e.getMessage());
                }
            } else if (input.equals("list")) {
                if (taskList.getSize() < 1) {
                    System.out.printf("Luke:\n\tYou don't have any tasks in your list :(\nYou:\n");
                } else {
                    String todoSummary = "Luke:\n\tHere are the tasks in your list.";
                    for (int i = 0; i < taskList.getSize(); i++) {
                        Task current = taskList.getTask(i);
                        todoSummary += String.format("\n\t%d.%s", i + 1, current);
                    }
                    System.out.printf("%s\nYou:\n", todoSummary);
                }
            } else if (Pattern.matches("^(done) *[0-9]*$", input)) {
               try {
                   taskList.completeTask(input);
               } catch (InvalidDoneException e) {
                   System.out.printf("Luke:%s\nYou:\n", e.getMessage());
               } catch (DoneIndexOutofboundsException e) {
                   System.out.printf("Luke:%s\nYou:\n", e.getMessage());
               }
            } else if (Pattern.matches("^(delete) *[0-9]*$", input)) {
                try {
                    taskList.deleteTask(input, tlFile);
                } catch (InvalidDeleteException e) {
                    System.out.printf("Luke:%s\nYou:\n", e.getMessage());
                } catch (DeleteIndexOutofboundsException e) {
                    System.out.printf("Luke:%s\nYou:\n", e.getMessage());
                }
            } else if (input.equals("bye")) {
                System.out.println("Luke:\n\tOh, are you leaving? Hope to see you soon!");
                break;
            } else {
                System.out.printf("Luke:\n\tSorry I do not understand :( Please try another command.\nYou:\n");
            }
        }
    }

    private static Todo createTodo(String input) throws EmptyTodoException {
        String todo = input.replaceAll("todo ", "");
        if (input.equals("todo") || input.equals("todo ")) {
            throw new EmptyTodoException("\n\tThe description of todo cannot be empty.\n\tPlease make sure you follow the correct format.");
        } else {
            return new Todo(todo);
        }
    }

    private static Deadline createDeadline(String input) throws EmptyDeadlineException, InvalidDeadlineException {
        String[] deadline = input.split("deadline | /by ");
        if (input.equals("deadline") || input.equals("deadline ")) {
            throw new EmptyDeadlineException("\n\tThe description of deadline cannot be empty.\n\tPlease make sure you follow the correct format.");
        } else if (deadline.length != 3) {
            throw new InvalidDeadlineException("\n\tYou have typed in an invalid deadline.\n\tPlease make sure you follow the correct format.");
        } else {
            return new Deadline(deadline[1], LocalDate.parse(deadline[2]));
        }
    }

    private static Event createEvent(String input) throws EmptyEventException, InvalidEventException {
        String[] event = input.split("event | /at ");
        if (input.equals("event") || input.equals("event ")) {
            throw new EmptyEventException("\n\tThe description of event cannot be empty.\n\tPlease make sure you follow the correct format.");
        } else if (event.length != 3) {
            throw new InvalidEventException("\n\tYou have typed in an invalid event.\n\tPlease make sure you follow the correct format.");
        } else {
            return new Event(event[1], event[2]);
        }
    }
}