package main.java;

public class DukeExceptions extends Exception {
    public DukeExceptions(String message) {
        super(message);
    }

    protected void continueTrying() {
        try {
            Duke.Echo();
        } catch (DukeExceptions e) {
            System.out.println(e.getMessage());
            System.out.println(Duke.getWrongInput());
            Duke.printLine();
            continueTrying();
        }
    }
}
