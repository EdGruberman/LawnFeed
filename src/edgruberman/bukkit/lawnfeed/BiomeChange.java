package edgruberman.bukkit.lawnfeed;

import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;

public class BiomeChange extends BlockPlaceEvent {

    private final Biome from;
    private final Biome to;

    public BiomeChange(final Block block, final Biome from, final Biome to, final Player player) {
        super(block, block.getState(), block, player.getItemInHand(), player, true);
        this.from = from;
        this.to = to;
    }

    public Biome getFrom() {
        return this.from;
    }

    public Biome getTo() {
        return this.to;
    }

}