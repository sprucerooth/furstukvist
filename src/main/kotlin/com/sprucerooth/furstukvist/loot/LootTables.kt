package com.sprucerooth.furstukvist.loot

import com.sprucerooth.furstukvist.register.Items
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback
import net.minecraft.entity.EntityType
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.provider.number.ConstantLootNumberProvider

object LootTables {

    init {
        hoglin()
    }

    private fun hoglin() {
        LootTableLoadingCallback.EVENT.register { _, _, id, table, _ ->
            if (EntityType.HOGLIN.lootTableId.equals(id)) {
                val poolBuilder =
                    FabricLootPoolBuilder.builder().rolls(ConstantLootNumberProvider.create(1.0F))
                        .with(ItemEntry.builder(Items.HOG_BRISTLES))
                table.pool(poolBuilder)
            }
        }
    }
}