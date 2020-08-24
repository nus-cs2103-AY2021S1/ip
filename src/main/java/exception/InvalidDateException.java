package exception;

public class InvalidDateException extends Exception {
    @Override
    public String toString() {
        String lines = "____________________\n";
        return lines + "Invalid date format detected!\n"
                + "Accepted formats: 'dd/MM/yyyy' or 'dd-MM-yyyy'\n"
                + lines;
    }
}
