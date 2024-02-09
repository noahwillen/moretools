package net.noah.moretools;


import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.noah.moretools.init.Registration;

@Mod(MoreTools.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MoreTools {

    public static final String MOD_ID = "moretools";

    public static IEventBus MOD_EVENT_BUS;

    public MoreTools() {

        MOD_EVENT_BUS = FMLJavaModLoadingContext.get().getModEventBus();

        MOD_EVENT_BUS.register(Registration.class);

        Registration.init();
    }
}
