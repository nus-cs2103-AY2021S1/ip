import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;

public class TaskList {
    private ArrayList<Task> list = new ArrayList<>();

    public int size() {
        return this.list.size();
    }

    public Task addTask(String type, String input) throws DukeException {
        Task task;
        if (type.equals("todo")) {
            task = new Todo(input);
        } else if (type.equals("deadline")) { // datetime formatter: "dd-MM-yyyy kk:mm"
            String[] arr = input.split("/by");
            try {
                task = new Deadline(arr[0].trim(), arr[1].trim());
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! I'm sorry, when is the deadline? (/by...)");
            } catch (DateTimeParseException e) {
                throw new DukeException("Invalid date-time format! (/by...)");
            }
        } else if (type.equals("event")) {
            String[] arr = input.split("/at");
            try {
                task = new Event(arr[0].trim(), arr[1].trim());
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! I'm sorry, when is the event? (/at...)");
            } catch (DateTimeParseException e) {
                throw new DukeException("Invalid date-time format! (/at...)");
            }
        } else {
            throw new DukeException("OOPS!!! I'm sorry, I don't know what that means :<");
        }
        if (input.isEmpty()) {
            throw new DukeException("OOPS!!! I'm sorry, the description cannot be empty :<");
        }
        this.list.add(task);
        return task;
    }

    public Task markAsDone(int i) throws DukeException {
        try {
            return this.list.get(i).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! I'm sorry, the task number is out of range :<");
        }
    }

    public Task deleteTask(int i) throws DukeException {
        try {
            return this.list.remove(i);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! I'm sorry, the task number is out of range :<");
        }
    }

    @Override
    public String toString() {
        String listString = "";
        for (int i = 0; i < this.size(); i++) {
            int taskNum = i + 1;
            listString += taskNum + ".";
            listString += this.list.get(i).toString();
            if (i < this.size() - 1) {
                listString += '\n';
            }
        }
        return listString;
    }
}
