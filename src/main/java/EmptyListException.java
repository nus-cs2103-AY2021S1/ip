public class EmptyListException extends DukeException {

    /**
     * Gives the String representation of the Exception
     *
     * @return a String
     */
    public String toString() {
        return "____________________________________________________________"
                + "\n"
                + " ☹ OOPS!!! Your list is currently empty!"
                + "\n"
                + "____________________________________________________________";
    }
}
