package com.sprucerooth.furstukvist

import com.sprucerooth.furstukvist.block.Blocks
import com.sprucerooth.furstukvist.item.BlockItems
import com.sprucerooth.furstukvist.item.Items
import com.sprucerooth.furstukvist.loot.LootTables
import net.fabricmc.api.ModInitializer

@Suppress("UNUSED")
object FurstuKvist : ModInitializer {
    const val MOD_ID = "furstukvist"

    override fun onInitialize() {
        Blocks
        BlockItems
        Items
        LootTables
    }
}