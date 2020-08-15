import java.util.ArrayList;
import java.util.List;

public class Store {
    List<String> store;
    List<Boolean> checkList;

    public Store() {
        this.store = new ArrayList<>();
        this.checkList = new ArrayList<>();
    }

    public void addItem(String item) {
        this.store.add(item);
        this.checkList.add(false);
    }

    public String getItem(Integer index) {
        return this.store.get(index);
    }

    public boolean getDoneIndicator(Integer index) {
        return this.checkList.get(index);
    }

    public void setDone(Integer index) {
        this.checkList.set(index, true);
    }

    public int size() {
        return this.store.size();
    }
}
