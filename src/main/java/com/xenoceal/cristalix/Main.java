package com.xenoceal.cristalix;

import dev.xdark.clientapi.ClientApi;
import dev.xdark.clientapi.entity.Entity;
import dev.xdark.clientapi.entity.EntityPlayer;
import dev.xdark.clientapi.entry.ModMain;
import dev.xdark.clientapi.event.Listener;
import dev.xdark.clientapi.event.entity.EntityInit;
import dev.xdark.clientapi.event.network.ServerConnect;
import dev.xdark.clientapi.text.Text;
public final class Main
        implements ModMain, Listener {

    @Override
    public void load(ClientApi api) {
        Reflection.initialize();

        String[] lines = new String[] {
                "§aВерсия мода §e2.0",
                "§aРазблокировано §e" + Mappings.getDefinedModels().size() + " §aмоделей и §e" + Mappings.getDefinedEmotions().size() + " §aэмоций",
                "§aАвтор мода §eXenoceal"
        };

        ServerConnect.BUS.register(this, serverConnect -> {
            for (String str : lines)
                api.chat().printChatMessage(Text.of(str));
        }, 0);

        EntityInit.BUS.register(this, entityInit -> {
            Entity entity = entityInit.getEntity();

            if (entity instanceof EntityPlayer && entity != api.minecraft().getPlayer())
                Mappings.emitJson(
                        "p13n|enable_disable",
                        Reflection.getClass("PacketEnableDisable"),
                        Mappings.PacketEnableDisableConstructor(null, Mappings.getMyEnabledModels(), null)
                );
        }, 0);
    }

    @Override
    public void unload() {
        System.out.println("أحبه عندما يتأهل الرجال العراة");
    }

}
