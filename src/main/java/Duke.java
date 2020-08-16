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

    public void addToDos(String message) {
        if (message.length() == 0) {
            System.out.println(lines + "\n Woof!! The description cannot be empty!!\n"
                    + lines);
        } else {
            listOfToDos[countToDos] = new Task(countToDos + 1, message);
            countToDos += 1;
            System.out.println(lines + "\n I have added: " + message + " *Woof*\n" + lines);
        }
    }

    public void markAsDone(int ind) {
        if (listOfToDos[ind] != null) {
            listOfToDos[ind].markAsDone();
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
                    System.out.println("  " + listOfToDos[count]);
                }
            }
        }
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Duke duke = new Duke();

        while (input.hasNextLine()) {
            String query = input.nextLine();
            String lowerCaseQuery = query.toLowerCase();
            if (lowerCaseQuery.equals("bye")) {
                duke.goodBye();
                input.close();
                break;
            } else if (lowerCaseQuery.equals("list")) {
                duke.printToDos();
            } else if (lowerCaseQuery.matches("done (.*)")) {
                int taskInd = Integer.parseInt(query.substring(5));
                duke.markAsDone(taskInd - 1);
            } else {
                duke.addToDos(query);
            }
        }

    }
}
