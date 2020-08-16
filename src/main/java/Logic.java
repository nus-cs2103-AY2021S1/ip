package main.java;

public class Logic {
    private Store store;

    public Logic() {
        this.store = new Store();
    }

    private final String LIST_COMMAND = "list";
    private final String EXIT_COMMAND = "bye";

    public boolean digestString(String answer) {
        String editted_answer = answer.strip().toLowerCase();
        if (editted_answer.equals(LIST_COMMAND)) return this.store.printStore();
        else if (editted_answer.equals(EXIT_COMMAND)) return this.exit();
        else {
            this.store.addToStore(answer);
            return true;
        }
    }

    private boolean exit() {
        System.out.println("Bye! See you later :)");
        return false;
    }

}
