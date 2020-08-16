import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private Task[] listOfToDos;
    private String lines = ".~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.";
    private int countToDos;

    Duke() {
        countToDos = 0;
        listOfToDos = new Task[100];
        String welcome = "\n Hello! I'm Yuki *Woof*\n What can I do for you? *Woof*\n";
        System.out.println(lines + welcome + lines);
    }

    public void goodBye() {
        String bye = "\n Bye. Hope to see you again soon! *Woof woof*\n";
        System.out.println(lines + bye + lines);
    }

    public String printTotal() {
        return " Now you have " + countToDos + " tasks in the list. Keep going!!\n";
    }

    public void addToDos(String message) {
        if (message.length() == 0) {
            System.out.println(lines + "\n Woof!! The description cannot be empty!!\n"
                    + lines);
        } else {
            Task t;
            String messageLowerCase = message.toLowerCase();
            if (messageLowerCase.contains("deadline")) {
                int indOfTime = messageLowerCase.indexOf("/by");
                String description = message.substring(9, indOfTime);
                String deadline = message.substring(indOfTime + 4);
                t = new Deadline(countToDos + 1, description, deadline);
            } else if (messageLowerCase.contains("event")) {
                int indOfTime = messageLowerCase.indexOf("/at");
                String description = message.substring(6, indOfTime);
                String at = message.substring(indOfTime + 4);
                t = new Event(countToDos + 1, description, at);
            } else {
                String description = message.substring(5);
                t = new Todo(countToDos + 1, description);
            }
            listOfToDos[countToDos] = t;
            countToDos += 1;
            System.out.println(lines + "\n *WOOF* I have added:\n   " + t + "\n"
                    + printTotal() + lines);
        }
    }

    public void markAsDone(int ind) {
        if (listOfToDos[ind] != null) {
            listOfToDos[ind].markAsDone();
            printTotal();
        } else {
            int taskInd = ind + 1;
            System.out.println(lines + "\n There's no task " + taskInd
                    + " in your list *Woof*\n" + lines);
        }
    }

    public void printToDos() {
        System.out.println(lines);
        if (countToDos == 0) {
            System.out.println(" You have no task to complete! *WOOF*\n" + lines);
        } else {
            System.out.println(" Here are the tasks in your list *Woof*:");
            for (int count=0; count <= 100; count++) {
                if (listOfToDos[count] == null) {
                    System.out.println(lines);
                    break;
                } else {
                    int indTodo = count + 1;
                    System.out.println("   " + indTodo + "." + listOfToDos[count]);
                }
            }
        }
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Duke duke = new Duke();

        while (input.hasNextLine()) {
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
                duke.addToDos(query);
            }
        }

    }
}
