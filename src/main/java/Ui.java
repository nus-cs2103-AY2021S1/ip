import java.util.Scanner;

class Ui {

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void sendMessage(String message) {
        System.out.println(String.format("----------\n%s\n----------\n", message));
        return;
    }

    public String getInput() {
        String input = this.scanner.nextLine();
        return input;
    }
}
