/**
 * This is a class that parses string to return relevant information.
 */
public class Parser {
    /**
     * Returns task number that user wants to edit.
     *
     * @param s User input.
     * @return Task number that user wants to edit.
     */
    public int getTaskNumber(String s) {
        int taskNumber = Integer.parseInt(s.split(" ")[1]);
        return taskNumber;
    }

    /**
     * Returns Task after reading string representation stored in save file.
     *
     * @param s String representation of task in save file.
     * @return Task based on data provided in String.
     */
    public Task stringToTask(String s) {
        String[] arr = s.split(" @");
        String type = arr[0];
        String status = arr[1].substring(1);
        Boolean completed;
        String name = arr[2].substring(1);
        String time = arr[3].substring(1);

        if (status.equals("x")) {
            completed = false;
        } else {
            completed = true;
        }

        if (type.equals("todo")) {
            return new Todo(name, completed);
        } else if (type.equals("deadline")) {
            return new Deadline(name, completed, time);
        } else {
            return new Event(name, completed, time);
        }
    }

    /**
     * Returns Task that user wants to add.
     *
     * @param s User input.
     * @return Task based on user input.
     * @throws IncompleteInputException If user input is incomplete.
     */
    public Task commandToTask(String s) throws IncompleteInputException {
        try {
            String[] arr = s.split(" ");
            String type = arr[0];
            String name = arr[1];
            String time;

            if (type.equals("todo")) {
                return new Todo(name);
            } else if (type.equals("deadline")) {
                time = arr[3];
                return new Deadline(name, time);
            } else {
                time = arr[3];
                return new Event(name, time);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IncompleteInputException();
        }
    }
}
