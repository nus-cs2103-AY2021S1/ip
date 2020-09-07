import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Chatbot class
 */
public class Duke {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private Storage storage;
    private TaskList tasks;


    /**
     * Constructor for Duke
     * Initializes storage path to be data/Duke.txt and
     * loads the file into field variable taskList
     */
    public Duke() {
        storage = new Storage("data/Duke.txt");
        tasks = new TaskList(storage.loadFile());
    }

    String getResponse(String input) {
        return processInput(input);
    }

    /**
     * Parses user input as a command and
     * returns corresponding string
     * @param msg user input
     * @return String
     */
    private String processInput(String msg) {
        assert false;
        String result;
        if (msg.equals("bye")) {
            result = "Bye bye. Talk again soon!";
        } else if (msg.equals("list")) {
            result = displayList();
        } else if (msg.contains("done")) {
            int index = Integer.parseInt(msg.replace("done ", "").trim());
            result = markAsDone(index);
        } else if (msg.contains("delete")) {
            int index = Integer.parseInt(msg.replace("delete ", "").trim());
            result = delete(index);
        } else if (msg.contains("todo")) {
            result = addToList(msg.replace("todo ", ""), TaskType.TODO);
        } else if (msg.contains("event")) {
            result = addToList(msg.replace("event ", ""), TaskType.EVENT);
        } else if (msg.contains("deadline")) {
            result = addToList(msg.replace("deadline ", ""), TaskType.DEADLINE);
        } else if (msg.contains("find")) {
            result = find(msg.replace("find ", ""));
        } else {
            result = "Sorry, Poco didn't understand your command";
        }
        return result;
    }

    /**
     * Prints each item in the task list
     */
    String displayList() {
        if (tasks.size() == 0) {
            return "Yay, all done!";
        } else {
            return tasks.toString();
        }
    }

    /**
     * Adds corresponding task to task list
     *
     * @param msg description of task
     * @param type type of task
     */
    private String addToList(String msg, TaskType type) {
        if (msg.trim().isEmpty()) {
            return "Poco noticed that your task is empty";
        } else {
            String[] sp = new String[]{msg};
            switch (type) {
            case TODO:
                tasks.add(new ToDo(msg));
                break;
            case EVENT:
                sp = msg.split("/");
                LocalDateTime ldt = LocalDateTime.parse(sp[1].trim(), formatter);
                tasks.add(new Event(sp[0], ldt));
                break;
            case DEADLINE:
                sp = msg.split("/");
                LocalDateTime ld = LocalDateTime.parse(sp[1].trim(), formatter);
                tasks.add(new Deadline(sp[0], ld));
                break;
            default:
                System.out.println("invalid task type");
            }
            storage.saveFile(tasks);
            return "Poco has added " + sp[0] + " to your list" + "\n"
                    + "Pending Tasks: " + tasks.size();
        }

    }

    /**
     * Marks the task at index in task list as done
     *
     * @param index index of item that is done
     */
    private String markAsDone(int index) {
        index--;
        if (index < 0 || index >= tasks.size()) {
            return "Poco cannot find the task: " + index;
        } else {
            tasks.done(index);
            storage.saveFile(tasks);
            return "Good job!" + "\n"
                    + tasks.get(index).toString();
        }
    }

    /**
     * Deletes the task at index in task list
     *
     * @param index index of item to be deleted
     */
    private String delete(int index) {
        index--;
        if (index < 0 || index >= tasks.size()) {
            index++;
            return "Poco cannot find the task: " + index;
        } else {
            tasks.remove(index);
            storage.saveFile(tasks);
            return "Poco has deleted the task" + "\n"
                    + tasks.get(index).toString();
        }

    }

    private String find(String match) {
        return tasks.find(match);
    }
}

enum TaskType {
    TODO, EVENT, DEADLINE
}
