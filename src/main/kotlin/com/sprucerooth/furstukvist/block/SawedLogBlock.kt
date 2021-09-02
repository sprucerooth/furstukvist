package com.sprucerooth.furstukvist.block

import com.sprucerooth.furstukvist.Pigment
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Material
import net.minecraft.block.PillarBlock

class SawedLogBlock(val color: Pigment? = null, settings: Settings? = FabricBlockSettings.of(Material.WOOD)) :
    PillarBlock(settings)