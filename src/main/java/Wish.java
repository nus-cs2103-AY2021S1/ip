public class Wish {
    private Ui ui;

    public static void main(String[] args) {
        new Wish().run();
    }

    private void run() {
        this.ui = new Ui();

        try {
            ui.greet();
            ui.getUserInput();
        } catch (WishException e) {
            System.out.println(e.getMessage());
        }
    }
}
