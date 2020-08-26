package duke;

import duke.task.TaskList;

import java.util.Scanner;

/**
 * UI class that deals with interactions with the user.
 */
public class Ui {

    /**
     * Method that simply scan the user's input and change it to a command.
     * @return      a user command as String
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        return str;
    }

    /**
     * Method that load and print the task list.
     *
     * @param list  a Tasklist object
     */
    public void showList(TaskList list) {
        String print = "";
        print += "Here are the tasks in your list:\n";
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size()-1) {
                print += String.format("%d. ", i+1) + list.get(i);
            } else {
                print += String.format("%d. ", i + 1) + list.get(i) + "\n";
            }
        }
        saySomthing(print);
    }

    /**
     * Method that print the welcome message.
     *
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
     * @param str   text in String to be wrapped
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
