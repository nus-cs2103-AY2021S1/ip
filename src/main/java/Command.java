import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Command {

    private DukeList list;

    Command(DukeList list) {
        this.list = list;
    }

    public String processCommand(String command) throws DukeException {
        String[] stringArray = command.split(" ");
        List<String> stringList = new ArrayList<>(Arrays.asList(stringArray));
        String com = stringList.remove(0);

        if (com.equals("hello")) {
            return "Hi! I'm Duke! Pleasure to meet you :)";
        }
        if (com.equals("list") || com.equals("done") || com.equals("delete")) {
            try {
                return this.processList(com, stringList);
            } catch (Exception e){
                return e.getMessage();
            }
        }
        if (com.equals("todo") || com.equals("deadline") || com.equals("event")) {
            try {
                return this.processTask(com, stringList);
            } catch (Exception e) {
                return e.getMessage();
            }
        }
        throw new DukeException("Sorry, I did not understand: " + command);
    }

    private String processTask(String com, List<String> stringList) throws DukeException {
        if (com.equals("todo")) {
            if (!stringList.isEmpty()) {
                return list.addItem(new Todo(String.join(" ", stringList)));
            } else {
                throw new DukeException("Please write a task to be done, with \"todo <task>\"");
            }
        }
        if (com.equals("deadline")) {
            if (!stringList.isEmpty()) {
                String[] taskAndDate = Command.getTaskAndDate(stringList);
                return list.addItem(new Deadline(taskAndDate[0], taskAndDate[1]));
            } else {
                throw new DukeException("Please write a deadline, with \"deadline <task> /by <date>\"");
            }
        }
        if (com.equals("event")) {
            if (!stringList.isEmpty()) {
                String[] taskAndDate = Command.getTaskAndDate(stringList);
                return list.addItem(new Event(taskAndDate[0], taskAndDate[1]));
            } else {
                throw new DukeException("Please write an event, with \"event <task> /at <date>\"");
            }
        }
        return null;
    }

    private String processList(String com, List<String> stringList) throws DukeException {
        if (com.equals("list")) {
            return list.toString();
        }
        if (com.equals("done")) {
            if (!stringList.isEmpty()) {
                return list.markDone(Integer.parseInt(stringList.get(0)) - 1);
            } else {
                throw new DukeException("Please choose a task to mark as done, with \"done <task number>\"");
            }
        }
        if (com.equals("delete")) {
            if (!stringList.isEmpty()) {
                return list.deleteItem(Integer.parseInt(stringList.get(0)) - 1);
            } else {
                throw new DukeException("Please choose a task to delete, with \"delete <task number>\"");
            }
        }
        return null;
    }

    private static String[] getTaskAndDate(List<String> description) {
        String[] result = new String[2];
        int dateIndex = -1;
        for (int i = 0; i < description.size(); i++) {
            if (description.get(i).charAt(0) == '/') {
                dateIndex = i;
            }
        }
        if (dateIndex == -1) {
            result[0] = String.join(" ", description.subList(0, description.size()));
            result[1] = "No date set";
        } else {
            result[0] = String.join(" ", description.subList(0, dateIndex));
            result[1] = String.join(" ", description.subList(dateIndex + 1, description.size()));
        }

        return result;
    }
}
