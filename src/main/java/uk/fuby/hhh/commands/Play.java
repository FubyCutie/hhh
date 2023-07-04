package uk.fuby.hhh.commands;

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
import uk.fuby.hhh.core.Team;
import uk.fuby.hhh.utils.BossBarDisplay;
import uk.fuby.hhh.utils.CountdownDisplay;

public class Play implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        boolean teamsEmpty = true;
        for (Team team : Main.teams) {
            if (team.getPlayers().size() > 0) {
                teamsEmpty = false;
                break;
            }
        }
        if (teamsEmpty) return true;

        int delay = 5;
        int timeLimit = 60;

        for (Team team : Main.teams) {
            team.setScore(0);
            for (HHHPlayer hhhPlayer : team.getPlayers()) {

                System.out.println(team.getName() + " has the player: " + hhhPlayer.getPlayer().getName());

                Player player = hhhPlayer.getPlayer();
                hhhPlayer.setScore(0);
                hhhPlayer.setPlaying(true);
                hhhPlayer.setWaiting(true);

                NamespacedKey bossBarKey = new NamespacedKey(Main.getPlugin(Main.class), player.getUniqueId().toString());
                KeyedBossBar bossBar = player.getServer().getBossBar(bossBarKey);
                bossBar.addPlayer(player);

                System.out.println(hhhPlayer);

                CountdownDisplay countdownDisplay = new CountdownDisplay(delay, hhhPlayer);
                countdownDisplay.runTaskTimerAsynchronously(Main.getPlugin(Main.class), 0, 20);
                BossBarDisplay bossBarDisplay = new BossBarDisplay(timeLimit, hhhPlayer, bossBar);
                bossBarDisplay.runTaskTimerAsynchronously(Main.getPlugin(Main.class), delay * 20, 20);

                System.out.println(hhhPlayer);
                System.out.println("=========");
            }
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                for (Team team : Main.teams) {
                    for (HHHPlayer hhhPlayer : team.getPlayers()) {
                        Player player = hhhPlayer.getPlayer();
                        hhhPlayer.setPlaying(false);
                        player.sendMessage("Your team got: " + team.getScore() + " blocks");
                    }
                }
            }
        }.runTaskLater(Main.getPlugin(Main.class),  (timeLimit + delay) * 20);

        return true;
    }
}
