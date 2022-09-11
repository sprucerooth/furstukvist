package com.sprucerooth.furstukvist.register.block

import com.sprucerooth.furstukvist.Pigment
import com.sprucerooth.furstukvist.block.SawedLogBlock
import com.sprucerooth.furstukvist.block.SawedLogSlab
import com.sprucerooth.furstukvist.block.TestBlock
import com.sprucerooth.furstukvist.register.RegistryUtil

object Blocks {

    val SAWED_SPRUCE_LOG = RegistryUtil.register("sawed_spruce_log", SawedLogBlock())

    val SAWED_SPRUCE_LOG_RED =
        RegistryUtil.register("sawed_spruce_log_red", SawedLogBlock(Pigment.RED))

    val SAWED_SPRUCE_LOG_SLAB = RegistryUtil.register("sawed_spruce_log_slab", SawedLogSlab())

    val SAWED_SPRUCE_LOG_SLAB_RED =
        RegistryUtil.register("sawed_spruce_log_slab_red", SawedLogSlab(Pigment.RED))

    val TEST_BLOCK = RegistryUtil.register("test_block", TestBlock())
}