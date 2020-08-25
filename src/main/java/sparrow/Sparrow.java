package sparrow;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Sparrow {
    private Storage storage;
    private TaskList tasks;

    public Sparrow(String filePath) {
        storage = new Storage("data/sparrow.Sparrow.txt");
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
        Sparrow sparrow = new Sparrow("data/sparrow.Sparrow.txt");
        Ui.greet();

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        Parser parser = new Parser(sparrow.getTasks(), sparrow.getStorage());
        while (parser.parse(command)) {
            command = sc.nextLine();
        }
        sc.close();
    }

    /**
     * Returns emoji to be pre-pended to an exception message.
     * @return Pre-exception string.
     */
    public static String standardExceptionMessage() {
        return "ARR!\uD83C\uDFF4\u200D\u2620\uFE0F️ ";
    }

    /**
     * Converts String representation of date to LocalDate.
     * @param dateStr String representation of a date.
     * @return LocalDate object.
     * @throws DateTimeParseException
     */
    public static LocalDate stringToDate(String dateStr) throws DateTimeParseException {
        return LocalDate.parse(dateStr);
    }




}
