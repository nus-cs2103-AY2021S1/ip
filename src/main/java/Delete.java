import java.util.List;

public class Delete extends NumberAction {

    public static void handleDeletion(String input, List<Task> tasks) throws DukeException{
        int len = input.length();
        if (len >= 8 && checkIfNumber(input.substring(7, len))) {
            int digit = Integer.parseInt(input.substring(7, len));

            if (checkIfValid(digit, tasks)) {
                Task current = tasks.get(digit - 1);
                tasks.remove(digit - 1);
                System.out.println("    Noted. I've removed this task:");
                System.out.println("      " + current);
                System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
            } else {
                throw new DukeException("OOPS!!! Task number does not exist in the list");
            }
        } else {
            throw new DukeException("OOPS!!! Input format is invalid");
        }
    }
}
