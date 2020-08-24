package Duke.Tasks;

import Duke.Errors.InvalidCommandException;

public abstract class Task {
    protected String description;
    protected String done;

    public Task(String description, String done) {
        this.description = description;
        this.done = done;
    }

    /**
     * Takes in an array of Task values to form into a String to be stored on database
     * @param arr String Array of Task values
     * @return String to be saved to database
     * @throws Exception
     */
    public static String stringFormat(String[] arr) throws Exception {
        String s;
        switch (arr[0]) {
            case "T":
                s = String.format("%s,%s,%s\n", arr[0], arr[1], arr[2]);
                break;
            case "E":
                s = String.format("%s,%s,%s,%s\n", arr[0], arr[1], arr[2], arr[3]);
                break;
            case "D":
                s = String.format("%s,%s,%s,%s\n", arr[0], arr[1], arr[2], arr[3]);
                break;
            default:
                throw new InvalidCommandException();
        }
        return s;
    }

    /**
     * Sets Status of Task to be done or not.
     * @param checked String "0" to represent false, and "1" for true
     */
    public void setStatus(String checked) {
        this.done = checked;
    }

    /**
     * abstract object for child classes to return Task as a String Array of values
     * @return String Array of values
     */
    public abstract String[] getStringArr();

    @Override
    public String toString() {
        String icon = this.done.equals("1") ? "[✓]" : "[✗]";
        return String.format("%s %s", icon, this.description);
    }
}