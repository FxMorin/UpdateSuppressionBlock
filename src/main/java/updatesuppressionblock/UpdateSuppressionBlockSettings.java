package updatesuppressionblock;

import carpet.settings.Rule;

import static carpet.settings.RuleCategory.CREATIVE;

public class UpdateSuppressionBlockSettings
{
    @Rule(
            desc = "Placing an activator rail on top of a barrier block will update suppress when the rail turns off.",
            category = {CREATIVE, "extras"}
    )
    public static boolean updateSuppressionBlock = false;
}
