import java.io.IOException;
import java.time.LocalDate;

/**
 * Represents a parser which is used to parse user's input.
 */
public class Parser {
    /**
     * Takes in user's order and passes order to TaskList and Storage.
     *
     * @param order User's input order.
     * @return Response to the user.
     * @throws IOException
     */
    public static String process(String order) throws IOException {
        assert order.length() > 0 : "command length should be more at least 1 character";
        if (order.equals("bye")) {
            //Storage.writeData(TaskList.list); //write data into the new file before exiting
            return "    Bye-bye, see you next time!";
        } else if (order.equals("list")) {
            if (TaskList.list.size() == 0) {
                return "    Well done! You've completed all your tasks.";
            } else {
                return TaskList.printList(TaskList.list);
            }
        } else if (order.length() >= 4 && order.substring(0, 4).equals("done")) {
            int index = Integer.parseInt(order.substring(5)) - 1;
            Task temp = TaskList.list.get(index);
            temp.finish();
            TaskList.list.set(index, temp);
            assert TaskList.list.get(index).getStatusIcon().equals("\u2713") : "attribute isDone should be set to true";
            Storage.writeData(TaskList.list);
            return "    Great! I have marked this task as done:\n" + temp;
        } else if (order.length() >= 4 && order.substring(0, 4).equals("todo")) {
            if (order.length() > 4) {
                String content = order.substring(5);
                TaskList.list.add(new Todo(false, content));
                Storage.writeData(TaskList.list);
                return "    added:" + content + "\n" + "    Now you have " + TaskList.list.size() + " task(s) in the list";
            } else {
                return "    description cannot be empty~";
            }
        } else if (order.length() >= 8 && order.substring(0, 8).equals("deadline")) {
            if (order.length() > 8) {
                Integer indexOfSlash = order.indexOf('/');
                String content = order.substring(9, indexOfSlash);
                String due = order.substring(indexOfSlash + 4);
                LocalDate ddl = LocalDate.parse(due);
                TaskList.list.add(new Deadline(false, content, ddl));
                Storage.writeData(TaskList.list);

                return "    added:" + content + "\n" + "    Now you have " + TaskList.list.size() + " task(s) in the list";
            } else {
                return "    description cannot be empty~";
            }
        } else if (order.length() >= 5 && order.substring(0, 5).equals("event")) {
            if (order.length() > 5) {
                int indexOfSlash = order.indexOf('/');
                String content = order.substring(6, indexOfSlash);
                String time = order.substring(indexOfSlash + 4);
                LocalDate ddl = LocalDate.parse(time);
                TaskList.list.add(new Event(false, content, ddl));
                Storage.writeData(TaskList.list);

                return "    added:" + content + "\n" + "    Now you have " + TaskList.list.size() + " task(s) in the list";
            } else {
                return "    description cannot be empty~";
            }
        } else if (order.length() >= 6 && order.substring(0, 6).equals("delete")) {
            if (order.length() > 6) {
                int toBeDeleted = Integer.parseInt(order.substring(7)) - 1;
                Task temp = TaskList.list.get(toBeDeleted);
                TaskList.list.remove(toBeDeleted);
                Storage.writeData(TaskList.list);
                return "    I have removed this task: " + "\n" + temp;
            } else {
                return "    Sorry, you have not specified which task to be deleted.";
            }
        } else if (order.length() >= 1 && order.charAt(0) == 't') {
            if (order.length() > 1) {
                String content = order.substring(2);
                TaskList.list.add(new Todo(false, content));
                Storage.writeData(TaskList.list);
                return "    added:" + content + "\n" + "    Now you have " + TaskList.list.size() + " task(s) in the list";
            } else {
                return "    description cannot be empty~";
            }
        } else if (order.length() >= 1 && order.charAt(0) == 'd') {
            if (order.length() > 1) {
                Integer indexOfSlash = order.indexOf('/');
                String content = order.substring(2, indexOfSlash);
                String due = order.substring(indexOfSlash + 4);
                LocalDate ddl = LocalDate.parse(due);
                TaskList.list.add(new Deadline(false, content, ddl));
                Storage.writeData(TaskList.list);

                return "    added:" + content + "\n" + "    Now you have " + TaskList.list.size() + " task(s) in the list";
            } else {
                return "    description cannot be empty~";
            }
        } else if (order.length() >= 1 && order.charAt(0) == 'e') {
            if (order.length() > 1) {
                int indexOfSlash = order.indexOf('/');
                String content = order.substring(2, indexOfSlash);
                String time = order.substring(indexOfSlash + 4);
                LocalDate ddl = LocalDate.parse(time);
                TaskList.list.add(new Event(false, content, ddl));
                Storage.writeData(TaskList.list);

                return "    added:" + content + "\n" + "    Now you have " + TaskList.list.size() + " task(s) in the list";
            } else {
                return "    description cannot be empty~";
            }
        } else if (order.length() >= 5 && order.substring(0, 4).equals("find")) {
            if (order.length() > 5) {
                String toBeFound = order.substring(5);
                return TaskList.find(toBeFound);
            } else {
                return "    Please specify what you wish to find.";
            }
        } else if (order.equals("play")) {
            Ui ui = new Ui();
            ui.setBot(new PlayBot());
            ui.play();
            return "";
        } else {
            return "    Sorry, I don't understand."; //handles all unexpected inputs
        }
    }

    /**
     * Converts a task into a string to write in File.txt.
     *
     * @param task Task to be written in File.txt.
     * @return A string to be written in File.txt.
     */
    public static String unparse(Task task) {
        return task.unparseMessage;
    }

}
