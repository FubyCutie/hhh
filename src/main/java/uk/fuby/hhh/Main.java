package uk.fuby.hhh;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.plugin.java.JavaPlugin;
import uk.fuby.hhh.commands.JoinTeam;
import uk.fuby.hhh.commands.Play;
import uk.fuby.hhh.core.HHHPlayer;
import uk.fuby.hhh.core.Team;
import uk.fuby.hhh.core.TeamColor;
import uk.fuby.hhh.core.events.listeners.EventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main extends JavaPlugin {

    public static List<HHHPlayer> players = new ArrayList<>();
    public static List<Team> teams = new ArrayList<>();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new EventListener(), this);
        registerCommands();
        registerTeams();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        bossBarRemover();
    }

    private void registerCommands() {
        getCommand("play").setExecutor(new Play());
        getCommand("jointeam").setExecutor(new JoinTeam());
    }

    private void bossBarRemover() {
        getServer().getBossBars().forEachRemaining(x -> getServer().removeBossBar(x.getKey()));
    }

    private void registerTeams() {
        teams.add(new Team(new ArrayList<>(), new TeamColor(DyeColor.RED, Material.RED_WOOL), "red", 0));
        teams.add(new Team(new ArrayList<>(), new TeamColor(DyeColor.BLUE, Material.BLUE_WOOL), "blue", 0));
    }


}