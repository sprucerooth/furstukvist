package com.sprucerooth.furstukvist.item

import com.sprucerooth.furstukvist.Pigment
import com.sprucerooth.furstukvist.util.RegistryUtil
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Item

@Suppress("UNUSED")
object Items {

    val PAINT_BRUSH: Item = RegistryUtil.register(
        "paint_brush", PaintBrushItem(FabricItemSettings().group(ItemGroups.GROUP_GENERAL))
    )
    val PAINT_BRUSH_RED: Item = RegistryUtil.register(
        "paint_brush_red", PaintBrushItem(FabricItemSettings().group(ItemGroups.GROUP_GENERAL), Pigment.RED)
    )
    val PAINT_BUCKET_RED: Item = RegistryUtil.register(
        "paint_bucket_red", PaintBucketItem(FabricItemSettings().group(ItemGroups.GROUP_GENERAL), "red"),
    )
    val HOG_BRISTLES: Item = RegistryUtil.register(
        "hog_bristles", Item(FabricItemSettings().group(ItemGroups.GROUP_GENERAL)),
    )

}