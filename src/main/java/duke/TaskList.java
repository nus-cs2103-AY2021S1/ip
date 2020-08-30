package duke;

import java.util.List;

public class TaskList {
    List<Task> lst;
    Storage storage;

    TaskList() {
        storage = new Storage("data.txt");
        lst = storage.loadFile();
    }

    private void addTodo(String desc) {
        desc = desc.trim();
        if (desc.isEmpty())
            throw new IllegalArgumentException("☹ OOPS!!! The description of a todo cannot be empty.");
        lst.add(new Todo(desc));
    }

    private void addDeadline(String desc) {
        desc = desc.trim();
        if (desc.isEmpty())
            throw new IllegalArgumentException("☹ OOPS!!! Missing arguments for deadline task");
        String newDesc = desc.substring(0, desc.indexOf('/') - 1);
        String time = desc.substring(desc.indexOf('/') + 4);
        lst.add(new Deadline(newDesc, time));

    }

    public void addEvent(String desc) {
        desc = desc.trim();
        if (desc.isEmpty())
            throw new IllegalArgumentException("☹ OOPS!!! Missing arguments for event task");
        String newDesc = desc.substring(0, desc.indexOf('/') - 1);
        String time = desc.substring(desc.indexOf('/') + 4);
        lst.add(new Event(newDesc, time));
    }

    public void addTask(String command) {
        if (command.startsWith("todo")) addTodo(command.substring(4));
        else if (command.startsWith("deadline")) addDeadline(command.substring(8));
        else if (command.startsWith("event")) addEvent(command.substring(5));
        Ui.addTask(lst);
        storage.writeFile(lst);
    }
    public void markDone(String command) {
        int num = Integer.parseInt(command) - 1;
        lst.get(num).markDone();
        Ui.markDone(lst.get(num));
        storage.writeFile(lst);
    }
    public void delete(String command) {
        int num = Integer.parseInt(command) - 1;
        Task cur = lst.remove(num);
        Ui.delete(cur, lst);
        storage.writeFile(lst);
    }
    public void list() {
        Ui.list(lst);
    }
}