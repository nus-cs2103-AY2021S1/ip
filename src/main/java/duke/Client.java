package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Class representing generic tasks.
 */
class Client {
    /** Variable to store client name. */
    protected String name;
    /** Variable to store if client is male. */
    protected boolean isMale;
    /** Variable to store client birthday. */
    protected LocalDate birthday;

    /**
     * Constructs client.
     *
     * @param name Client name.
     * @param birthday Client birthday.
     */
    Client(String name, boolean isMale, LocalDate birthday) {
        this.name = name;
        this.isMale = isMale;
        this.birthday = birthday;
    }

    /**
     * Gets client name.
     *
     * @return Client name.
     */
    private String getName() {
        return this.name;
    }

    /**
     * Gets client gender in string format.
     *
     * @return Client gender.
     */
    private String getGenderString() {
        return isMale ? "M" : "F";
    }

    /**
     * Returns simple string format for file.
     *
     * @return Simple string description.
     */
    String toStringForStorage() {
        return  " | " + this.getName();
    }

    @Override
    public String toString() {
        String formattedDate = this.birthday.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
        return this.getName() + " " + this.getGenderString() + " (birthday: " + formattedDate + ")";
    }

}
