public class StorageParser {

    public static boolean isDone(String symbol) {
        if (symbol.equals("✓")) {
            return true;
        } else {
            return false;
        }
    }

    public static Task parse(String input) {
        Task task = null;
        String[] splitTask = input.split(" ", 2);
        String typeAndIsDone = splitTask[0].replaceFirst("\\]", "|").
                replaceAll("\\[","").replaceAll("\\]","");
        String[] typeAndIsDoneArr = typeAndIsDone.split("\\|");

        if (typeAndIsDoneArr[0].equals("T")) {
            boolean isDone = isDone(typeAndIsDoneArr[1]);
            task = new Todo(isDone, splitTask[1]);
        } else if (typeAndIsDoneArr[0].equals("D")) {
            boolean isDone = isDone(typeAndIsDoneArr[1]);
            String organisedDescription = splitTask[1].replaceAll(" \\(by: ","|").replaceAll("\\)","");
            String[] splitDescription = organisedDescription.split("\\|");
            task = new Deadline(isDone, splitDescription[0], splitDescription[1]);
        } else if (typeAndIsDoneArr[0].equals("E")) {
            boolean isDone = isDone(typeAndIsDoneArr[1]);
            String organisedDescription = splitTask[1].replaceAll(" \\(at: ","|").replaceAll("\\)","");
            String[] splitDescription = organisedDescription.split("\\|");
            task = new Event(isDone, splitDescription[0], splitDescription[1]);
        } else {
            System.out.println("error in reading input");
        }
        return task;
    }

    public static void main(String[] args) {
        System.out.println(StorageParser.parse("[T][✗] read book"));
        System.out.println(StorageParser.parse("[D][✗] return book (by: 3 mar)"));
    }
}
