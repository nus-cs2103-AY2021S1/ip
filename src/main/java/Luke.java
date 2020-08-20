import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Luke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        System.out.printf("Luke:\n\tHey there! I'm Luke.\n\tPlease tell me what to add to your list.\nYou:\n\t");
        while (true) {
            String input = sc.nextLine().toLowerCase();
            if (Pattern.matches("^(todo) *.*$", input)) {
                try {
                    Task newTodo = createTodo(input);
                    list.add(newTodo);
                    String number = countTasks(list);
                    System.out.printf("Luke:\n\tThe following task has been successfully added.\n\t\t%s\n\tNow you have %s in your list.\nYou:\n\t", newTodo, number);
                } catch (EmptyTodoException e) {
                    System.out.printf("Luke:%s\nYou:\n\t", e.getMessage());
                }
            } else if (Pattern.matches("^(deadline) *.*$", input)){
                try {
                    Deadline newDeadline = createDeadline(input);
                    list.add(newDeadline);
                    String number = countTasks(list);
                    System.out.printf("Luke:\n\tThe following task has been successfully added.\n\t\t%s\n\tNow you have %s in your list.\nYou:\n\t", newDeadline, number);
                } catch (EmptyDeadlineException e) {
                    System.out.printf("Luke:%s\nYou:\n\t", e.getMessage());
                } catch (InvalidDeadlineException e) {
                    System.out.printf("Luke:%s\nYou:\n\t", e.getMessage());
                }
            } else if (Pattern.matches("^(event) *.*$", input)) {
                try {
                    Event newEvent = createEvent(input);
                    list.add(newEvent);
                    String number = countTasks(list);
                    System.out.printf("Luke:\n\tThe following task has been successfully added.\n\t\t%s\n\tNow you have %s in your list.\nYou:\n\t", newEvent, number);
                } catch (EmptyEventException e) {
                    System.out.printf("Luke:%s\nYou:\n\t", e.getMessage());
                } catch (InvalidEventException e) {
                    System.out.printf("Luke:%s\nYou:\n\t", e.getMessage());
                }
            } else if (input.equals("list")) {
                if (list.size() < 1) {
                    System.out.printf("Luke:\n\tYou don't have any tasks in your list :(\nYou:\n\t");
                } else {
                    String todoSummary = "Luke:\n\tHere are the tasks in your list.";
                    for (int i = 0; i < list.size(); i++) {
                        Task current = list.get(i);
                        todoSummary += String.format("\n\t%d.%s", i + 1, current);
                    }
                    System.out.printf("%s\nYou:\n\t", todoSummary);
                }
            } else if (Pattern.matches("^(done) *[0-9]*$", input)) {
               try {
                   completeTask(input, list);
               } catch (InvalidDoneException e) {
                   System.out.printf("Luke:%s\nYou:\n\t", e.getMessage());
               } catch (DoneIndexOutofboundsException e) {
                   System.out.printf("Luke:%s\nYou:\n\t", e.getMessage());
               }
            } else if (Pattern.matches("^(delete) *[0-9]*$", input)) {
                try {
                    deleteTask(input, list);
                } catch (InvalidDeleteException e) {
                    System.out.printf("Luke:%s\nYou:\n\t", e.getMessage());
                } catch (DeleteIndexOutofboundsException e) {
                    System.out.printf("Luke:%s\nYou:\n\t", e.getMessage());
                }
            } else if (input.equals("bye")) {
                System.out.println("Luke:\n\tOh, are you leaving? Hope to see you soon!");
                break;
            } else {
                System.out.printf("Luke:\n\tSorry I do not understand :( Please try another command.\nYou:\n\t");
            }
        }
    }

    private static String countTasks(ArrayList<Task> arrayList) {
        int n = arrayList.size();
        return n <= 1
                ? String.format("%d task", n)
                : String.format("%d tasks", n);
    }

    private static Task createTodo(String input) throws EmptyTodoException {
        String todo = input.replaceAll("todo ", "");
        if (input.equals("todo") || input.equals("todo ")) {
            throw new EmptyTodoException("\n\tThe description of todo cannot be empty.\n\tPlease make sure you follow the correct format.");
        } else {
            return new Task(todo);
        }
    }

    private static Deadline createDeadline(String input) throws EmptyDeadlineException, InvalidDeadlineException {
        String[] deadline = input.split("deadline | /by ");
        if (input.equals("deadline") || input.equals("deadline ")) {
            throw new EmptyDeadlineException("\n\tThe description of deadline cannot be empty.\n\tPlease make sure you follow the correct format.");
        } else if (deadline.length != 3) {
            throw new InvalidDeadlineException("\n\tYou have typed in an invalid deadline.\n\tPlease make sure you follow the correct format.");
        } else {
            return new Deadline(deadline[1], deadline[2]);
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

    private static void completeTask(String input, ArrayList<Task> arrayList) throws InvalidDoneException, DoneIndexOutofboundsException {
        if (input.equals("done") || input.equals("done ")) {
            throw new InvalidDoneException("\n\tThe index of done cannot be empty.\n\tPlease make sure you follow the correct format.");
        }
        String numbering = input.replaceAll("\\D+", ""); //extract only the digits from the input
        int index = Integer.parseInt(numbering) - 1;
        if (index < 0 || index >= arrayList.size()) {
            throw new DoneIndexOutofboundsException("\n\tYou have typed in an invalid number.\n\tPlease check your list again.");
        } else {
            Task done = arrayList.get(index);
            done.markAsDone();
            System.out.printf("Luke:\n\tThe following task has successfully been marked as done!\n\t\t%s\nYou:\n\t", done);
        }
    }

    private static void deleteTask(String input, ArrayList<Task> arrayList) throws InvalidDeleteException, DeleteIndexOutofboundsException {
        if (input.equals("delete") || input.equals("delete ")) {
            throw new InvalidDeleteException("\n\tThe index of delete cannot be empty.\n\tPlease make sure you follow the correct format.");
        }
        String numbering = input.replaceAll("\\D+", ""); //extract only the digits from the input
        int index = Integer.parseInt(numbering) - 1;
        if (index < 0 || index >= arrayList.size()) {
            throw new DeleteIndexOutofboundsException("\n\tYou have typed in an invalid number.\n\tPlease check your list again.");
        } else {
            Task delete = arrayList.get(index);
            arrayList.remove(index);
            System.out.printf("Luke:\n\tThe following task has successfully been deleted!\n\t\t%s\nYou:\n\t", delete);
        }
    }
}