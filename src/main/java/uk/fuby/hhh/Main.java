package uk.fuby.hhh;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.plugin.java.JavaPlugin;
import uk.fuby.hhh.commands.Play;
import uk.fuby.hhh.core.HHHPlayer;
import uk.fuby.hhh.core.events.listeners.EventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main extends JavaPlugin {

    public static List<HHHPlayer> players = new ArrayList<>();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new EventListener(), this);
        registerCommands();
        bossBarRemover();
    }

    private void registerCommands() {
        getCommand("play").setExecutor(new Play());
    }

    private void bossBarRemover() {
        Iterator<KeyedBossBar> bossBarIterator = getServer().getBossBars();
        for (Iterator<KeyedBossBar> it = bossBarIterator; it.hasNext(); ) {
            KeyedBossBar bossBar = it.next();
            NamespacedKey key = bossBar.getKey();
            getServer().removeBossBar(key);
        }
    }

}