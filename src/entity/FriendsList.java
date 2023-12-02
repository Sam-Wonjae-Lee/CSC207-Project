package entity;

import java.util.HashSet;

public class FriendsList {
    private HashSet<String> user_ids;

    public FriendsList(){
        // initialize FriendsList
        this.user_ids = new HashSet<String>();
    }

    public void add_friend(String user_id){
        this.user_ids.add(user_id);
    }

    public void remove_friend(String user_id){
        // removes invite from inbox
        this.user_ids.remove(user_id);
    }

    public HashSet<String> get_friends(){
        return this.user_ids;
    }
}
