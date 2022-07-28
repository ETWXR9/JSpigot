package org.etwxr9.JSpigot;

import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import javax.script.ScriptEngine;

import javax.script.ScriptEngineManager;

import java.lang.reflect.Field;
import java.util.Arrays;


public class JSpigot extends JavaPlugin {
    private static JSpigot instance;

    private static SimpleCommandMap scm;
    private SimplePluginManager spm;

    public static JSpigot getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        setupSimpleCommandMap();
        System.setProperty("nashorn.args", "--language=es6");
        ClassLoader cl = this.getClass().getClassLoader();
        Thread.currentThread().setContextClassLoader(cl);
        var sem = new ScriptEngineManager();
        ScriptEngine engine = new ScriptEngineManager(this.getClassLoader()).getEngineByName("js");

        var path = System.getProperty("user.dir");
        try {
            engine.eval(new java.io.FileReader(path + "\\plugins\\JSpigot\\init.js"));
            getLogger().info("init.js loaded");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onDisable() {

    }

    //register cmd dynamically
    public void registerCommands(JsCommand... commands) {
        Arrays.stream(commands).forEach(command -> scm.register("JSpigot", command));//Register the plugin
    }

    private void setupSimpleCommandMap() {
        spm = (SimplePluginManager) this.getServer().getPluginManager();
        Field f = null;
        try {
            f = SimplePluginManager.class.getDeclaredField("commandMap");
        } catch (Exception e) {
            e.printStackTrace();
        }
        f.setAccessible(true);
        try {
            scm = (SimpleCommandMap) f.get(spm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SimpleCommandMap getCommandMap() {
        return scm;
    }
}
