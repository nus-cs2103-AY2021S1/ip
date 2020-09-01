import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class Duke {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private Storage storage;
    private TaskList tasks;

    String getResponse(String input) {
        return processInput(input);
    }

    public Duke() {
        storage = new Storage("data/Duke.txt");
        tasks = new TaskList(storage.loadFile());
    }

    /**
     * Processes each command line by line,
     * calling the corresponding methods
     * Exits when user inputs "bye"
     */
    String processInput(String msg) {
        if(msg.equals("bye")) {
            return "Bye bye. Talk again soon!";
        } else if(msg.equals("list")) {
            return displayList();
        } else if(msg.contains("done")) {
            int index = Integer.parseInt(msg.replace("done ", "").trim());
            return done(index);
        } else if(msg.contains("delete")) {
            int index = Integer.parseInt(msg.replace("delete ", "").trim());
            return delete(index);
        } else if(msg.contains("todo")){
            return addToList(msg.replace("todo ", ""), Type.TODO);
        } else if(msg.contains("event")) {
            return addToList(msg.replace("event ", ""), Type.EVENT);
        } else if(msg.contains("deadline")) {
            return addToList(msg.replace("deadline ", ""), Type.DEADLINE);
        } else if(msg.contains("find")) {
            return find(msg.replace("find ", ""));
        } else {
            return "Sorry, Poco didn't understand. Try again?";
        }
    }

    /**
     * Prints each item in the task list
     */
    String displayList() {
        if(tasks.size() == 0) {
            return "Yay, all done!";
        } else {
            return tasks.toString();
        }
    }

    /**
     * Adds corresponding task to task list
     * @param msg
     * @param type
     */
    String addToList(String msg, Type type) {
        if(msg.trim().isEmpty()) {
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
            }
            storage.saveFile(tasks);
            return "Poco has added " + sp[0] + " to your list" + "\n"
                    + "Pending Tasks: " + tasks.size();
        }

    }

    /**
     * Marks the task at index in task list as done
     * @param index
     */
    String done(int index) {
        index--;
        if(index < 0 || index >= tasks.size()) {
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
     * @param index
     */
    String delete(int index) {
        index--;
        if(index < 0 || index >= tasks.size()) {
            index++;
            return "Poco cannot find the task: " + index;
        } else {
            tasks.remove(index);
            storage.saveFile(tasks);
            return "Poco has deleted the task" + "\n"
                  + tasks.get(index).toString();
        }

    }

    String find(String match) {
        return tasks.find(match);
    }
}

enum Type {
    TODO, EVENT, DEADLINE
}
