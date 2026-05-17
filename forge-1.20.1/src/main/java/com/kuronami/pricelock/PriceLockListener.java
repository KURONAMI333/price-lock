package com.kuronami.pricelock;

import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

/**
 * Clears the demand-based price markup ({@code specialPriceDiff}) from a
 * villager's offers so "demand" inflation never accumulates.
 *
 * <p><b>Forge 1.20.1 (Forge 47) note:</b> Forge 47 does NOT ship a
 * {@code TradeWithVillagerEvent} (it was added later, around Forge 52 /
 * NeoForge). So this build keeps <em>only</em> the {@code EntityInteract}
 * hook — the same fallback path the NeoForge source already carries.
 * Behaviour is preserved: the markup is cleared <em>before</em> the
 * player even sees prices, so every freshly-opened villager shows base
 * prices and demand inflation never sticks across sessions. (The
 * post-trade clear is redundant once every open already resets it.)
 *
 * <p>Covers <em>any</em> {@link AbstractVillager} (vanilla Villager +
 * WanderingTrader, and modded villagers that extend AbstractVillager and
 * use the vanilla trade path — e.g. More Villagers and most trade-content
 * mods / datapacks).
 *
 * <p>Honest scope: mods with fully custom merchant entities that do NOT
 * extend {@code AbstractVillager} or bypass vanilla {@code MerchantOffer}
 * are out of scope — but those don't have vanilla's demand-inflation
 * mechanic in the first place, so there is nothing to fix there.
 */
public class PriceLockListener {

    @SubscribeEvent
    public void onInteract(PlayerInteractEvent.EntityInteract event) {
        if (!PriceLockConfig.FREEZE_PRICES.get()
                || event.getLevel().isClientSide()
                || event.getHand() != net.minecraft.world.InteractionHand.MAIN_HAND) {
            return; // main-hand only: avoid the redundant off-hand re-trigger
        }
        if (event.getTarget() instanceof AbstractVillager villager) {
            reset(villager);
        }
    }

    private void reset(AbstractVillager villager) {
        if (villager == null) {
            return;
        }
        for (MerchantOffer offer : villager.getOffers()) {
            offer.resetSpecialPriceDiff();
        }
    }
}
