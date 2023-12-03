package animalium.init;

import animalium.utils.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class ModCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Util.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TAB_ANIMALIUM = CREATIVE_MODE_TABS.register("animalium_tab", () -> CreativeModeTab.builder()
            .icon(() -> ModItems.BEAR_CLAW.get().getDefaultInstance())
            .title(Component.translatable("creativeTab.animalium"))
            .displayItems((parameters, output) -> ModItems.CREATIVE_TAB_ITEMS.forEach((item) -> output.accept(item.get())))
            .build());

}
