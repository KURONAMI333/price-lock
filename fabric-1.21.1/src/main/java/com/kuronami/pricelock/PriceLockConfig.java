package com.kuronami.pricelock;

import net.minecraftforge.common.ForgeConfigSpec;

/**
 * One toggle, edited in the standard server-config file / mod-config GUI
 * (the owner prefers GUI-editable config over commands). Default on —
 * installing the mod should just work.
 *
 * <p>Fabric build: the config builder API is supplied by Forge Config API
 * Port (FCAP). FCAP {@code v21.1.6-1.21.1-Fabric} exposes the class as
 * {@code net.minecraftforge.common.ForgeConfigSpec} (same as the proven
 * compass-to-map Fabric 1.21.1 reference).
 */
public final class PriceLockConfig {

    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.BooleanValue FREEZE_PRICES;

    static {
        ForgeConfigSpec.Builder b = new ForgeConfigSpec.Builder();
        FREEZE_PRICES = b
            .comment("Clear the demand-based price markup after every villager",
                     "trade so trade prices never creep upward. Default: true.")
            .define("freezePrices", true);
        SPEC = b.build();
    }

    private PriceLockConfig() {}
}
