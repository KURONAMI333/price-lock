# Price Lock

> Stops villager trade prices from creeping up. The deal you found stays the deal you get — no more 60-emerald mending books on a long-lived world.

Recurring r/feedthebeast request (70+↑): "is there a mod for 1.21.1 that prevents villager price increases?" Long-term worlds make trading tedious as demand-based prices balloon. The Paper plugins that do this don't run on vanilla NeoForge servers.

- 💱 Clears the demand-based markup so prices never inflate
- 🔁 Resets on every trade **and** when you open a villager (prices flat the moment the GUI opens)
- ⚙️ One server-config toggle (`freezePrices`, default on), editable in the Mod Config GUI
- 🧩 No commands, server-side

## What it does / Usage

Install and it just works — the demand markup is cleared after every trade and on opening a villager. Toggle via `freezePrices` in the server config / Mod Config GUI.

## Supported loaders / versions

| Minecraft | NeoForge | Forge | Fabric |
|---|:---:|:---:|:---:|
| 1.21.1 | ✅ | ✅ | ✅ |
| 1.20.1 | — | ✅ | ✅ |

Ships for NeoForge / Forge / Fabric on Minecraft 1.21.1, and Forge / Fabric on Minecraft 1.20.1 (NeoForge has no 1.20.1 build). Server-side, dependency-free, identical behaviour on every loader.

## Dependencies

None.

## Compatibility & scope

Works on **vanilla villagers, wandering traders, and modded villagers that use the vanilla trade system** (More Villagers, trade datapacks, most trade-content mods — anything extending `AbstractVillager`). Fully-custom merchant entities with their own pricing are out of scope — but they don't have vanilla's demand inflation in the first place, so there's nothing to fix there. No mixin.

## Known limitations

v0.1 clears the markup on trade and on GUI-open (zero mixin, certain to work). A full freeze of the internal demand counter would need a mixin into `MerchantOffer.updateDemand` — intentionally out of v0.1.

## Install

1. Install your mod loader — NeoForge, Forge, or Fabric — for Minecraft 1.21.1 or 1.20.1.
2. Drop `pricelock-0.1.0.jar` into `mods/`. Server-side.

- Minecraft 1.21.1 · NeoForge · JDK 21

## License

MIT — modpack inclusion welcome, no credit required.

Author: KURONAMI
