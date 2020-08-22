import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class TaskList {
    protected List<Task> data;
    public TaskList() {
        data = new ArrayList();
    }
    public TaskList(List<Task> data) {
        this.data = data;
    }
    public List<Task> getData() {
        return data;
    }
    public void done(String input, Ui ui, Storage storage) throws DukeException, FileNotFoundException {
        if (input.length() <= 5) {
            // Exception: eg. done
            throw new DukeException("     ☹ OOPS!!! The description of done is incomplete.");
        }
        int n = Integer.parseInt(input.substring(5)) - 1;
        if (n > data.size() || n < 0) {
            //Exception: eg. done 999
            throw new DukeException("     ☹ OOPS!!! The index is out of bounds.");
        }
        data.get(n).done();
        ui.printDone(data, n);
        storage.writeFile(data);
    }
    public void delete(String input, Ui ui, Storage storage) throws DukeException, FileNotFoundException {
        if (input.length() <= 7) {
            // Exception: eg. delete
            throw new DukeException("     ☹ OOPS!!! The description of delete is incomplete.");
        }
        int n = Integer.parseInt(input.substring(7)) - 1;
        if (n > data.size() || n < 0) {
            //Exception: eg. delete 999
            throw new DukeException("     ☹ OOPS!!! The index is out of bounds.");
        }
        ui.printDeletePre(data, n);
        data.remove(n);
        ui.printDeletePost(data, n);
        storage.writeFile(data);
    }
    public void todo(String input, Ui ui, Storage storage) throws DukeException, FileNotFoundException {
        if (input.length() <= 5) {
            // Exception: eg. todo
            throw new DukeException("     ☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Todo t = new Todo(input.substring(5));
        data.add(t);
        ui.printTodo(data, t);
        storage.writeFile(data);
    }
    public void deadline(String input, Ui ui, Storage storage) throws DukeException, FileNotFoundException {
        if (input.length() <= 9) {
            // Exception: eg. deadline
            throw new DukeException("     ☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        String[] ss = input.split("/");
        if (!ss[1].startsWith("by ")) {
            // Exception: eg. deadline do homework /Mon
            throw new DukeException("     ☹ OOPS!!! Please enter the time following the format: by XXX");
        }
        Deadline t = new Deadline(ss[0].substring(9),
                LocalDateTime.of(Integer.parseInt(ss[3].split(" ")[0]), Integer.parseInt(ss[2]),
                        Integer.parseInt(ss[1].substring(3)),
                        Integer.parseInt(ss[3].split(" ")[1].substring(0, 2)),
                        Integer.parseInt(ss[3].split(" ")[1].substring(2))));
        data.add(t);
        ui.printDeadline(data, t);
        storage.writeFile(data);
    }
    public void event(String input, Ui ui, Storage storage) throws DukeException, FileNotFoundException {
        if (input.length() <= 9) {
            // Exception: eg. event
            throw new DukeException("     ☹ OOPS!!! The description of an event cannot be empty.");
        }
        String[] ss = input.split("/");
        if (!ss[1].startsWith("at ")) {
            // Exception: eg. event meeting /Mon
            throw new DukeException("     ☹ OOPS!!! Please enter the time following the format: at XXX");
        }
        Event t = new Event(ss[0].substring(9),
                LocalDateTime.of(Integer.parseInt(ss[3].split(" ")[0]), Integer.parseInt(ss[2]),
                        Integer.parseInt(ss[1].substring(3)),
                        Integer.parseInt(ss[3].split(" ")[1].substring(0, 2)),
                        Integer.parseInt(ss[3].split(" ")[1].substring(2))));
        data.add(t);
        ui.printEvent(data, t);
        storage.writeFile(data);
    }
}

