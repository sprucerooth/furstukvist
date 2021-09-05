package com.sprucerooth.furstukvist.block.logslab

import com.sprucerooth.furstukvist.Pigment
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.*
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Vec3d
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import kotlin.math.ceil
import kotlin.math.floor

class SawedLogSlab(
    val color: Pigment? = null,
    settings: Settings? = FabricBlockSettings.of(Material.WOOD)
) : HorizontalFacingBlock(settings) {

    init {
        defaultState = stateManager.defaultState.with(Properties.HORIZONTAL_FACING, Direction.NORTH)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(Properties.HORIZONTAL_FACING)
    }

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        return when (state.get(FACING)) {
            Direction.NORTH -> VoxelShapes.cuboid(0.0, 0.0, 0.0, 1.0, 1.0, 0.5)
            Direction.SOUTH -> VoxelShapes.cuboid(0.0, 0.0, 0.5, 1.0, 1.0, 1.0)
            Direction.EAST -> VoxelShapes.cuboid(0.5, 0.0, 0.0, 1.0, 1.0, 1.0)
            Direction.WEST -> VoxelShapes.cuboid(0.0, 0.0, 0.0, 0.5, 1.0, 1.0)
            else -> VoxelShapes.fullCube()
        }
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState {
        val blockFacingDirection =
            if (isPlayerFacingNearestHalfOfBlock(
                    ctx.playerFacing,
                    ctx.hitPos
                )
            ) ctx.playerFacing.opposite else ctx.playerFacing

        return defaultState.with(Properties.HORIZONTAL_FACING, blockFacingDirection)
    }
}

private fun isPlayerFacingNearestHalfOfBlock(dir: Direction, hitPos: Vec3d): Boolean {
    return when (dir) {
        Direction.NORTH -> floor(hitPos.z - 0.5) == floor(hitPos.z)
        Direction.SOUTH -> ceil(hitPos.z + 0.5) == ceil(hitPos.z)
        Direction.WEST -> floor(hitPos.x - 0.5) == floor(hitPos.x)
        Direction.EAST -> ceil(hitPos.x + 0.5) == ceil(hitPos.x)
        else -> true
    }
}
