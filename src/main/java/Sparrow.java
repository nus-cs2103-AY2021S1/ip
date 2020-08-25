import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.StringBuilder;

public class Sparrow {
    private Storage storage;
    private TaskList tasks;

    public Sparrow(String filePath) {
        storage = new Storage("data/Sparrow.txt");
        try {
            tasks = new TaskList(storage.loadFromFile());
        } catch (AssertionError e ) {
            System.out.println(standardExceptionMessage() + "file not loaded");
            tasks = new TaskList();
        }

    }

    public Storage getStorage() {
        return storage;
    }

    public TaskList getTasks() {
        return tasks;
    }

    public static void main(String[] args) {
        Sparrow sparrow = new Sparrow("data/Sparrow.txt");
        Ui.greet();

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        Parser parser = new Parser(sparrow.getTasks(), sparrow.getStorage());
        while (parser.parse(command)) {
            command = sc.nextLine();
        }
        sc.close();
    }


    public static String standardExceptionMessage() {
        return "ARR!\uD83C\uDFF4\u200D\u2620\uFE0F️ ";
    }



    public static LocalDate stringToDate(String dateStr) throws DateTimeParseException {
        return LocalDate.parse(dateStr);
    }




}
