// deals with interactions with the user
// probably about printing stuff to user ie "talking"

import java.io.IOException;
import java.util.Scanner;

public class Ui {
    static String HOME = System.getProperty("user.home");
    java.nio.file.Path PATH = java.nio.file.Paths.get(HOME, "ip", "data.txt");
    Scanner sc;
    Parser p = new Parser();

    Ui(Scanner sc) {
        this.sc = sc;
    }

    static void printLine() {
        System.out.println("______________________");
    }

    String greet() {
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

    void exit() {
        System.out.println("ok u can leave lmao");
        printLine();
    }

    String list() throws IOException {
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

    String complete(Task t) {
        System.out.println("gfy youve managed to finish the following...");
        t = t.completeTask();
        System.out.println(t);
        printLine();

        return sc.nextLine();
    }

    String delete(Task t, int total) {
        System.out.println("removed!! ^^");
        System.out.println(t);
        System.out.println("total task: " + total + "\n:o");
        printLine();

        return sc.nextLine();
    }

    String add(Task t, int total) {
        System.out.println("added!");
        System.out.println(t);
        System.out.println("total task: " + total + "\n:o");
        printLine();

        return sc.nextLine();
    }

    String handleException(Exception e) {
        System.out.println(e.getMessage());
        printLine();

        return sc.nextLine();
    }

    String find(String keyword) throws IOException {
        int counter = 1;
        Scanner reader = new Scanner(PATH);

        String line = reader.nextLine();
        line = reader.nextLine();

        while (reader.hasNextLine()) {
            if (line.contains(keyword)) {
                System.out.println(counter + ". " + p.stringToTask(line));
                counter++;
            }
            line = reader.nextLine();
        }

        printLine();
        return sc.nextLine();
    }
}
