package com.kuronami.pricelock.mixin;

import com.kuronami.pricelock.PriceLockLogic;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.item.trading.MerchantOffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Fabric 1.21.1: hook into {@link AbstractVillager#notifyTrade} to clear
 * the demand-based price markup right after a trade — Fabric has no
 * first-class TradeWithVillagerEvent, so we mixin where vanilla itself
 * fires its "trade just happened" callback (same hook point as Trade
 * Diary's {@code AbstractVillagerMixin}).
 *
 * <p>{@code notifyTrade} is called server-side from inside
 * {@code AbstractVillager#processTrade} right after the offer's use count
 * is incremented. Both AbstractVillager subclasses (Villager,
 * WanderingTrader) route through here, so this single mixin covers both —
 * exactly mirroring the NeoForge {@code TradeWithVillagerEvent} hook.
 */
@Mixin(AbstractVillager.class)
public abstract class AbstractVillagerMixin {

    @Inject(method = "notifyTrade", at = @At("HEAD"))
    private void pricelock$onNotifyTrade(MerchantOffer offer, CallbackInfo ci) {
        AbstractVillager self = (AbstractVillager) (Object) this;
        PriceLockLogic.reset(self);
    }
}
