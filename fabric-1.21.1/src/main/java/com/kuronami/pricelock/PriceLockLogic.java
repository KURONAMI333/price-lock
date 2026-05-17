package com.kuronami.pricelock;

import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.item.trading.MerchantOffer;

/**
 * Shared demand-reset logic for the Fabric build.
 *
 * <p>Clears the demand-based price markup ({@code specialPriceDiff}) from
 * every offer of an {@link AbstractVillager} so "demand" inflation never
 * accumulates. Identical effect to the NeoForge/Forge {@code reset(...)}.
 * Used by both the trade mixin and the open-villager interact callback.
 */
public final class PriceLockLogic {

    public static void reset(AbstractVillager villager) {
        if (villager == null || !PriceLockConfig.FREEZE_PRICES.get()) {
            return;
        }
        for (MerchantOffer offer : villager.getOffers()) {
            offer.resetSpecialPriceDiff();
        }
    }

    private PriceLockLogic() {}
}
