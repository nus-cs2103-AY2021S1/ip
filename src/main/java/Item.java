public class Item {
    private String item;
    private Boolean isDone;

    public Item(String item) {
        this.item = item;
        this.isDone = false;
    }

    public String getItemName() {
        return item;
    }

    public Boolean isDone() {
        return isDone;
    }

    public void setDone() {
        isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getItemName();
    }
}
