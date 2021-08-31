package com.sprucerooth.furstukvist.item

import com.sprucerooth.furstukvist.block.Blocks
import com.sprucerooth.furstukvist.util.RegistryUtil
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.BlockItem
import net.minecraft.item.Item

@Suppress("UNUSED")
object BlockItems {

    val SAWED_SPRUCE_LOG: Item = RegistryUtil.register(
        "sawed_spruce_log",
        BlockItem(
            Blocks.SAWED_SPRUCE_LOG, FabricItemSettings().group(ItemGroups.GROUP_GENERAL)
        )
    )
    val SAWED_SPRUCE_LOG_RED: Item = RegistryUtil.register(
        "sawed_spruce_log_red",
        BlockItem(
            Blocks.SAWED_SPRUCE_LOG_RED, FabricItemSettings().group(ItemGroups.GROUP_GENERAL)
        )
    )

}