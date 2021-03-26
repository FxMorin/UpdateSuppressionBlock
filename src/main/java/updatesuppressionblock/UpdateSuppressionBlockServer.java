package updatesuppressionblock;

import carpet.CarpetExtension;
import carpet.CarpetServer;

public class UpdateSuppressionBlockServer implements CarpetExtension
{

    public static void noop() {}
    static
    {
        CarpetServer.manageExtension(new UpdateSuppressionBlockServer());
        System.out.print("The mod has loaded!!!!!!!!!!!!!!!!!!");
    }

    @Override
    public void onGameStarted()
    {
        System.out.print("The game has started!!!!!!!!!!!!!!!!!!");
        // let's /carpet handle our few simple settings
        CarpetServer.settingsManager.parseSettingsClass(UpdateSuppressionBlockSettings.class);
    }
}
