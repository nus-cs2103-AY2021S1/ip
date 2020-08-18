public class TaskCreator {
    public static Task createTask(String input) {
        if (input.contains("todo")) {
            return new Todo(input.substring(5));
        } else if (input.contains("deadline")) {

            // Splitting string
            String substring = input.substring(9);
            String[] strArr = substring.split("/by");
            String description = strArr[0];
            String date = strArr[1];
            return new Deadline(description, date);
        } else if (input.contains("event")) {

            // Splitting string
            String substring = input.substring(6);
            String[] strArr = substring.split("/at");
            String description = strArr[0];
            String date = strArr[1];
            return new Event(description, date);
        } else {
            return null;
        }
    }
}
