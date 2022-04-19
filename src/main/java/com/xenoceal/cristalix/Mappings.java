package com.xenoceal.cristalix;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

public final class Mappings {

    public static Object getRenderManager() {
        return Reflection.invokeMethod("renderManager");
    }

    public static Object getDataStore() {
        return Reflection.invokeMethod("dataStore", getRenderManager());
    }

    public static Object getPacketManager() {
        return Reflection.invokeMethod("packetManager");
    }

    public static void emitJson(String s, Class<?> clazz, Object obj) {
        Reflection.invokeMethod("emitJson", getPacketManager(), s, clazz, obj);
    }

    public static Object PacketEnableDisableConstructor(UUID uuid, Set<UUID> enables, Set<UUID> disables) {
        return Reflection.invokeMethod("PacketEnableDisable", uuid, enables, disables);
    }

    public static Set<UUID> getMyEnabledModels() {
        return Reflection.invokeMethod("getMyEnabledModels", getDataStore());
    }

    public static Map<UUID, ?> getDefinedModels() {
        return Reflection.invokeMethod("definedModels", getDataStore());
    }

    public static Map<UUID, ?> getDefinedEmotions() {
        return Reflection.invokeMethod("definedEmotions", getDataStore());
    }

}
