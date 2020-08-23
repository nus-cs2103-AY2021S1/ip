import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
public class Duke {

    private static String line = "________________________________________________";

    public static void display(String text) {
        System.out.println(line);
        System.out.println(text);
        System.out.println(line);
    }

    public static Task assignTask(String type, String name) throws NoTaskException, InvalidCommandException, NoDateException {

        try {
            if (type.equals("todo")) {
                try {
                    return new Todo(name.substring(5), false);
                } catch (IndexOutOfBoundsException e) {
                    throw new NoTaskException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
            } else {
                int indexOfCommand = name.indexOf("/");
                String deadline = name.substring(indexOfCommand + 4);
                    if (type.equals("deadline")) {
                        try {
                            String currname = name.substring(9);
                            if (indexOfCommand > -1 ) {
                                return new Deadline(name.substring(9, indexOfCommand - 1), false, deadline);
                            } else {
                                throw new NoDateException("☹ OOPS!!! Please specify the deadline!");
                            }
                        } catch (IndexOutOfBoundsException e) {
                            throw new NoTaskException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                    } else {
                        try {
                            String currname = name.substring(6);
                            if (indexOfCommand > -1 ) {
                                return new Deadline(name.substring(6, indexOfCommand - 1), false, deadline);
                            } else {
                                throw new NoDateException("☹ OOPS!!! Please specify when the event is going to be held!");
                            }
                        } catch (IndexOutOfBoundsException e) {
                            throw new NoTaskException("☹ OOPS!!! The description of a event cannot be empty.");
                        }
                    }
            }
        } catch (NullPointerException e) {
            throw new InvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static int decideIndexDelete(String word) throws MissingSpecifiedDeleteError {
        int index = 0;
        try {
            index = Integer.parseInt(word.substring(7));
        } catch (IndexOutOfBoundsException e) {
            throw new MissingSpecifiedDeleteError("☹ OOPS!!! Please specify which task you want to delete.");
        }
        return index;
    }

    public static Task deletedTask(int index, ArrayList<Task> tasks) throws WrongDeleteIndexError {
        Task curr = null;
        try {
            curr = tasks.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new WrongDeleteIndexError("☹ OOPS!!! You only have " + tasks.size() + " tasks in your list. " +
                    "Please select a valid task to be deleted.");
        }
        return curr;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String logo = "Hello I'm Verzachtend \n" +
                "What can I do for you?\n" +
                "BE YOURSELF, NEVER SURRENDER AND KEEP A SMILE ON YOUR FACE";
        System.out.println(logo);

        String end = "bye";
        String done = "done";
        String delete = "delete";
        ArrayList<Task> tasks = new ArrayList<>();
        String echo = scan.nextLine();
        String listing = "list";

        while (!echo.equals(end)) {
            String displayText = "";
            if (echo.equals(listing)) {
                int i = 1;
                System.out.println(line);
                System.out.println("Here are the tasks in your list:");
                for (Task item :tasks) {
                    System.out.println(i + ". " + item);
                    i++;
                }
                System.out.println(line);
            } else if (echo.toLowerCase().contains(done)) {
                int num = Integer.parseInt(echo.substring(5));
                Task curr = tasks.get(num - 1).setToTrue();
                tasks.set(num - 1, curr);
                curr = tasks.get(num - 1);
                displayText = "Nice! I've marked this task as done: \n"
                        + curr;
                System.out.println(line);
                System.out.println(curr);
                System.out.println(line);
            } else if (echo.toLowerCase().contains(delete)) {
                int num = 0;
                try {
                    num = decideIndexDelete(echo);
                    try {
                        Task removed = deletedTask(num,tasks);
                        tasks.remove(num - 1);
                        System.out.println(line);
                        System.out.println("Noted. I've removed this task: \n" +
                                "  " + removed + "\n" +
                                "Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println(line);
                    } catch (WrongDeleteIndexError e) {
                        System.out.println(line);
                        System.out.println(e.getMessage());
                        System.out.println(line);
                    }
                } catch (MissingSpecifiedDeleteError e) {
                    System.out.println(line);
                    System.out.println(e.getMessage());
                    System.out.println(line);
                }


            } else {
                String firstWord = echo.toLowerCase().contains("todo") ? "todo"
                        : echo.toLowerCase().contains("deadline") ? "deadline"
                        : echo.toLowerCase().contains("event") ? "event"
                        : null;

                Task curr = null;
                try {
                    curr = assignTask(firstWord, echo);
                    tasks.add(curr);
                    System.out.println(line);
                    System.out.println("Got it. I've added this task: \n"
                            + " " + curr + "\n"
                            + "Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(line);
                } catch (NoTaskException e) {
                    System.out.println(line);
                    System.out.println(e.getMessage());
                    System.out.println(line);
                } catch (InvalidCommandException e) {
                    System.out.println(line);
                    System.out.println(e.getMessage());
                    System.out.println(line);
                } catch (NoDateException e){
                    System.out.println(line);
                    System.out.println(e.getMessage());
                    System.out.println(line);
                }
            }
            echo = scan.nextLine();
        }
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
