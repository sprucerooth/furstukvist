package com.sprucerooth.furstukvist.block

import com.sprucerooth.furstukvist.util.RegistryUtil
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Material
import net.minecraft.block.PillarBlock

@Suppress("UNUSED")
object Blocks {

    val SAWED_SPRUCE_LOG: Block =
        RegistryUtil.register("sawed_spruce_log", PillarBlock(FabricBlockSettings.of(Material.WOOD)))

    val SAWED_SPRUCE_LOG_RED: Block =
        RegistryUtil.register("sawed_spruce_log_red", PillarBlock(FabricBlockSettings.of(Material.WOOD)))
}