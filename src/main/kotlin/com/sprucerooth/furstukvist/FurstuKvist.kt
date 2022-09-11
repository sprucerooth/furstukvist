package com.sprucerooth.furstukvist

import com.sprucerooth.furstukvist.loot.LootTables
import com.sprucerooth.furstukvist.register.Items
import com.sprucerooth.furstukvist.register.block.BlockItems
import com.sprucerooth.furstukvist.register.block.Blocks
import net.fabricmc.api.ModInitializer

object FurstuKvist : ModInitializer {
    const val MOD_ID = "furstukvist"

    override fun onInitialize() {
        Blocks
        BlockItems
        Items
        LootTables
    }
}