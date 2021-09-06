package com.sprucerooth.furstukvist.block.logslab

import com.sprucerooth.furstukvist.Pigment
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
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
        FlammableBlockRegistry.getDefaultInstance().add(this, 4, 4)
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
            if (targetedBlockHalf(
                    ctx.playerFacing,
                    ctx.hitPos
                ) == BlockHalf.NEAREST
            ) ctx.playerFacing.opposite else ctx.playerFacing

        return defaultState.with(Properties.HORIZONTAL_FACING, blockFacingDirection)
    }
}

private fun targetedBlockHalf(dir: Direction, hitPos: Vec3d): BlockHalf {
    return when (dir) {
        Direction.NORTH -> if (floor(hitPos.z - 0.5) == floor(hitPos.z)) BlockHalf.NEAREST else BlockHalf.FARTHEST
        Direction.SOUTH -> if (ceil(hitPos.z + 0.5) == ceil(hitPos.z)) BlockHalf.NEAREST else BlockHalf.FARTHEST
        Direction.WEST -> if (floor(hitPos.x - 0.5) == floor(hitPos.x)) BlockHalf.NEAREST else BlockHalf.FARTHEST
        Direction.EAST -> if (ceil(hitPos.x + 0.5) == ceil(hitPos.x)) BlockHalf.NEAREST else BlockHalf.FARTHEST
        else -> BlockHalf.NEAREST
    }
}

private enum class BlockHalf {
    NEAREST,
    FARTHEST
}