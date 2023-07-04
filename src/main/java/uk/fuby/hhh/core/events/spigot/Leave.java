package uk.fuby.hhh.core.events.spigot;

import org.bukkit.event.player.PlayerQuitEvent;
import uk.fuby.hhh.Main;
import uk.fuby.hhh.core.HHHPlayer;

public class Leave {
	public static void execute(PlayerQuitEvent event) {
		HHHPlayer hhhPlayer = HHHPlayer.getFromPlayer(event.getPlayer());
		Main.teams.forEach(x -> x.getPlayers().remove(hhhPlayer));
		Main.players.remove(hhhPlayer);
	}
}
