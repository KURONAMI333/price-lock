package com.kuronami.pricelock;

import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraftforge.fml.config.ModConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Price Lock — entry point (Fabric 1.20.1).
 *
 * <p>Two coverage paths, mirroring the NeoForge build:
 * <ul>
 *   <li><b>On trade</b> — Fabric has no TradeWithVillagerEvent, so
 *       {@code mixin/AbstractVillagerMixin} hooks
 *       {@code AbstractVillager#notifyTrade} and clears the markup.</li>
 *   <li><b>On opening the trade GUI</b> — {@link UseEntityCallback} on an
 *       {@link AbstractVillager} clears the markup <em>before</em> the
 *       player sees prices (the Fabric API equivalent of NeoForge's
 *       {@code PlayerInteractEvent.EntityInteract}).</li>
 * </ul>
 *
 * <p>The single config toggle is registered through Forge Config API Port
 * (FCAP) so {@link PriceLockConfig} (a {@code ForgeConfigSpec}) runs
 * unchanged on Fabric — same pattern as the proven compass-to-map Fabric
 * 1.20.1. Note: FCAP {@code v8.0.3-1.20.1-Fabric} exposes the registry as
 * {@code fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry}
 * (the 1.21.1 FCAP renamed it to {@code fabric.api.forge.v4}).
 */
public class PriceLockFabric implements ModInitializer {

    public static final String MOD_ID = "pricelock";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        // ─── Config: registered via FCAP (server config, GUI-editable) ──
        ForgeConfigRegistry.INSTANCE.register(MOD_ID, ModConfig.Type.SERVER, PriceLockConfig.SPEC);

        // ─── Open-villager hook (main-hand only; server-side only) ──────
        UseEntityCallback.EVENT.register((player, level, hand, entity, hitResult) -> {
            if (!level.isClientSide()
                    && hand == InteractionHand.MAIN_HAND
                    && entity instanceof AbstractVillager villager) {
                PriceLockLogic.reset(villager);
            }
            return InteractionResult.PASS; // never consume — let vanilla open the GUI
        });

        LOGGER.info("Price Lock ready — ON by default: villager demand price "
            + "markups are cleared on trade & on opening a villager. "
            + "Set freezePrices=false in serverconfig/pricelock-server.toml to disable.");
    }
}
