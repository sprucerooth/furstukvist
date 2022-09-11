package com.sprucerooth.furstukvist.register

import com.sprucerooth.furstukvist.FurstuKvist
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object RegistryUtil {

    fun <T : Item> register(path: String, item: T): Item {
        return Registry.register(Registry.ITEM, Identifier(FurstuKvist.MOD_ID, path), item)
    }

    fun <T : Block> register(path: String, block: T): T {
        return Registry.register(Registry.BLOCK, Identifier(FurstuKvist.MOD_ID, path), block)
    }
}