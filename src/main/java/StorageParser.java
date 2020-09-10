import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StorageParser {

    public static boolean isDone(String symbol) {
        return symbol.equals("\u2713");
    }

    /**
     * Parse a line of String into Task.
     * @param input String
     * @return
     */
    public static Task parse(String input) {
        Task task = null;
        String[] splitTask = input.split(" ", 2);
        String typeAndIsDone = splitTask[0].replaceFirst("\\]", "|")
                .replaceAll("\\[", "").replaceAll("\\]", "");
        String[] typeAndIsDoneArr = typeAndIsDone.split("\\|");

        if (typeAndIsDoneArr[0].equals("T")) {
            boolean isDone = isDone(typeAndIsDoneArr[1]);
            task = new Todo(isDone, splitTask[1]);
        } else if (typeAndIsDoneArr[0].equals("D")) {
            boolean isDone = isDone(typeAndIsDoneArr[1]);
            String organisedDescription = splitTask[1].replaceAll(" \\(by: ", "|")
                    .replaceAll("\\)", "");
            String[] splitDescription = organisedDescription.split("\\|");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            LocalDate localDate = LocalDate.parse(splitDescription[1], formatter);
            task = new Deadline(isDone, splitDescription[0], localDate);
        } else if (typeAndIsDoneArr[0].equals("E")) {
            boolean isDone = isDone(typeAndIsDoneArr[1]);
            String organisedDescription = splitTask[1].replaceAll(" \\(at: ", "|")
                    .replaceAll("\\)", "");
            String[] splitDescription = organisedDescription.split("\\|");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mma");
            LocalDateTime localDateAndTime = LocalDateTime.parse(splitDescription[1], formatter);
            task = new Event(isDone, splitDescription[0], localDateAndTime);
        } else {
            System.out.println("error in reading input");
        }
        return task;
    }

    /**
     * Test parsing string into Task.
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(StorageParser.parse("[T][✓] read book"));
        System.out.println(StorageParser.parse("[D][✗] return book (by: Oct 15 2019, 12:00PM)"));
    }
}
