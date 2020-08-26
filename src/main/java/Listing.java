public class Listing {
    public Listing(String s) {
        this.title = s;
    }
    String title;
    Boolean done = false;

    public void complete() {
        done = true;
    }

    public String doneness() {
        if (done) {
            return "[✓]";
        } else {
            return "[✗]";
        }
    }

    public void checkDoneness(String s) {
        if (s.equals("1")) {
            this.done = true;
        } else {
            this.done = false;
        }
    }

    public String[] toArray() {
        return new String[2];
    }

    public String toString(){
        return this.title;
    }
}
