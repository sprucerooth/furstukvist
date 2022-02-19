package com.sprucerooth.furstukvist.item

import com.sprucerooth.furstukvist.FurstuKvist
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier

object ItemGroups {
    val GROUP_GENERAL: ItemGroup =
        FabricItemGroupBuilder.build(Identifier(FurstuKvist.MOD_ID, "general")) { ItemStack(Items.PAINT_BRUSH_RED) }
}