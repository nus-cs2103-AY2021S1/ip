package duke;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import duke.task.TaskList;



/**
 * UI class that deals with interactions with the user.
 */
public class Ui {

    /**
     * Method that simply scan the user's input and change it to a command.
     *
     * @return a user command as String
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        return str;
    }

    /**
     * Method that load and print the task list.
     *
     * @param list a Tasklist object
     */
    public String showList(TaskList list) {
        return "Here are the tasks in your list:\n" + IntStream.range(0, list.size())
                .mapToObj(i -> String.format("%d. %s", i + 1, list.getList().get(i).toString()))
                .collect(Collectors.joining("\n"));
    }

    /**
     * Method that print the welcome message.
     */
    public void sayHi() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you mate?");
    }

    /**
     * Method that wrap lines around text that Duke send out.
     *
     * @param str text in String to be wrapped
     */
    public void saySomthing(String str) {
        System.out.println("------------------------------------------------------------------------");
        System.out.println(str);
        System.out.println("------------------------------------------------------------------------");
    }

    /**
     * Method that echos whatever the input is.
     *
     * @param input String of input
     */
    public void echo(String input) {
        this.saySomthing(input);
    }

    /**
     * Method that print the exit message.
     */
    public void byeMessage() {
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-");
        System.out.println("Bye. Hope to see you again soon!!!");
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-");
    }
}
