package uk.fuby.hhh.commands;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import uk.fuby.hhh.Main;
import uk.fuby.hhh.core.HHHPlayer;
import uk.fuby.hhh.utils.BossBarDisplay;
import uk.fuby.hhh.utils.CountdownDisplay;

import java.util.Iterator;
import java.util.UUID;

public class Play implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        Player player = (Player) sender;
        UUID uuid = player.getUniqueId();
        HHHPlayer hhhPlayer = HHHPlayer.getFromPlayer(player);
        if (hhhPlayer == null) return true;

        hhhPlayer.setScore(0);
        hhhPlayer.setPlaying(true);
        hhhPlayer.setWaiting(true);

        int delay = 5;
        int timeLimit = 60;

        NamespacedKey bossBarKey = new NamespacedKey(Main.getPlugin(Main.class), player.getUniqueId().toString());
        KeyedBossBar bossBar = player.getServer().getBossBar(bossBarKey);
        bossBar.addPlayer(player);

        CountdownDisplay countdownDisplay = new CountdownDisplay(delay, hhhPlayer);
        countdownDisplay.runTaskTimerAsynchronously(Main.getPlugin(Main.class), 0, 20);
        BossBarDisplay bossBarDisplay = new BossBarDisplay(timeLimit, hhhPlayer, bossBar);
        bossBarDisplay.runTaskTimerAsynchronously(Main.getPlugin(Main.class), delay * 20, 20);

        new BukkitRunnable() {
            @Override
            public void run() {
                hhhPlayer.setPlaying(false);
                player.sendMessage("You got: " + hhhPlayer.getScore() + " blocks");
            }
        }.runTaskLater(Main.getPlugin(Main.class),  (timeLimit + delay) * 20);

        return true;
    }
}
