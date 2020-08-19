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
            //not the extra whitespace
            return "[✓] ";
        } else {
            return "[✗] ";
        }
    }

    public String toString(){
        return this.title;
    }
}
