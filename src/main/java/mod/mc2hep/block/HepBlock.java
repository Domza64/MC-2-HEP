package mod.mc2hep.block;

import mod.mc2hep.MC2HEP;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

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
            try {
                posaljiKomandu(world.isReceivingRedstonePower(pos));
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void posaljiKomandu(boolean turnOn) throws IOException, InterruptedException {

        if (turnOn && MC2HEP.ledOn == 0) {
            LOGGER.info("Transferring redstone energy to real world");
            MC2HEP.ledOn = 1;
            MC2HEP.sp.getOutputStream().write('1');
            MC2HEP.sp.getOutputStream().flush();
        }
        else if (!turnOn && MC2HEP.ledOn == 1) {
            LOGGER.info("Stopping redstone energy transfer to real world");
            MC2HEP.ledOn = 0;
            MC2HEP.sp.getOutputStream().write('0');
            MC2HEP.sp.getOutputStream().flush();
        }
        else {
            LOGGER.info("WFT");
        }
    }
}
