// deals with interactions with the user
// probably about printing stuff to user ie "talking"

import java.io.IOException;
import java.util.Scanner;

/**
 * This is class to interact with the user.
 */
public class Ui {
    static String HOME = System.getProperty("user.home");
    java.nio.file.Path PATH = java.nio.file.Paths.get(HOME, "ip", "data.txt");

    Parser p = new Parser();

    Scanner sc;

    Ui(Scanner sc) {
        this.sc = sc;
    }

    static void printLine() {
        System.out.println("______________________");
    }

    /**
     * Greets user.
     *
     * @return Next line that user inputs.
     */
    public String greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|";
        printLine();
        System.out.println(logo);
        System.out.println("welcome to my crib");
        printLine();

        return sc.nextLine();
    }

    /**
     * Bids user farewell.
     */
    public void exit() {
        System.out.println("ok u can leave lmao");
        printLine();
    }

    /**
     * List all Tasks in save file.
     *
     * @return Next line that user inputs.
     */
    public String list() throws IOException {
        int counter = 1;
        Scanner myReader = new Scanner(PATH);
        myReader.nextLine();

        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            System.out.println(counter + ". " + p.stringToTask(data));
            counter++;
        }
        printLine();
        return sc.nextLine();
    }

    /**
     * Informs user of completed task.
     *
     * @return Next line that user inputs.
     */
    public String complete(Task t) {
        System.out.println("gfy youve managed to finish the following...");
        t = t.completeTask();
        System.out.println(t);
        printLine();

        return sc.nextLine();
    }

    /**
     * Informs user of deleted task.
     *
     * @return Next line that user inputs.
     */
    public String delete(Task t, int total) {
        System.out.println("removed!! ^^");
        System.out.println(t);
        System.out.println("total task: " + total + "\n:o");
        printLine();

        return sc.nextLine();
    }

    /**
     * Informs user of added task.
     *
     * @return Next line that user inputs.
     */
    public String add(Task t, int total) {
        System.out.println("added!");
        System.out.println(t);
        System.out.println("total task: " + total + "\n:o");
        printLine();

        return sc.nextLine();
    }

    /**
     * Informs user of exception.
     *
     * @return Next line that user inputs.
     */
    public String handleException(Exception e) {
        System.out.println(e.getMessage());
        printLine();

        return sc.nextLine();
    }
}
