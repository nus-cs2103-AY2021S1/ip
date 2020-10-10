package main.java;

//@@author {Ringo1225}-reused
//{reused code from nus-cs2103-AY2021S1/ip
// url: https://github.com/nus-cs2103-AY2021S1/ip}
/**
 * The User Interface of the robot
 * It prints the greeting message and remind user to input
 */
public class Ui {
    String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

//@@author

    /**
     * prints out the logo and greeting message
     */
    public void start() {
        System.out.println("Hello from\n" + logo);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke\n     What can I do for you?");
        System.out.println("    ____________________________________________________________\n");
    }

    public String greet() {
        return "Hello from\n" + logo + "     Hello! I'm Duke\n     What can I do for you?";
    }

    /**
     * prints out the loading error message
     */
    protected void showLoadingError() {
        System.out.println("Oops!! There is a Loading error!!");
    }
}
