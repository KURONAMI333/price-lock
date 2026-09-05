Stops villager trade prices from creeping up. The deal you found stays the deal you get — no more 60-emerald mending books on a long-lived world.

On a long-running world, villager prices inflate as demand-based markup accumulates. The Paper plugins that fix this don't run on vanilla NeoForge/Fabric servers; Price Lock does the same thing as a mod.

It clears the demand markup after every trade and again whenever you open a villager, so prices are flat the moment the GUI opens. One server-config toggle, `freezePrices` (default on), editable in the Mod Config GUI. No commands.

It works on vanilla villagers, wandering traders, and modded villagers that use the vanilla trade system (More Villagers, trade datapacks, anything extending `AbstractVillager`). Fully-custom merchant entities with their own pricing are out of scope — they don't have vanilla's demand inflation to begin with. No mixin.

v0.1 clears the markup on trade and on GUI-open (the zero-mixin approach that's certain to work); freezing the internal demand counter outright would need a mixin and is intentionally out of scope for now.

Bugs and questions: comment on the CurseForge page, or DM @kuronami333 on X.

All Rights Reserved. Modpack inclusion is allowed without permission or credit. Source: https://github.com/KURONAMI333/price-lock
