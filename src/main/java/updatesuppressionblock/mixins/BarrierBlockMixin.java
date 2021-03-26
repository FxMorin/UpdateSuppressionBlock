package updatesuppressionblock.mixins;

import net.minecraft.block.*;
import net.minecraft.util.crash.CrashException;
import net.minecraft.util.crash.CrashReport;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import updatesuppressionblock.UpdateSuppressionBlockSettings;

@Mixin(BarrierBlock.class)
public class BarrierBlockMixin extends Block {

    public BarrierBlockMixin(Settings settings) {
        super(settings);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        if (UpdateSuppressionBlockSettings.updateSuppressionBlock) {
            if (fromPos.equals(pos.up())) {
                BlockState stateAbove = world.getBlockState(fromPos);
                if (stateAbove.isOf(Blocks.ACTIVATOR_RAIL) && !stateAbove.get(PoweredRailBlock.POWERED)) {
                    throw new StackOverflowError();
                }
            }
        }
        super.neighborUpdate(state, world, pos, block, fromPos, notify);
    }
}
