package com.sprucerooth.furstukvist.block

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Material
import net.minecraft.block.ShapeContext
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView

class TestBlock(
    settings: Settings = FabricBlockSettings.of(Material.WOOD).strength(4.0F).sounds(
        BlockSoundGroup.WOOD
    )
) : Block(settings) {

    override fun getOutlineShape(
        state: BlockState?,
        world: BlockView?,
        pos: BlockPos?,
        context: ShapeContext?
    ): VoxelShape {
        return VoxelShapes.cuboid(0.5, 0.0, 0.0, 1.0, 1.0, 1.0)
    }
    
    override fun isShapeFullCube(state: BlockState?, world: BlockView?, pos: BlockPos?): Boolean {
        return super.isShapeFullCube(state, world, pos)
    }

    override fun getOpacity(state: BlockState?, world: BlockView?, pos: BlockPos?): Int {
        return 0
    }

    override fun isTranslucent(state: BlockState?, world: BlockView?, pos: BlockPos?): Boolean {
        return super.isTranslucent(state, world, pos)
    }

    override fun hasSidedTransparency(state: BlockState?): Boolean {
        return super.hasSidedTransparency(state)
    }

    override fun isSideInvisible(state: BlockState?, stateFrom: BlockState?, direction: Direction?): Boolean {
        return super.isSideInvisible(state, stateFrom, direction)
    }

    override fun getAmbientOcclusionLightLevel(state: BlockState?, world: BlockView?, pos: BlockPos?): Float {
        return super.getAmbientOcclusionLightLevel(state, world, pos)
    }
}