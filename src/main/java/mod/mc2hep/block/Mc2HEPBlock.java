package mod.mc2hep.block;

import mod.mc2hep.Constant;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import java.util.HashMap;
import java.util.Map;

public class Mc2HEPBlock {
    public static final Map<Identifier, Block> BLOCKS = new HashMap<>();

    public static final Block HEP_BLOCK =
            register(Constant.HEP_BLOCK, new HepBlock(
                    AbstractBlock.Settings.copy(Blocks.REDSTONE_BLOCK)));

    private static <T extends Block> T register(String id, T block) {
        BLOCKS.put(new Identifier(Constant.MOD_ID, id), block);
        return block;
    }

    public static void registerBlocks() {
        BLOCKS.forEach((identifier, block) -> {
            BlockItem blockItem = new BlockItem(block, new Item.Settings());

            Registry.register(Registries.BLOCK, identifier, block);
            Registry.register(Registries.ITEM, identifier, blockItem);

            ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(entries -> entries.add(blockItem));
        });
    }
}
