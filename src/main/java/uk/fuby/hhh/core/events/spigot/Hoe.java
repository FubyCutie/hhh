package uk.fuby.hhh.core.events.spigot;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import uk.fuby.hhh.core.HHHPlayer;

import java.util.ArrayList;
import java.util.List;

public class Hoe {

    public static void execute(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        HHHPlayer hhhPlayer = HHHPlayer.getFromPlayer(player);

        if (hhhPlayer == null) return;
        if (!hhhPlayer.isPlaying()) return;
        if (player.getInventory().getItemInMainHand().getType() != Material.DIAMOND_HOE) return;

        Block block = event.getClickedBlock();

        if (block == null) return;
        Material type = block.getType();

        if (type != Material.GRASS_BLOCK && type != Material.MYCELIUM) return;

        event.setCancelled(true);

        if (hhhPlayer.isWaiting()) return;

        if (hhhPlayer.getScore() == 0) {
            block.setType(Material.RED_WOOL);
            hhhPlayer.setScore(hhhPlayer.getScore() + 1);
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
        }
        if (!surroundingBlocks.contains(Material.RED_WOOL)) {
            player.sendMessage("§cYou must hoe next to red wool!");
            return;
        }
        block.setType(Material.RED_WOOL);
        hhhPlayer.setScore(hhhPlayer.getScore() + 1);
    }
}
