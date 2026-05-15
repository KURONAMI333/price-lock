package com.kuronami.pricelock;

import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.entity.player.TradeWithVillagerEvent;

/**
 * Clears the demand-based price markup ({@code specialPriceDiff}) from a
 * villager's offers so "demand" inflation never accumulates.
 *
 * <p>Two hooks for broad coverage of <em>any</em> {@link AbstractVillager}
 * (vanilla Villager + WanderingTrader, and modded villagers that extend
 * AbstractVillager and use the vanilla trade path — e.g. More Villagers
 * and most trade-content mods / datapacks):
 * <ul>
 *   <li><b>On trade</b> ({@code TradeWithVillagerEvent}) — clears markup
 *       right after a trade so it never sticks.</li>
 *   <li><b>On opening the trade GUI</b> ({@code EntityInteract} with an
 *       AbstractVillager) — clears markup <em>before</em> the player even
 *       sees prices, so a freshly-opened villager already shows base
 *       prices.</li>
 * </ul>
 *
 * <p>Honest scope: mods with fully custom merchant entities that do NOT
 * extend {@code AbstractVillager} or bypass vanilla {@code MerchantOffer}
 * are out of scope — but those don't have vanilla's demand-inflation
 * mechanic in the first place, so there is nothing to fix there. Stated
 * plainly in the store description.
 */
public class PriceLockListener {

    @SubscribeEvent
    public void onTrade(TradeWithVillagerEvent event) {
        if (!PriceLockConfig.FREEZE_PRICES.get()) {
            return;
        }
        reset(event.getAbstractVillager());
    }

    @SubscribeEvent
    public void onInteract(PlayerInteractEvent.EntityInteract event) {
        if (!PriceLockConfig.FREEZE_PRICES.get()
                || event.getLevel().isClientSide()) {
            return;
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
