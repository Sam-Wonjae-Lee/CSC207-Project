package use_case.match;
import entity.Playlist;
import entity.CommonUser;

import java.util.ArrayList;

public class MatchInteractor implements MatchInputobundary{
    private final Playlist playlist;
    public MatchInteractor(Playlist playlist) {
        this.playlist = playlist;
    }

    @Override
    public void execute(MatchInputData matchInputData) {
        ArrayList<CommonUser> matchedUsers = this.playlist.matchOtherPlaylist();
    }
}
