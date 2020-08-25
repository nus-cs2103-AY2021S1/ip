package utility;

public class Ui {

    /**
     * Prints out the welcome text.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String msg = "____________________________________________________________\n"
                + "Hello I'm Duke\n"
                + "Your personal smart assistant\n"
                + "____________________________________________________________";
        System.out.println(msg);
    }

    /**
     * Prints out a line.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

}
