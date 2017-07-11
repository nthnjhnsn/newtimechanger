package pw.cinque.timechanger.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import pw.cinque.timechanger.TimeChanger;
import pw.cinque.timechanger.TimeType;

public class CommandNight extends CommandBase
{
    private Minecraft mc;
    
    public CommandNight() {
        this.mc = Minecraft.getMinecraft();
    }
    
    public String getCommandName() {
        return "night";
    }
    
    public String getCommandUsage(final ICommandSender sender) {
        return "/night";
    }
    
    public void processCommand(final ICommandSender sender, final String[] args) {
        if (args.length > 0) {
            mc.ingameGUI.getChatGUI().getSentMessages().remove(mc.ingameGUI.getChatGUI().getSentMessages().size() - 1);
        }

    	try{
        if (args.length == 1 && !TimeChanger.a) {
        	TimeChanger.e = Double.parseDouble(args[0]);
        	return;
        }
    	} catch (Throwable e) {}
        
        TimeChanger.TIME_TYPE = TimeType.NIGHT;
        sender.addChatMessage(new ChatComponentText("Time set to night.").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN)));
    }
    
    public int getRequiredPermissionLevel() {
        return 0;
    }
    
    public boolean canCommandSenderUseCommand(final ICommandSender sender) {
        return true;
    }
}
