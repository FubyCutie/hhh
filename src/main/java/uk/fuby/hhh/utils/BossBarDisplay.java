package uk.fuby.hhh.utils;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import uk.fuby.hhh.core.HHHPlayer;

public class BossBarDisplay extends BukkitRunnable {

    private int seconds;

    private final int timeLimit;

    private final HHHPlayer hhhPlayer;

    private final Player player;

    private final KeyedBossBar bossBar;

    public BossBarDisplay(int seconds, HHHPlayer hhhPlayer, KeyedBossBar bossBar) {
        this.seconds = seconds;
        this.timeLimit = seconds;
        this.hhhPlayer = hhhPlayer;
        this.player = hhhPlayer.getPlayer();
        this.bossBar = bossBar;

    }

    @Override
    public void run() {
        if (!hhhPlayer.isPlaying()) {
            bossBar.removeAll();
            hhhPlayer.setWaiting(false);
            this.cancel();
            return;
        }
        if (seconds == 0) {
            this.cancel();
            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 0.8f);
            hhhPlayer.setWaiting(false);
            bossBar.removeAll();
            return;
        }

        bossBar.setProgress((float) seconds / timeLimit);
        bossBar.setTitle("Time: " + seconds);

        seconds--;
    }
}
