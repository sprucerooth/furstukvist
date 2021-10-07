package com.sprucerooth.furstukvist.block

import com.sprucerooth.furstukvist.Pigment
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Material
import net.minecraft.block.PillarBlock
import net.minecraft.item.ItemPlacementContext
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.state.StateManager
import net.minecraft.state.property.Properties
import net.minecraft.util.math.Direction

private val FACING = Properties.HORIZONTAL_FACING

class SawedLogBlock(
    val color: Pigment? = Pigment.NONE,
    settings: Settings = FabricBlockSettings.of(Material.WOOD).strength(4.0F).sounds(
        BlockSoundGroup.WOOD
    ).requiresTool().breakByTool(FabricToolTags.AXES)
) : PillarBlock(settings) {
    init {
        FlammableBlockRegistry.getDefaultInstance().add(this, 2, 2)
        defaultState = stateManager.defaultState.with(FACING, Direction.NORTH)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(FACING)
        super.appendProperties(builder)
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState? {
        return super.getPlacementState(ctx)!!.with(FACING, ctx.playerFacing)
    }
}