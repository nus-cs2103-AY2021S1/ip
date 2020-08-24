import java.util.ArrayList;
import java.util.Scanner;

public class BotHandler {
    private static ArrayList<Task> lst = new ArrayList<>();

    private static void displayList() throws DuckieException{
        if (lst.size() == 0) {
            throw new DuckieNoListException();
        }

        BotResponses.displayListReply(lst);
    }

    private static void addTask(String input, String type) throws DuckieException{
        Task t1;
        try {
            if (type.equals("todo")) {
                String todo = input.split(" ", 2)[1];
                t1 = new Todo(todo);
            } else {
                String[] splitted = input.split("/");
                String time = splitted[1].split(" ", 2)[1];
                String description = splitted[0].split(" ", 2)[1];
                if (type.equals("deadline")) {
                    t1 = new Deadline(description, time);
                } else { //This will be Event task.
                    t1 = new Event(description, time);
                }
            }
        } catch (Exception e) {
            throw new DuckieInsufficientInfoException();
        }
        lst.add(t1);
        BotResponses.addTaskReply(t1, lst);
    }

    private static void checkTask(int ind) {
        Task t1 = lst.get(ind - 1);
        t1.checked();
        BotResponses.checkTaskReply(t1);
    }

    public static void deleteTask(int ind) {
        Task t1 = lst.get(ind - 1);
        lst.remove(ind - 1);
        BotResponses.deleteTaskReply(t1);
    }

    //Check if a String only
    private static boolean is_word(String s) {
        return (s.length() > 0 && s.split("\\s+").length == 1);
    }

    public static void serve() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            try {
                String initialInput = sc.nextLine();
                String input = initialInput.strip();
                System.out.println(input);
                if (input.equalsIgnoreCase("bye")) {
                    System.out.print(BotResponses.getHorizL());
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    displayList();
                } else if (input.toLowerCase().indexOf("done") == 0
                        || input.toLowerCase().indexOf("delete") == 0) {
                    if (lst.size() == 0) {
                        throw new DuckieNoListException();
                    } else if (is_word(input)) {
                        throw new DuckieInsufficientInfoException();
                    }
                    int ind = Integer.parseInt(input.split(" ")[1]);

                    if (lst.size() < ind) {
                        throw new DuckieNoIndexException();
                    }

                    if (input.toLowerCase().indexOf("done") == 0) {
                        checkTask(ind);
                    } else {
                        deleteTask(ind);
                    }
                } else if (input.toLowerCase().indexOf("todo") == 0) {
                    addTask(input, "todo");
                } else if (input.toLowerCase().indexOf("deadline") == 0) {
                    addTask(input, "deadline");
                } else if (input.toLowerCase().indexOf("event") == 0) {
                    addTask(input, "event");
                } else {
                    throw new DuckieInvalidCommandException();
                }
            } catch (DuckieException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
