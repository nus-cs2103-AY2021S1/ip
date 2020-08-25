import exceptions.DukeException;

import java.util.Scanner;

public class Ui {
    Scanner sc = new Scanner(System.in);

    public void greetings() {
        showLine();
        String greetings = "Hello! I'm Duke\n" + "What can I do for you?\n";
        System.out.println(greetings);
        showLine();
    }

    public void goodbye() {
        // print goodbye message
        String goodbye = "Bye. Hope to see you again soon!";
        System.out.println(goodbye);
        showLine();
        // exits program
        System.exit(0);
    }

    public void badInput() throws DukeException {
        throw new DukeException(
                "I'm sorry, but I don't know what that means :(\n" +
                "\nHere's the command list:\n" +
                "1. list - show your current list of tasks\n" +
                "2. todo - creates a to-do task\n" +
                "3. event - creates an event task\n" +
                "4. deadline - creates a deadline task\n" +
                "5. done - marks task as done\n" +
                "6. delete - deletes task from the list");
    }

    public void showLine() {
        System.out.println("____________________________________________________________\n");
    }

    public String readCommand() throws DukeException {
        String input = null;
        input = sc.nextLine().trim(); // get rid of leading and trailing spaces
        return input;
    }
}
