import java.io.*;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

enum Category {
    TODO, DEADLINE, EVENT, LIST, BYE, DONE, DELETE, READ
}

public class Duke {

    public static void printHello() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("-------------------------------------------\n" +
                "Hello! I'm Duke\n" + "What can I do for you?\n" +
                "-------------------------------------------\n");
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        printHello();
        Scanner sc = new Scanner(System.in);
        String input;

        TaskList taskList = Storage.load();
        while (true) {
            try {
                input = sc.nextLine(); //1. read input
                Command command = Parser.decideCategory(input);
                command.execute(taskList);
                Storage.store(taskList);
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            } catch (DateTimeParseException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}










