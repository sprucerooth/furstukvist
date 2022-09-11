package com.sprucerooth.furstukvist.register

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.BlockItem

object MiscItems {
    val YELLOW_DOOR = RegistryUtil.register(
        "yellow_door",
        BlockItem(Misc.YELLOW_DOOR, FabricItemSettings().group(ItemGroups.GROUP_GENERAL))
    )
}