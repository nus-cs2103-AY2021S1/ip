import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private final List<Task> listOfTask = new ArrayList<>();
    private final static String ignoreCase = "(?i)";

    enum Command {
        LIST, DONE, BYE, TODO, DEADLINE, EVENT, DELETE, CHECK
    }

    Duke() {
        String welcome = " Hello! I'm Yuki *Woof*\n What can I do for you? *Woof woof*\n";
        Print.print(welcome);
    }

    public void goodBye() {
        String bye = " Bye. Hope to see you again soon! *Woof woof*\n";
        Print.print(bye);
    }

    public String printTotal() {
        return " Now you have " + listOfTask.size() + " tasks in the list. Keep going!!\n";
    }

    public void deleteTask(String message) throws DukeException{
        try {
            int ind = Integer.parseInt(message.substring(6).trim()) - 1;
            Task t = listOfTask.get(ind);
            listOfTask.remove(ind);
            Print.print(" *WOOF* I have removed:\n   " + t + "\n" + printTotal());
        } catch (IndexOutOfBoundsException e) {
            String errMessage = Print.printFormat(" *Woof!* This task does not exist!\n");
            throw new DukeException(errMessage);
        } catch (NumberFormatException e) {
            String errMessage = Print.printFormat(" *Woof!* Please enter an integer value! I can't really read...\n");
            throw new DukeException(errMessage);
        }
    }

    public void addTask(Task t) {
        listOfTask.add(t);
        Print.print(" *WOOF* I have added:\n   " + t + "\n" + printTotal());
    }

    public void checkAction(String message) throws DukeException{
        Task t;
        if (message.matches(ignoreCase + Command.DEADLINE.name() + "(.*)")) {
            t = Deadline.createTask(message);
            addTask(t);
        } else if (message.matches(ignoreCase + Command.EVENT.name() + "(.*)")) {
            t = Event.createTask(message);
            addTask(t);
        } else if (message.matches(ignoreCase + Command.TODO.name() + "(.*)")) {
            t = Todo.createTask(message);
            addTask(t);
        } else if (message.matches(ignoreCase + Command.DELETE.name() + "(.*)")) {
            deleteTask(message);
        } else {
            String errMessage = Print.printFormat(" I'm sorry but i do not know what you want to do. *woof*\n");
            throw new DukeException(errMessage);
        }
    }

    public void markAsDone(int ind) throws DukeException{
        try {
            listOfTask.get(ind).markAsDone();
            printTotal();
        } catch (Exception e) {
            int taskInd = ind + 1;
            String errMessage = Print.printFormat(" There's no task " + taskInd + " in your list *woof*\n");
            throw new DukeException(errMessage);
        }
    }

    public void printToDos() {
        if (listOfTask.size() == 0) {
            Print.print(" You have no task to complete! *WOOF*\n");
        } else {
            String lines = ".~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.\n";
            System.out.print(lines);
            System.out.println(" Here are the tasks in your list *Woof*:");
            listOfTask.forEach((task) -> {
                int ind = listOfTask.indexOf(task) + 1;
                System.out.println("   " + ind + "." + task.toString());
            });
            System.out.println(lines);
        }
    }

    public void checkDate(String s) throws DukeException{
        try {
            String[] inputDate = s.trim().split("/");
            String formatDate = inputDate[0] + "-" + inputDate[1] + "-" + inputDate[2];
            LocalDate date = LocalDate.parse(formatDate);
            int ind = 1;
            String lines = ".~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.\n";
            System.out.print(lines);
            for (Task t : listOfTask) {
                if (t.compareDate(date)) {
                    if (ind == 1) {
                        System.out.println(" Here is the list of ongoing events on "
                                + DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(date) + ":");
                    }
                    System.out.println("   " + ind + "." + t.toString());
                    ind++;
                }
            }
            if (ind == 1) {
                System.out.println(" You have no event on this day! Have a good break! *Woof*");
            }
            System.out.println(lines);
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            String errMessage = Print.printFormat(" Please enter date in YYYY/MM/DD format! *Woof woof*\n");
            throw new DukeException(errMessage);
        }
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Duke duke = new Duke();

        while (input.hasNextLine()) {
            try {
                String query = input.nextLine();
                if (query.matches(ignoreCase + Command.BYE.name())) {
                    duke.goodBye();
                    input.close();
                    break;
                } else if (query.matches(ignoreCase + Command.LIST.name())) {
                    duke.printToDos();
                } else if (query.matches(ignoreCase + Command.DONE.name() + "(.*)")) {
                    int taskInd = Integer.parseInt(query.substring(5));
                    duke.markAsDone(taskInd - 1);
                } else if (query.matches(ignoreCase + Command.CHECK.name() + "(.*)")) {
                    duke.checkDate(query.substring(5));
                } else {
                    duke.checkAction(query);
                }
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }
}
