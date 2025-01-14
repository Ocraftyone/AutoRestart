package de.geheimagentnr1.auto_restart.handlers;

import de.geheimagentnr1.auto_restart.AutoRestart;
import de.geheimagentnr1.auto_restart.config.ServerConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;


@Mod.EventBusSubscriber(
	modid = AutoRestart.MODID,
	bus = Mod.EventBusSubscriber.Bus.MOD,
	value = Dist.DEDICATED_SERVER
)
public class ModEventHandler {
	
	
	@SubscribeEvent
	public static void handleModConfigLoadingEvent( ModConfigEvent.Loading event ) {
		
		ServerConfig.handleConfigChange();
	}
	
	@SubscribeEvent
	public static void handleModConfigReloadingEvent( ModConfigEvent.Reloading event ) {
		
		ServerConfig.handleConfigChange();
	}
}
