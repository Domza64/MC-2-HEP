package mod.mc2hep.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HepBlock extends Block {
    // TODO - Add blockstate, like LIT, to make sure it only sends signal when to stop sending power when
    //  neighbor update happened because of redstone signal and not some other block update
    //  Make sure to only send one signal in future, currently its sending few signals per when start/stop receiving redstone signal.

    public static final Logger LOGGER = LoggerFactory.getLogger("mc2hep");
    public HepBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (!world.isClient) {
            if (world.isReceivingRedstonePower(pos)) {
                LOGGER.info("Transferring redstone energy to real world");
                // Posalji signal da upali
            }
            else {
                LOGGER.info("Stopping redstone energy transfer to real world");
                // Posalji signal da ugasi
            }
        }
    }
}
