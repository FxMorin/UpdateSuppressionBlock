package updatesuppressionblock.mixins;

import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.crash.CrashException;
import net.minecraft.util.crash.CrashReport;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.TickPriority;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import updatesuppressionblock.UpdateSuppressionBlockSettings;

import java.util.Random;

@Mixin(BarrierBlock.class)
public class BarrierBlockMixin extends Block {

    private boolean shouldPower = false;

    public BarrierBlockMixin(Settings settings) { super(settings); }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return (shouldPower && direction == Direction.DOWN) ? 15 : 0;
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        if (UpdateSuppressionBlockSettings.updateSuppressionBlockSetting != -1) {
            if (fromPos.equals(pos.up())) {
                BlockState stateAbove = world.getBlockState(fromPos);
                if (stateAbove.isOf(Blocks.ACTIVATOR_RAIL) && !stateAbove.get(PoweredRailBlock.POWERED)) {
                    if (UpdateSuppressionBlockSettings.updateSuppressionBlockSetting > 0) {
                        world.getBlockTickScheduler().schedule(pos, this, UpdateSuppressionBlockSettings.updateSuppressionBlockSetting);
                    }
                    throw new StackOverflowError("updateSuppressionBlock");
                }
            }
        }
        super.neighborUpdate(state, world, pos, block, fromPos, notify);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BlockPos posAbove = pos.up();
        BlockState stateAbove = world.getBlockState(posAbove);
        if (stateAbove.isOf(Blocks.ACTIVATOR_RAIL) && !stateAbove.get(PoweredRailBlock.POWERED)) {
            shouldPower = true;
            world.setBlockState(posAbove, stateAbove.with(PoweredRailBlock.POWERED, true), 3);
            shouldPower = false;
        }
    }
}
