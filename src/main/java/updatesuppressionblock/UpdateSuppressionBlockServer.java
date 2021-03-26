package updatesuppressionblock;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import net.fabricmc.api.ModInitializer;

public class UpdateSuppressionBlockServer implements CarpetExtension, ModInitializer
{

    public static void noop() {}
    static
    {
        CarpetServer.manageExtension(new UpdateSuppressionBlockServer());
    }

    @Override
    public void onGameStarted()
    {
        // let's /carpet handle our few simple settings
        CarpetServer.settingsManager.parseSettingsClass(UpdateSuppressionBlockSettings.class);
    }

    @Override
    public void onInitialize() {
    }
}
