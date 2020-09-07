package duke;

import duke.dependencies.storage.Storage;

public class UserAuthenticator {

    /**
     * Storage object to access and manipulate files/saved data.
     */
    private Storage passwordStorage;

    /**
     * Private constructor for a UserAuthenticator object.
     */
    private UserAuthenticator() {
        passwordStorage = new Storage("cache", "pw.dat");
    }

    /**
     * Factory methods
     * @return UserAuthenticator object.
     */
    public static UserAuthenticator init() {
        return new UserAuthenticator();
    }

    /**
     * Checks if the user details is cached previously.
     *
     * @return True if the user details is already cached.
     */
    public boolean isUserCached() {
        return passwordStorage.isSavedFilePresent();
    }

    /**
     * Initialises the user data file to store the authentication details.
     */
    public void initUserCache() {
        passwordStorage.instantiateFile();
    }

    /**
     * Save the user details.
     * @param userDetails The given details.
     */
    public void save(String userDetails) {
        passwordStorage.instantiateFile();
        passwordStorage.writeStringToFile(userDetails);
    }

    /**
     * Checks if the given user details matches the saved details.
     *
     * @param userDetails The details to be validated.
     * @return True if the given details matches the saved details.
     */
    public boolean check(String userDetails) {
        return passwordStorage.readDataFileAsString().equals(userDetails);
    }

}
