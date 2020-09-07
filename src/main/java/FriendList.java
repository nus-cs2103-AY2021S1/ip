import java.util.ArrayList;
import java.util.List;

public class FriendList {

    public List<Friend> friends;

    public FriendList() {
        this.friends = new ArrayList<>();
    }

    public FriendList(ArrayList<Friend> friends) {
        this.friends = friends;
    }

    public void deleteFriend(int pos) {
        friends.remove(pos);
    }

    public void addFriend(Friend friend) {
        friends.add(friend);
    }
}
