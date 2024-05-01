package mod.mc2hep;

import mod.mc2hep.block.Mc2HEPBlock;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MC2HEP implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("mc2hep");

	@Override
	public void onInitialize() {
		Mc2HEPBlock.registerBlocks();
	}
}