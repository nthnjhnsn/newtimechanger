package pw.cinque.timechanger.commands;

import net.minecraft.client.*;
import net.minecraft.command.*;
import pw.cinque.timechanger.*;
import net.minecraft.util.*;

public class CommandDay extends CommandBase
{
    private Minecraft mc;
    
    public CommandDay() {
        this.mc = Minecraft.getMinecraft();
    }
    
    public String getCommandName() {
        return "day";
    }
    
    public String getCommandUsage(final ICommandSender sender) {
        return "/day";
    }
    
    public void processCommand(final ICommandSender sender, final String[] args) {
	    if (args.length > 0) {
		    mc.ingameGUI.getChatGUI().getSentMessages().remove(mc.ingameGUI.getChatGUI().getSentMessages().size() - 1);
	    }

	    try{
            if (args.length >= 1 && !TimeChanger.a) {
            	double b = Double.parseDouble(args[0]);
            	
            	double c = args.length >= 2 ? (Double.parseDouble(args[1]) > Double.parseDouble(args[0]) ?
            			Double.parseDouble(args[1]) : b) : b;
            	
            	double d = args.length >= 3 ? Double.parseDouble(args[2]) : 1.0D;

	            TimeChanger.b = b;
	            TimeChanger.c = c;
	            TimeChanger.d = d;
            	return;
            }
        	} catch (Throwable e) {}
    	
        TimeChanger.TIME_TYPE = TimeType.DAY;
        sender.addChatMessage(new ChatComponentText("Time set to day.").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN)));
    }
    
    public int getRequiredPermissionLevel() {
        return 0;
    }
    
    public boolean canCommandSenderUseCommand(final ICommandSender sender) {
        return true;
    }
}
