package uk.fuby.hhh.core.events.listeners;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import uk.fuby.hhh.core.events.spigot.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
public class EventListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Join.execute(event);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Hoe.execute(event);
    }
}
