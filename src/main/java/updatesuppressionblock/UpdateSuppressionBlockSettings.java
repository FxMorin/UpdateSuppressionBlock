package updatesuppressionblock;

import carpet.settings.Rule;

import static carpet.settings.RuleCategory.CREATIVE;

public class UpdateSuppressionBlockSettings
{
    @Rule(desc = "UpdateSuppressionBlock", category = {CREATIVE, "extras"})
    public static boolean updateSuppressionBlock = false;

}
