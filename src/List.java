import java.util.ArrayList;

public class List {
    int count;
    ArrayList<String> list;

    private List() {
        count = 0;
        list = new ArrayList<>();
    }

    public static List startList() {
        return new List();
    }

    public String addToList(String s) {
        this.count = this.count + 1;
        this.list.add(s);

        return "added: " + s;
    }

    public String listToString() {
        if (this.count == 0) {
            return "There are no items in your list";
        }

        String msg = "";
        int num = 1;
        for (String s : this.list) {
            msg = msg + num + ". " + s + "\n\t";
            num++;
        }
        return msg;
    }
}
