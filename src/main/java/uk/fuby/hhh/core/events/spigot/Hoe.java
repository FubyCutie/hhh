package uk.fuby.hhh.core.events.spigot;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import uk.fuby.hhh.core.HHHPlayer;
import uk.fuby.hhh.core.Team;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Hoe {

    public static void execute(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        HHHPlayer hhhPlayer = HHHPlayer.getFromPlayer(player);


        if (hhhPlayer == null) return;
        if (!hhhPlayer.isPlaying()) return;
        if (player.getInventory().getItemInMainHand().getType() != Material.DIAMOND_HOE) return;

        Team team = hhhPlayer.getTeam();
        if (team == null) return;
        DyeColor color = team.getColor().color();
        Material wool = team.getColor().woolType();

        Block block = event.getClickedBlock();

        if (block == null) return;
        Material type = block.getType();

        if (type != Material.GRASS_BLOCK && type != Material.MYCELIUM) return;

        event.setCancelled(true);

        if (hhhPlayer.isWaiting()) return;

        if (hhhPlayer.getScore() == 0) {
            block.setType(wool);
            hhhPlayer.setScore(hhhPlayer.getScore() + 1);
            team.setScore(team.getScore() + 1);
            return;
        }
        List<Material> surroundingBlocks = new ArrayList<>();
        BlockFace[] directions = {
                BlockFace.NORTH,
                BlockFace.EAST,
                BlockFace.SOUTH,
                BlockFace.WEST
        };

        for (BlockFace direction : directions) {
            surroundingBlocks.add(block.getRelative(direction).getType());
            surroundingBlocks.add(block.getRelative(BlockFace.DOWN).getRelative(direction).getType());
            surroundingBlocks.add(block.getRelative(BlockFace.UP).getRelative(direction).getType());
        }

        if (!surroundingBlocks.contains(wool)) {
            player.sendMessage("§cYou must hoe next to " + color.name() + " wool!");
            return;
        }
        block.setType(wool);
        hhhPlayer.setScore(hhhPlayer.getScore() + 1);
        team.setScore(team.getScore() + 1);
    }
}
