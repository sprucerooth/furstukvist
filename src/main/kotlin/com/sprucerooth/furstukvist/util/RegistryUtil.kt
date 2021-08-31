package com.sprucerooth.furstukvist.util

import com.sprucerooth.furstukvist.FurstuKvist
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object RegistryUtil {

    fun register(path: String, item: Item): Item {
        return Registry.register(Registry.ITEM, Identifier(FurstuKvist.MOD_ID, path), item)
    }

    fun register(path: String, block: Block): Block {
        return Registry.register(Registry.BLOCK, Identifier(FurstuKvist.MOD_ID, path), block)
    }
}