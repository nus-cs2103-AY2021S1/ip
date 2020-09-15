import java.util.ArrayList;
import java.util.List;

public class FriendList {

    public List<Friend> friends;

    /**
     * Constructor for the class.
     */
    public FriendList() {
        this.friends = new ArrayList<>();
    }

    /**
     * Constructor for the class.
     */
    public FriendList(ArrayList<Friend> friends) {
        this.friends = friends;
    }

    /**
     * Delete the friend from your friend list.
     * @param pos
     */
    public void deleteFriend(int pos) {
        friends.remove(pos);
    }

    /**
     * Add new friend.
     * @param friend
     */
    public void addFriend(Friend friend) {
        friends.add(friend);
    }
}
