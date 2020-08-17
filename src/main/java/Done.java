import java.util.List;

public class Done extends NumberAction{

    public static void handleDone(String input, List<Task> tasks) throws DukeException{
        int len = input.length();
        if (len >= 6 && checkIfNumber(input.substring(5, len))) {
            int digit = Integer.parseInt(input.substring(5, len));
            if (checkIfValid(digit, tasks)) {
                Task current = tasks.get(digit - 1);
                if (current.checkIfDone()) {
                    throw new DukeException("OOPS!!! Task has already been mark as done!");
                } else {
                    current.markAsDone();
                    System.out.println("    Nice! I've marked this task as done:");
                    System.out.println("      " + current);
                }
            } else {
                throw new DukeException("OOPS!!! Task number does not exist in the list");
            }
        } else {
            throw new DukeException("OOPS!!! Input format is invalid");
        }
    }
}
