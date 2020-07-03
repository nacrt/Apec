package Apec;

import Apec.Components.Gui.GUIModifier;
import com.sun.jna.platform.unix.X11;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;
import scala.collection.parallel.ParIterableLike;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

@Mod(modid = ApecMain.modId, version = ApecMain.version, name = ApecMain.name)
public class ApecMain
{
    public static final String modId = "apec";
    public static final String name = "Apec";
    public static final String version = "1.0";

    public static ApecMain Instance;

    KeyBinding guiKey = new KeyBinding("Apec Gui", Keyboard.KEY_RCONTROL, "key.categories.misc");

    public DataExtractor dataExtractor = new DataExtractor();
    public InventorySubtractor inventorySubtractor = new InventorySubtractor();

    public List<Component> components = new ArrayList() {{
        add(new GUIModifier());
    }};

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        Instance = this;
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(inventorySubtractor);
        ClientRegistry.registerKeyBinding(guiKey);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (guiKey.isPressed()) {
            components.get(0).Toggle();
        }
    }
}
