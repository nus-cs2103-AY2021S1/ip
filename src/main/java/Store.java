public class Store {
    private String[] strStore;
    private int nextIndx;

    public Store() {
        this.strStore = new String[100];
        this.nextIndx = 0;
    }

    public void add(String str) {
        strStore[nextIndx] = str;
        StringUtils.printWithWrapper(new String[]{"Added: " + str});
        nextIndx++;
    }

    public void list() {
        StringUtils.printWithWrapper(this.strStore);
    }
}
