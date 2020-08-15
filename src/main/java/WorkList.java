import java.util.ArrayList;

public class WorkList {
    private final ArrayList<String> workList;

    WorkList() {
        this.workList = new ArrayList<>();
    }

    public void addWork(String work) {
        this.workList.add(work);
    }

    public String readWork() {
        StringBuilder results = new StringBuilder("1. " + this.workList.get(0));
        for(int i = 1; i < this.workList.size(); i ++) {
            results.append("\n");
            results.append(i + 1);
            results.append(". ");
            results.append(this.workList.get(i));
        }
        return results.toString();

    }
}
