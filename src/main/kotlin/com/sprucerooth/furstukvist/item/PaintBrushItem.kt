package com.sprucerooth.furstukvist.item

import com.google.common.collect.ImmutableMap
import com.sprucerooth.furstukvist.Pigment
import com.sprucerooth.furstukvist.block.Blocks
import com.sprucerooth.furstukvist.block.SawedLogBlock
import com.sprucerooth.furstukvist.block.logslab.SawedLogSlab
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.PillarBlock
import net.minecraft.client.item.TooltipContext
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUsageContext
import net.minecraft.sound.SoundEvents
import net.minecraft.state.property.Properties
import net.minecraft.text.Text
import net.minecraft.text.TranslatableText
import net.minecraft.util.ActionResult
import net.minecraft.world.World
import org.apache.logging.log4j.LogManager

val PAINTED_LOGS: ImmutableMap<Pigment, Block> = ImmutableMap.of(Pigment.RED, Blocks.SAWED_SPRUCE_LOG_RED)
val PAINTED_LOG_SLABS: ImmutableMap<Pigment, Block> = ImmutableMap.of(Pigment.RED, Blocks.SAWED_SPRUCE_LOG_SLAB_RED)

class PaintBrushItem(settings: Settings, private val color: Pigment? = Pigment.NONE) : Item(settings) {

    val LOGGER = LogManager.getLogger()

    override fun useOnBlock(context: ItemUsageContext): ActionResult {
        val world = context.world
        val blockPos = context.blockPos
        val blockState = world.getBlockState(blockPos)

        if (color != Pigment.NONE && isPaintable(blockState.block)) {
            context.player?.playSound(SoundEvents.BLOCK_WOOL_BREAK, 1.0F, 1.0F)
            if (!world.isClient) {
                val newBlockState = getBlock(blockState)
                world.setBlockState(
                    blockPos,
                    newBlockState,
                    Block.NOTIFY_ALL or Block.REDRAW_ON_MAIN_THREAD
                )
            }
            return ActionResult.SUCCESS
        } else return ActionResult.PASS
    }

    override fun appendTooltip(stack: ItemStack, world: World?, tooltip: MutableList<Text>, context: TooltipContext) {
        if (color != Pigment.NONE) tooltip.add(TranslatableText("${translationKey}.tooltip"))
    }

    private fun isPaintable(block: Block) =
        ((block is SawedLogBlock) && block.color != color) || ((block is SawedLogSlab) && block.color != color)

    private fun getBlock(blockState: BlockState): BlockState {
        if (blockState.block is SawedLogBlock) {
            return PAINTED_LOGS[color]!!.defaultState.with(PillarBlock.AXIS, blockState.get(PillarBlock.AXIS))
        } else if (blockState.block is SawedLogSlab) {
            return PAINTED_LOG_SLABS[color]!!.defaultState.with(
                Properties.HORIZONTAL_FACING,
                blockState.get(Properties.HORIZONTAL_FACING)
            )
        } else {
            throw Exception()
        }
    }
}