package edgruberman.bukkit.lawnfeed;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerInteract(final PlayerInteractEvent interaction) {
        if (interaction.getPlayer().getItemInHand().getTypeId() != Material.SEEDS.getId()) return;
        if (interaction.getClickedBlock().getTypeId() != Material.GRASS.getId()) return;
        if (interaction.getClickedBlock().getBiome() != Biome.EXTREME_HILLS) return;
        if (!interaction.getPlayer().hasPermission("lawnfeed.user")) return;

        final BiomeChange change = new BiomeChange(interaction.getClickedBlock(), interaction.getClickedBlock().getBiome(), Biome.FOREST, interaction.getPlayer());
        Bukkit.getPluginManager().callEvent(change);
        if (change.isCancelled() || !change.canBuild()) return;

        final ItemStack inHand = interaction.getPlayer().getItemInHand();
        inHand.setAmount(inHand.getAmount() - 1);
        interaction.getPlayer().setItemInHand(inHand);

        interaction.getClickedBlock().setBiome(Biome.FOREST);
        interaction.getClickedBlock().getWorld().refreshChunk(interaction.getClickedBlock().getChunk().getX(), interaction.getClickedBlock().getChunk().getZ());
    }

}