package com.sprucerooth.furstukvist.block

import com.sprucerooth.furstukvist.Pigment
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags
import net.minecraft.block.Material
import net.minecraft.block.PillarBlock
import net.minecraft.sound.BlockSoundGroup

class SawedLogBlock(
    val color: Pigment? = null, settings: Settings = FabricBlockSettings.of(Material.WOOD).strength(4.0F).sounds(
        BlockSoundGroup.WOOD
    ).requiresTool().breakByTool(FabricToolTags.AXES)
) :
    PillarBlock(settings) {
    init {
        FlammableBlockRegistry.getDefaultInstance().add(this, 2, 2)
    }
}