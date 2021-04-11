package updatesuppressionblock;

import carpet.settings.ParsedRule;
import carpet.settings.Rule;
import carpet.settings.Validator;
import net.minecraft.server.command.ServerCommandSource;

import static carpet.settings.RuleCategory.CREATIVE;

public class UpdateSuppressionBlockSettings
{
    public static int getInteger(String s) {
        try {
            return Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return -1;
        }
    }

    public static int updateSuppressionBlockSetting = -1;
    @Rule(
            desc = "Placing an activator rail on top of a barrier block will update suppress when the rail turns off.",
            extra = {"Entering an integer will make the update suppression block auto-reset","Integer entered is the delay in ticks for it to reset"},
            category = {CREATIVE, "extras"},
            options = {"false","true","1"},
            strict = false,
            validate = updateSuppressionBlockModes.class
    )
    public static String updateSuppressionBlock = "false";

    //Ghetto work around so I don't need to write my own validator cause im lazy :)
    private static class updateSuppressionBlockModes extends Validator<String> {
        @Override
        public String validate(ServerCommandSource source, ParsedRule<String> currentRule, String newValue, String string) {
            if (!currentRule.get().equals(newValue)) {
                if (newValue.equalsIgnoreCase("false")) {
                    updateSuppressionBlockSetting = -1;
                } else if (newValue.equalsIgnoreCase("true")) {
                    updateSuppressionBlockSetting = 0;
                } else {
                    int parsedInt = getInteger(newValue);
                    if (parsedInt <= 0) {
                        updateSuppressionBlockSetting = -1;
                        return "false";
                    } else {
                        updateSuppressionBlockSetting = parsedInt;
                    }
                }
            }
            return newValue;
        }
        @Override
        public String description() {
            return "Cannot be negative, can be true, false, or # > 0";
        }
    }
}
