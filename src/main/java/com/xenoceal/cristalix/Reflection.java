package com.xenoceal.cristalix;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public final class Reflection {

    final static MethodHandles.Lookup LOOKUP;

    final static Map<String, MethodHandle> handles;
    final static Map<String, Class<?>> classes;

    static void initialize() {
        try {
            classes.put("DataStore", findClass("aqQ"));
            classes.put("RenderManager", findClass("aqV"));
            classes.put("PacketManager", findClass("anC"));
            classes.put("PacketEnableDisable", findClass("arI"));

            handles.put("renderManager", LOOKUP.findStaticGetter(getClass("RenderManager"), "a", getClass("RenderManager")));
            handles.put("dataStore", LOOKUP.findGetter(getClass("RenderManager"), "a", getClass("DataStore")));
            handles.put("packetManager", LOOKUP.findStaticGetter(getClass("PacketManager"), "a", getClass("PacketManager")));
            handles.put("emitJson", LOOKUP.findVirtual(getClass("PacketManager"), "a", MethodType.methodType(Void.TYPE, String.class, Class.class, Object.class)));
            handles.put("PacketEnableDisable", LOOKUP.findConstructor(getClass("PacketEnableDisable"), MethodType.methodType(Void.TYPE, UUID.class, Set.class, Set.class)));
            handles.put("getMyEnabledModels", LOOKUP.findVirtual(getClass("DataStore"), "a", MethodType.methodType(Set.class)));
            handles.put("definedModels", LOOKUP.findVirtual(getClass("DataStore"), "a", MethodType.methodType(Map.class)));
            handles.put("definedEmotions", LOOKUP.findVirtual(getClass("DataStore"), "b", MethodType.methodType(Map.class)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    static <T> T invokeMethod(String name, Object... args) {
        try {
            return (T) handles.get(name).invokeWithArguments(args);
        } catch (Throwable t) {
            t.printStackTrace();
            return null;
        }
    }

    static Class<?> findClass(String name)
            throws ClassNotFoundException {
        return Class.forName(name, true, ClassLoader.getSystemClassLoader());
    }

    static Class<?> getClass(String name) {
        return classes.get(name);
    }

    static {
        LOOKUP = MethodHandles.lookup();

        handles = new HashMap<>();
        classes = new HashMap<>();
    }

}
