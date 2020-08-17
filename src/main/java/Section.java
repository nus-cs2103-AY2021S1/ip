public class Section {
    String title;

    public Section(String title) {
        this.title = title;
    }

    private final String BORDER = "    _________________________________________________________________________";

    public void displayText() {
        System.out.println(BORDER + "\n      " + this.title + "\n" + BORDER);
    }


}
