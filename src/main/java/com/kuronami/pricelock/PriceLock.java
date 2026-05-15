package com.kuronami.pricelock;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Price Lock — entry point.
 *
 * <p>Keeps villager trade prices from creeping up over a long-lived
 * world. One server-config boolean, one trade listener. No mixin, no
 * command, no game object, nothing client-side.
 */
@Mod(PriceLock.MOD_ID)
public class PriceLock {

    public static final String MOD_ID = "pricelock";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public PriceLock(IEventBus modBus, ModContainer container) {
        container.registerConfig(ModConfig.Type.SERVER, PriceLockConfig.SPEC);
        LOGGER.info("Price Lock ready — villager price markups will be cleared on trade.");
        NeoForge.EVENT_BUS.register(new PriceLockListener());
    }
}
