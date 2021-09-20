package com.sprucerooth.furstukvist.block

import com.sprucerooth.furstukvist.Pigment
import com.sprucerooth.furstukvist.block.logslab.SawedLogSlab
import com.sprucerooth.furstukvist.util.RegistryUtil
import net.minecraft.block.Block

@Suppress("UNUSED")
object Blocks {

    val SAWED_SPRUCE_LOG: Block =
        RegistryUtil.register("sawed_spruce_log", SawedLogBlock())

    val SAWED_SPRUCE_LOG_RED: Block =
        RegistryUtil.register("sawed_spruce_log_red", SawedLogBlock(Pigment.RED))

    val SAWED_SPRUCE_LOG_SLAB: Block =
        RegistryUtil.register("sawed_spruce_log_slab", SawedLogSlab())

    val SAWED_SPRUCE_LOG_SLAB_RED: Block =
        RegistryUtil.register("sawed_spruce_log_slab_red", SawedLogSlab(Pigment.RED))
}