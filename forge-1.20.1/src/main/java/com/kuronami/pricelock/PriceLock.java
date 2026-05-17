package com.kuronami.pricelock;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Price Lock — entry point (Forge 1.20.1).
 *
 * <p>Keeps villager trade prices from creeping up over a long-lived
 * world. One server-config boolean, one interact listener. No mixin, no
 * command, no game object, nothing client-side.
 *
 * <p>Forge 47.x (1.20.1) uses a no-arg {@code @Mod} constructor; the
 * config is registered through {@code ModLoadingContext} and the
 * listener on {@code MinecraftForge.EVENT_BUS}.
 */
@Mod(PriceLock.MOD_ID)
public class PriceLock {

    public static final String MOD_ID = "pricelock";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public PriceLock() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, PriceLockConfig.SPEC);
        LOGGER.info("Price Lock ready — ON by default: villager demand price "
            + "markups are cleared on opening a villager. "
            + "Set freezePrices=false in serverconfig/pricelock-server.toml to disable.");
        MinecraftForge.EVENT_BUS.register(new PriceLockListener());
    }
}
