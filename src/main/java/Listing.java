public class Listing {
    public Listing(String s) {
        this.title = s;
    }
    String title;
    Boolean isDone = false;

    public void complete() {
        isDone = true;
    }

    public String doneness() {
        if (isDone) {
            return "[✓]";
        } else {
            return "[✗]";
        }
    }

    public void checkDoneness(String s) {
        if (s.equals("1")) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
    }

    public String[] toArray() {
        return new String[2];
    }

    public String toString(){
        return this.title;
    }
}
