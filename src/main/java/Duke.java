import java.util.Scanner;
import java.util.ArrayList;
import static java.lang.Integer.parseInt;

public class Duke {
    private static final String LINE = "_______________________________________________";
    private static final String KEYWORD_BYE = "bye";
    private static final String KEYWORD_LIST = "list";
    private static final String KEYWORD_DONE = "done";
    private static final String KEYWORD_TODO = "todo";
    private static final String KEYWORD_EVENT = "event";
    private static final String KEYWORD_DEADLINE = "deadline";
    private static final String KEYWORD_DELETE = "delete";

    private final Scanner sc;
    private ArrayList<Task> listTasks;

    Duke() {
        this.sc = new Scanner(System.in);
        this.listTasks = new ArrayList<>();
    }

    // activate the Duke Bot
    public void echo() {
        greetings();
        while (sc.hasNext()) {
            try {
                String input = sc.nextLine();
                String[] inputArr = input.trim().split(" ", 2);
                if (isEnd(inputArr[0])) {
                    goodBye();
                    break;
                } else if (isList(inputArr[0])) {
                    showListTasks(listTasks);
                } else if (isValidDone(inputArr[0]) && isNumber(inputArr[1])) {
                    // checking if the input is valid
                    if (inputArr.length == 1) {
                        throw new InvalidFormatDoneException();
                    }
                    marking(parseInt(inputArr[1]), listTasks.size());
                } else if (isTask(inputArr[0])) {
                    // checking if the input is valid
                    if (inputArr.length == 1) {
                        throw new EmptyTextException(inputArr[0]);
                    }
                    addTask(inputArr[0], inputArr[1]);
                } else if (isDelete(inputArr[0])) {
                    // checking if the input is valid
                    if (inputArr.length == 1) {
                        throw new InvalidFormatDeleteException();
                    }
                    deleteTask(parseInt(inputArr[1]));
                } else {
                    throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                printException(e.getMessage());
            }
        }
    }

    private void greetings() {
        messageFormatter(() -> {
            System.out.println("Hello! I'm Duke ^.^");
            System.out.println("What can I do for you?");
        });
    }

    private void goodBye() {
        messageFormatter(() -> System.out.println("Bye ^.^, Hope to see you again soon!!!"));
    }

    private void printException(String msg) {
        messageFormatter(() -> System.out.println(msg));
    }

    // Formatter to format any message. Easily customizable
    private void messageFormatter(Runnable func) {
        System.out.println(LINE);
        func.run();
        System.out.println(LINE);
        System.out.println(); // optional
    }

    private boolean isNumber(String str) {
        try {
            int num = parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidDone(String type) {
        return type.equals(KEYWORD_DONE);
    }

    public boolean isEnd(String type) {
        return type.equals(KEYWORD_BYE);
    }

    public boolean isList(String type) {
        return type.equals(KEYWORD_LIST);
    }

    public boolean isTODO(String type) {
        return type.equals(KEYWORD_TODO);
    }

    public boolean isDeadline(String type) {
        return type.equals(KEYWORD_DEADLINE);
    }

    public boolean isEvent(String type) {
        return type.equals(KEYWORD_EVENT);
    }

    private boolean isTask(String type) {
        return isDeadline(type) || isTODO(type) || isEvent(type);
    }

    public boolean isDelete(String type) {
        return type.equals(KEYWORD_DELETE);
    }

    // Printing out the items in the list
    private void showListTasks(ArrayList<Task> listTasks) {
        if (listTasks.size() == 0) {
            messageFormatter(() -> System.out.println("Your list is empty!!!"));
        } else {
            messageFormatter(() -> {
                System.out.println("Here are the tasks in your list:");
                for(int i = 1; i <= listTasks.size(); i++) {
                    System.out.println(i + ". " + listTasks.get(i - 1));
                }
            });
        }
    }

    // marking the task
    private void marking(int num, int size) throws InvalidFormatDoneException {
        if (num <= 0 || size < num) {
            messageFormatter(() -> System.out.println("Invalid input for done"));
        } else {
            Task task = listTasks.get(num - 1);
            messageFormatter(() -> task.markAsDone());
        }
    }

    // adding the task into the list
    private void addTask(String type, String message)
            throws InvalidFormatDeadlineException, InvalidFormatEventException {
        Task task;
        String[] dateTime;
        if (isTODO(type)) {
            task = new ToDo(message);
        } else if (isDeadline(type)) {
            dateTime = message.split(" /by ", 2);
            // checking if the input is valid
            if (dateTime.length == 1) {
                throw new InvalidFormatDeadlineException();
            }
            task = new Deadline(dateTime[0], dateTime[1]);
        } else if (isEvent(type)){
            dateTime = message.split(" /at ", 2);
            // checking if the input is valid
            if (dateTime.length == 1) {
                throw new InvalidFormatEventException();
            }
            task = new Event(dateTime[0], dateTime[1]);
        } else {
            return;
        }
        listTasks.add(task);
        messageFormatter(() -> {
            System.out.println("Got it. I've added this task:");
            System.out.println(task);
            System.out.printf("Now you have %d tasks in the list.\n", listTasks.size());
        });
    }

    private void deleteTask(int pos) {
        if (listTasks.size() == 0) {
            messageFormatter(() -> System.out.println("Your list is empty!!!"));
        } else if (pos <= 0 || pos > listTasks.size()) {
            messageFormatter(() -> System.out.println("Invalid input for delete"));
        } else {
            Task task = listTasks.get(pos - 1);
            listTasks.remove(pos - 1);
            messageFormatter(() -> {
                System.out.println("Noted . I've removed this task:");
                System.out.println(task);
                System.out.printf("Now you have %d tasks in the list.\n", listTasks.size());
            });
        }
    }

}
