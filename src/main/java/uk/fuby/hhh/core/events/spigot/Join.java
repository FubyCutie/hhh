package uk.fuby.hhh.core.events.spigot;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.event.player.PlayerJoinEvent;
import uk.fuby.hhh.Main;
import uk.fuby.hhh.core.HHHPlayer;

public class Join {

    public static void execute(PlayerJoinEvent event) {
        NamespacedKey key = new NamespacedKey(Main.getPlugin(Main.class), event.getPlayer().getUniqueId().toString());
        event.getPlayer().getServer().removeBossBar(key);
        event.getPlayer().getServer().createBossBar(key, "Time", BarColor.GREEN, BarStyle.SEGMENTED_20);

        HHHPlayer hhhPlayer = new HHHPlayer(event.getPlayer());
        Main.players.add(hhhPlayer);

    }
}
