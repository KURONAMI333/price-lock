package com.kuronami.pricelock;

import net.neoforged.neoforge.common.ModConfigSpec;

/**
 * One toggle, edited in the standard server-config file / mod-config GUI
 * (the owner prefers GUI-editable config over commands). Default on —
 * installing the mod should just work.
 */
public final class PriceLockConfig {

    public static final ModConfigSpec SPEC;
    public static final ModConfigSpec.BooleanValue FREEZE_PRICES;

    static {
        ModConfigSpec.Builder b = new ModConfigSpec.Builder();
        FREEZE_PRICES = b
            .comment("Clear the demand-based price markup after every villager",
                     "trade so trade prices never creep upward. Default: true.")
            .define("freezePrices", true);
        SPEC = b.build();
    }

    private PriceLockConfig() {}
}
