package uk.fuby.hhh.utils;


import org.bukkit.Bukkit;
import uk.fuby.hhh.core.HHHPlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CountdownDisplay extends BukkitRunnable {
    private int seconds;

    private final HHHPlayer hhhPlayer;

    private final Player player;

    public CountdownDisplay(int seconds, HHHPlayer hhhPlayer) {
        this.seconds = seconds;
        this.hhhPlayer = hhhPlayer;
        this.player = hhhPlayer.getPlayer();
    }

    @Override
    public void run() {

        if (!hhhPlayer.isPlaying()) {
            this.cancel();
            return;
        }
        if (seconds == 0) {
            this.cancel();
            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 0.8f);
            hhhPlayer.setWaiting(false);
            return;
        }


        char color = switch (seconds) {
            case 5, 4 -> 'e'; // yellow
            case 3, 2, 1 -> 'c'; // red
            default -> 'a'; // green
        };

        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1, 0.8f);
        player.sendTitle("§" + color + seconds, "", 0, 20, 0);
        seconds--;
    }
}
