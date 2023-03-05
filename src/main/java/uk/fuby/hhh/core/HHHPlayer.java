package uk.fuby.hhh.core;

import org.bukkit.entity.Player;
import uk.fuby.hhh.Main;

public class HHHPlayer {

    private boolean isPlaying;

    private boolean isWaiting;

    private int score;

    private Player player;

    public HHHPlayer(Player player) {
        this.isPlaying = true;
        this.player = player;
    }

    public static HHHPlayer getFromPlayer(Player p) {
        int size = Main.players.size();

        for (int i = 0; i < size; i++) {
            HHHPlayer qp = Main.players.get(i);
            if (qp == null) break;
            if (qp.player.getUniqueId().equals(p.getUniqueId())) {
                return (qp);
            }
        }
        return (null);
    }

    public Player getPlayer() { return player; }

    public boolean isPlaying() { return isPlaying; }

    public void setPlaying(boolean playing) { isPlaying = playing; }

    public int getScore() { return score; }

    public void setScore(int score) { this.score = score; }

    public boolean isWaiting() { return isWaiting; }

    public void setWaiting(boolean waiting) { isWaiting = waiting; }
}
