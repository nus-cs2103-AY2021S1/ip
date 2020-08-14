import java.util.ArrayList;
import java.util.List;

public class DukeList {

    List<String> list;

    DukeList() {
        this.list = new ArrayList<>();
    }

    public String addItem(String item) {
        this.list.add(item);
        return "added: " + item;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 1; i <= this.list.size(); i++) {
            result += i + ". " + this.list.get(i - 1) + "\n";
        }
        return result;
    }
}
