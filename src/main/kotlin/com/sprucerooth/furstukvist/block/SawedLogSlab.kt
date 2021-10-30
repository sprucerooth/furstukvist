package com.sprucerooth.furstukvist.block

import com.sprucerooth.furstukvist.Pigment
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Material
import net.minecraft.block.ShapeContext
import net.minecraft.item.ItemPlacementContext
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.state.StateManager
import net.minecraft.state.property.EnumProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.StringIdentifiable
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Vec3d
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.floor

private val TYPE = EnumProperty.of("type", LogSlabType::class.java)
private val AXIS = Properties.AXIS

class SawedLogSlab(
    val color: Pigment? = Pigment.NONE,
    settings: Settings? = FabricBlockSettings.of(Material.WOOD).strength(2.0F, 5.0F).sounds(BlockSoundGroup.WOOD)
        .requiresTool().breakByTool(FabricToolTags.AXES)
) : Block(settings) {

    init {
        defaultState = stateManager.defaultState.with(TYPE, LogSlabType.NORTH_FACING).with(AXIS, Direction.Axis.X)
        FlammableBlockRegistry.getDefaultInstance().add(this, 4, 4)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(TYPE).add(AXIS)
    }

    override fun getOutlineShape(
        state: BlockState, world: BlockView, pos: BlockPos, context: ShapeContext
    ): VoxelShape = SHAPES[state.get(TYPE)]!!

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState {
        val targetedBlockState = ctx.world.getBlockState(ctx.blockPos)
        if (targetedBlockState.isOf(this)) return targetedBlockState.with(TYPE, LogSlabType.DOUBLE)

        val slabType = getSlabPositionFrom(ctx.playerFacing, ctx.hitPos)
        return defaultState.with(TYPE, slabType).with(AXIS, ctx.playerFacing.axis)
    }

    // state - target block state
    override fun canReplace(state: BlockState, context: ItemPlacementContext): Boolean {
        val itemStack = context.stack
        val slabType = state.get(TYPE)

        // TODO - context.canReplaceExisting() är med i SlabBlock.canReplace(). Ska man?
        if (slabType != LogSlabType.DOUBLE && itemStack.item == this.asItem()) {
            if (context.side.axis == Direction.Axis.Z && abs(context.hitPos.z - floor(context.hitPos.z)) == 0.5) return true
            else if (context.side.axis == Direction.Axis.X && abs(context.hitPos.x - floor(context.hitPos.x)) == 0.5) return true
            if (context.side == Direction.UP && context.hitPos.y.toInt() == context.blockPos.y) return true
            if (context.side == Direction.DOWN && context.hitPos.y.toInt() != context.blockPos.y) return true
            // TODO - kanske. när man placerar en slab på samma sätt som för up och down men på Sidan av ett annat fullt block.
        }

        return super.canReplace(state, context)
    }

    override fun getOpacity(state: BlockState, world: BlockView, pos: BlockPos): Int {
        return world.maxLightLevel
    }

    override fun hasSidedTransparency(state: BlockState): Boolean {
        return state.get(TYPE) != LogSlabType.DOUBLE
    }

    override fun isTranslucent(state: BlockState?, world: BlockView?, pos: BlockPos?): Boolean {
        return false
    }

}

private fun getSlabPositionFrom(playerFacingDirection: Direction, hitPos: Vec3d): LogSlabType {
    return when (playerFacingDirection) {
        Direction.NORTH -> if (floor(hitPos.z - 0.5) == floor(hitPos.z)) LogSlabType.SOUTH_FACING else LogSlabType.NORTH_FACING
        Direction.SOUTH -> if (ceil(hitPos.z + 0.5) == ceil(hitPos.z)) LogSlabType.NORTH_FACING else LogSlabType.SOUTH_FACING
        Direction.WEST -> if (floor(hitPos.x - 0.5) == floor(hitPos.x)) LogSlabType.EAST_FACING else LogSlabType.WEST_FACING
        Direction.EAST -> if (ceil(hitPos.x + 0.5) == ceil(hitPos.x)) LogSlabType.WEST_FACING else LogSlabType.EAST_FACING
        else -> LogSlabType.NORTH_FACING
    }
}

private val SHAPES =
    mapOf<LogSlabType, VoxelShape>(
        Pair(LogSlabType.NORTH_FACING, VoxelShapes.cuboid(0.0, 0.0, 0.0, 1.0, 1.0, 0.5)),
        Pair(LogSlabType.SOUTH_FACING, VoxelShapes.cuboid(0.0, 0.0, 0.5, 1.0, 1.0, 1.0)),
        Pair(LogSlabType.EAST_FACING, VoxelShapes.cuboid(0.5, 0.0, 0.0, 1.0, 1.0, 1.0)),
        Pair(LogSlabType.WEST_FACING, VoxelShapes.cuboid(0.0, 0.0, 0.0, 0.5, 1.0, 1.0)),
        Pair(LogSlabType.DOUBLE, VoxelShapes.fullCube())
    )

private enum class LogSlabType : StringIdentifiable {
    NORTH_FACING,
    SOUTH_FACING,
    WEST_FACING,
    EAST_FACING,
    DOUBLE;

    override fun asString(): String {
        return name.lowercase() // Lowercase important for block properties!
    }
}