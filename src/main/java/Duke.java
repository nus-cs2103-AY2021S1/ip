import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private String[] listOfToDos;
    private String lines = ".~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.";
    private int countToDos;

    Duke() {
        countToDos = 0;
        listOfToDos = new String[100];
        String welcome = "\n Hello! I'm Yuki *Woof*\n What can I do for you? *Woof*\n";
        System.out.println(lines + welcome + lines);
    }

    public void goodBye() {
        String bye = "\n Bye. Hope to see you again soon! *Woof woof*\n";
        System.out.println(lines + bye + lines);
    }

    public void addToDos(String message) {
        listOfToDos[countToDos] = message;
        countToDos += 1;
        System.out.println(lines + "\n I have added: " + message + " *Woof*\n" + lines);
    }

    public void printToDos() {
        int count = 0;
        System.out.println(lines);
        for (String message : listOfToDos) {
            if (countToDos == 0) {
                System.out.println(" You have no task to complete! *WOOF*\n" + lines);
                break;
            } else {
                if (listOfToDos[count] == null) {
                    System.out.println(lines);
                    break;
                } else {
                    count += 1;
                    System.out.println(" " + count + ". " + message);
                }
            }
        }
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Duke duke = new Duke();

        while (input.hasNextLine()) {
            String query = input.nextLine();
            if (query.equalsIgnoreCase("bye")) {
                duke.goodBye();
                input.close();
                break;
            } else if (query.equalsIgnoreCase("list")) {
                duke.printToDos();
            } else {
                duke.addToDos(query);
            }
        }

    }
}
