import java.util.ArrayList;
import java.util.List;

public class DukeList {

    List<Task> list;

    DukeList() {
        this.list = new ArrayList<>();
    }

    public String addItem(String item) {
        this.list.add(new Task(item));
        return "added: " + item;
    }

    public String markDone(int index) {
        if (index >= this.list.size()) {
            return "Please choose a valid task number.";
        }
        return this.list.get(index).markDone();
    }

    @Override
    public String toString() {
        if (this.list.size() == 0) {
            return "There are currently no tasks.";
        }
        String result = "Current tasks:\n";
        for (int i = 1; i <= this.list.size(); i++) {
            result += i + ". " + this.list.get(i - 1) + "\n";
        }
        return result;
    }
}
