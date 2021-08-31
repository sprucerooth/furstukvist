package com.sprucerooth.furstukvist.item

import com.google.common.collect.ImmutableMap
import com.sprucerooth.furstukvist.Pigment
import com.sprucerooth.furstukvist.block.Blocks
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.PillarBlock
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUsageContext
import net.minecraft.sound.SoundEvents
import net.minecraft.text.Text
import net.minecraft.text.TranslatableText
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

val paintedLogs = ImmutableMap.Builder<Pigment, Block>().put(Pigment.RED, Blocks.SAWED_SPRUCE_LOG_RED).build()

class PaintBrushItem(settings: Settings, val color: Pigment? = null) : Item(settings) {

    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        user.playSound(SoundEvents.BLOCK_COPPER_BREAK, 1.0F, 1.0F)


        return TypedActionResult.success(user.getStackInHand(hand))
    }

    override fun useOnBlock(context: ItemUsageContext): ActionResult {
        if (!context.world.isClient) {
            val blockState: BlockState = context.world.getBlockState(context.blockPos)

            if (blockState.block.equals(Blocks.SAWED_SPRUCE_LOG)) {
                val paintedBlock = paintedLogs[color] //TODO: non null assertion from map
                val newBlockState = paintedBlock?.defaultState?.with(PillarBlock.AXIS, blockState.get(PillarBlock.AXIS))
                context.world.setBlockState(context.blockPos, newBlockState)
                return ActionResult.SUCCESS
            }
        }
        context.player?.playSound(SoundEvents.BLOCK_BASALT_BREAK, 1.0F, 1.0F)
        /*if (!context.world.isClient) {
            if (color.equals("red")) {
                context.world.setBlockState(context.blockPos, Blocks.SAWED_SPRUCE_LOG_RED.defaultState)
            }
        }*/
        return ActionResult.CONSUME
    }

    override fun appendTooltip(stack: ItemStack, world: World?, tooltip: MutableList<Text>, context: TooltipContext) {
        if (color != null) tooltip.add(TranslatableText("${translationKey}.tooltip"))
    }
}