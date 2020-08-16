import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private final List<Task> listOfTask = new ArrayList<>();
    private final String lines = ".~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.\n";

    Duke() {
        String welcome = " Hello! I'm Yuki *Woof*\n What can I do for you? *Woof woof*\n";
        System.out.println(lines + welcome + lines);
    }

    public void goodBye() {
        String bye = " Bye. Hope to see you again soon! *Woof woof*\n";
        System.out.println(lines + bye + lines);
    }

    public String printTotal() {
        return " Now you have " + listOfTask.size() + " tasks in the list. Keep going!!\n";
    }

    public void deleteTask(String message) throws DukeException{
        try {
            int ind = Integer.parseInt(message.substring(6).stripLeading().stripTrailing()) - 1;
            Task t = listOfTask.get(ind);
            listOfTask.remove(ind);
            System.out.println(lines + " *WOOF* I have removed:\n   " + t + "\n" + printTotal() + lines);
        } catch (IndexOutOfBoundsException e) {
            String errMessage = lines + " *Woof!* This task does not exist!\n" + lines;
            throw new DukeException(errMessage);
        } catch (NumberFormatException e) {
            String errMessage = lines + " *Woof!* Please enter an integer value\n" + lines;
            throw new DukeException(errMessage);
        }
    }

    public void addTask(Task t) {
        listOfTask.add(t);
        System.out.println(lines + " *WOOF* I have added:\n   " + t + "\n" + printTotal() + lines);
    }

    public void checkAction(String message) throws DukeException{
        Task t;
        String messageLowerCase = message.toLowerCase();
        if (messageLowerCase.contains("deadline")) {
            t = Deadline.createTask(message);
            addTask(t);
        } else if (messageLowerCase.contains("event")) {
            t = Event.createTask(message);
            addTask(t);
        } else if (messageLowerCase.contains("todo")) {
            t = Todo.createTask(message);
            addTask(t);
        } else if (messageLowerCase.contains("delete")) {
            deleteTask(message);
        } else {
            String errMessage = lines + " I'm sorry but i do not know what you want to do. *woof*\n"
                    + lines;
            throw new DukeException(errMessage);
        }
    }

    public void markAsDone(int ind) throws DukeException{
        try {
            listOfTask.get(ind).markAsDone();
            printTotal();
        } catch (Exception e) {
            int taskInd = ind + 1;
            String errMessage = lines + " There's no task " + taskInd + " in your list *woof*\n" + lines;
            throw new DukeException(errMessage);
        }
    }

    public void printToDos() {
        System.out.print(lines);
        if (listOfTask.size() == 0) {
            System.out.println(" You have no task to complete! *WOOF*\n" + lines);
        } else {
            System.out.println(" Here are the tasks in your list *Woof*:");
            listOfTask.forEach((task) -> {
                int ind = listOfTask.indexOf(task) + 1;
                System.out.println("   " + ind + "." + task.toString());
            });
            System.out.println(lines);
        }
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Duke duke = new Duke();

        while (input.hasNextLine()) {
            try {
                String query = input.nextLine();
                String queryLowerCase = query.toLowerCase();
                if (queryLowerCase.equals("bye")) {
                    duke.goodBye();
                    input.close();
                    break;
                } else if (queryLowerCase.equals("list")) {
                    duke.printToDos();
                } else if (queryLowerCase.matches("done (.*)")) {
                    int taskInd = Integer.parseInt(query.substring(5));
                    duke.markAsDone(taskInd - 1);
                } else {
                    duke.checkAction(query);
                }
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }
}
