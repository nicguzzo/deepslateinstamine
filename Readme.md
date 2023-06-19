# Deepslate, Instamineable and Renewable deepslate
 
Become my [![Patreon](https://i.imgur.com/r5hfGFc.png)](https://www.patreon.com/nicguzzo "become my patron")

Get support on [![Discord](https://i.imgur.com/NyP9y98.jpg)](https://discord.gg/T9VbYBNYCR "")

---------------------- 

## Features

### Breaking Deepslate

- Break deepslate instantly using a Netherite pickaxe. 
- The pickaxe must have the efficiency V enchantment and the player has to be under the effect of a Haste II beacon.

### Make more Deepslate

- Renewable deepslate, lava above water like a stone generator but at lower Y levels, y=11 by default (configurable).

### Insta-Break Logs

- Break Logs instantly using a Netherite axe with efficiency V and haste 2. (can be disabled in config file) Version &gt;=1.3

### Insta-Break Other blocks with other tools

- As of version 2.4 you can add blocks and tools for instamining to the config file

---------------------- 
  
## Changelog
 
### Version 2.0 

- Needs architectury-api (![Architectury](https://www.curseforge.com/minecraft/mc-mods/architectury-api)]
- Configurable list of blocks to instamine
.minecraft/config/deepslate_instamine.json
```"pickaxe_instamine_blocks": [
    "minecraft:deepslate",
    "minecraft:cobblestone",
    "minecraft:end_stone"
  ],
  "pickaxes_that_can_instamine": [
    "minecraft:netherite_pickaxe"
  ],
  "axes_that_can_instamine": [
    "minecraft:netherite_axe"
  ],
  "axe_instamine_blocks": []
```

### Version 2.1 changelog

configurable list of tools that can instamine

### Version 2.4

- Added cobbled deepslate generation, same as cobblestone
- Added calcite generation, same as basalt
- Blocks can now be added for axe instamining

# Important: this mod is requiered on both Client AND Server to work properly.