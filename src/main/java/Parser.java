import java.io.IOException;
import java.time.LocalDate;

public class Parser {

    public static String process(String order) throws IOException {
        if (order.equals("bye")) {
            Storage.writeData(TaskList.list);//write data into the new file before exiting
            return "    Bye-bye, see you next time!";
        } else if (order.equals("list")) {
            if (TaskList.list.size() == 0) {
                return "    Well done! You've completed all your tasks.";
            } else {
                return TaskList.printList(TaskList.list);
            }
        } else if (order.length() >= 4 && order.substring(0, 4).equals("done")) {
            int index = Integer.parseInt(order.substring(5))-1;
            Task temp = TaskList.list.get(index);
            temp.finish();
            TaskList.list.set(index, temp);
            Storage.writeData(TaskList.list);
            return "    Great! I have marked this task as done:\n" + temp;
        } else if (order.length() >= 4 && order.substring(0, 4).equals("todo")) {
            if (order.length()>4) {
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
                TaskList.list.add(new Deadline(false,content, ddl));
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
                TaskList.list.add(new Event(false,content, ddl));
                Storage.writeData(TaskList.list);

                return "    added:" + content + "\n" + "    Now you have " + TaskList.list.size() + " task(s) in the list";
            } else {
                return "    description cannot be empty~";
            }
        } else if (order.length() >= 6 && order.substring(0, 6).equals("delete")) {
            if (order.length() > 6) {
                Integer toBeDeleted = Integer.valueOf(order.substring(7));
                Task temp = TaskList.list.get(toBeDeleted-1);
                TaskList.list.remove(toBeDeleted-1);
                Storage.writeData(TaskList.list);
                return "    I have removed this task: " + "\n" + temp;
            } else {
                return "    Sorry, you have not specified which task to be deleted.";
            }
        }
        else {
            return "    Sorry, I don't understand."; //handles all unexpected inputs
        }
    }

    public static String unparse(Task task) {
        return task.unparseMessage;
    }

}
