# Price Lock

> Stops villager trade prices from creeping up. The deal you found stays the deal you get — no more 60-emerald mending books on a long-lived world.

## What it does

Minecraft raises trade prices based on "demand" as you trade. Price Lock clears that demand-based markup:

- **after every trade**, and
- **whenever you open a villager** (so prices are already flat the moment the GUI opens).

Install it and it just works. One server-config toggle (`freezePrices`, default on) editable in the standard mod-config GUI. No commands.

## Coverage

Works on **vanilla villagers, wandering traders, and modded villagers that use the vanilla trade system** (More Villagers, trade datapacks, most trade-content mods — anything extending `AbstractVillager`). Fully-custom merchant entities with their own pricing are out of scope — but they don't have vanilla's demand inflation in the first place, so there's nothing to fix there.

## Why

Recurring request (e.g. r/feedthebeast, 70+ upvotes): "is there a mod for 1.21.1 that prevents villager price increases?" — long-term worlds make trading tedious as prices balloon. The Paper plugins that do this don't run on vanilla NeoForge/Fabric servers.

## Install

Drop `pricelock-<version>.jar` into `mods/`. Server-side. No dependencies.

- Minecraft 1.21.1 · NeoForge · JDK 21

## Scope

v0.1 clears the markup on trade and on GUI-open (zero mixin, certain to work). A full freeze of the internal demand counter would need a mixin into `MerchantOffer.updateDemand` — intentionally out of v0.1.

## License

MIT — modpack inclusion welcome, no credit required.

Author: KURONAMI
