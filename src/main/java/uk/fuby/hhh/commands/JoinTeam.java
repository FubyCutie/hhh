package uk.fuby.hhh.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import uk.fuby.hhh.Main;
import uk.fuby.hhh.core.HHHPlayer;
import uk.fuby.hhh.core.Team;

import java.util.UUID;

public class JoinTeam implements CommandExecutor {
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		if (!(sender instanceof Player player)) return true;
		UUID uuid = player.getUniqueId();
		HHHPlayer hhhPlayer = HHHPlayer.getFromPlayer(player);
		if (hhhPlayer == null) return true;
		if (hhhPlayer.isPlaying()) return true;
		if (hhhPlayer.isWaiting()) return true;

		if (args.length != 1) {
			player.sendMessage("§cInvalid arguments.");
			return true;
		}

		for (Team team: Main.teams) {
			if (team.getName().equals(args[0].toLowerCase())) {
				if (hhhPlayer.getTeam() != null) {
					hhhPlayer.getTeam().getPlayers().remove(hhhPlayer);
				}
				team.getPlayers().add(hhhPlayer);
				hhhPlayer.setTeam(team);
				player.sendMessage("§aYour team was set to " + team.getName());
				return true;
			}
		}

		return true;
	}



}
