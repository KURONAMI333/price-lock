package com.kuronami.pricelock;

import net.minecraftforge.common.ForgeConfigSpec;

/**
 * One toggle, edited in the standard server-config file / mod-config GUI
 * (the owner prefers GUI-editable config over commands). Default on —
 * installing the mod should just work.
 *
 * <p>Forge 1.20.1 (Forge 47) exposes the config builder API as
 * {@code net.minecraftforge.common.ForgeConfigSpec} (the pre-rename name
 * — NeoForge later renamed the identical class to {@code ModConfigSpec}).
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
