package com.sprucerooth.furstukvist.register.block

import com.sprucerooth.furstukvist.register.ItemGroups
import com.sprucerooth.furstukvist.register.RegistryUtil
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.registry.FuelRegistry
import net.minecraft.item.BlockItem
import net.minecraft.item.Item

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

    val SAWED_SPRUCE_LOG_SLAB: Item =
        RegistryUtil.register(
            "sawed_spruce_log_slab",
            BlockItem(
                Blocks.SAWED_SPRUCE_LOG_SLAB,
                FabricItemSettings().group(ItemGroups.GROUP_GENERAL)
            )
        )

    val SAWED_SPRUCE_LOG_SLAB_RED: Item =
        RegistryUtil.register(
            "sawed_spruce_log_slab_red",
            BlockItem(
                Blocks.SAWED_SPRUCE_LOG_SLAB_RED,
                FabricItemSettings().group(ItemGroups.GROUP_GENERAL)
            )
        )

    val TEST_BLOCK: Item =
        RegistryUtil.register(
            "test_block",
            BlockItem(Blocks.TEST_BLOCK, FabricItemSettings().group(ItemGroups.GROUP_GENERAL))
        )

    init {
        FuelRegistry.INSTANCE.add(SAWED_SPRUCE_LOG, 600)
        FuelRegistry.INSTANCE.add(SAWED_SPRUCE_LOG_RED, 600)
        FuelRegistry.INSTANCE.add(SAWED_SPRUCE_LOG_SLAB, 300)
        FuelRegistry.INSTANCE.add(SAWED_SPRUCE_LOG_SLAB_RED, 300)
    }
}