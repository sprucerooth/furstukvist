package com.sprucerooth.furstukvist.block.misc

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags
import net.minecraft.block.DoorBlock
import net.minecraft.block.Material
import net.minecraft.sound.BlockSoundGroup

class YellowDoor(
    settings: Settings = FabricBlockSettings.of(Material.WOOD).strength(2.0F, 5.0F)
        .sounds(BlockSoundGroup.WOOD).requiresTool().breakByTool(FabricToolTags.AXES)
) : DoorBlock(settings)