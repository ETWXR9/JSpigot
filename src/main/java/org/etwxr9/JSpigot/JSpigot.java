package org.etwxr9.JSpigot;

import com.google.common.io.Resources;
import com.oracle.truffle.js.scriptengine.GraalJSScriptEngine;
import org.bukkit.Bukkit;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;
import org.graalvm.polyglot.Source;

import javax.script.ScriptEngine;

import javax.script.ScriptEngineManager;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        ClassLoader cl = this.getClass().getClassLoader();
        Thread.currentThread().setContextClassLoader(cl);
        var path = getDataFolder().getPath();
        Context jsContext = Context.newBuilder("js")
                .allowAllAccess(true)
                .option("engine.WarnInterpreterOnly", "false")
                .build();
        try {
            var file = new File(path + "/init.js");
            jsContext.eval(Source.newBuilder("js", file).build());
        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.setProperty("nashorn.args", "--language=es6");

//        var sem = new ScriptEngineManager();
//        ScriptEngine engine = new ScriptEngineManager(this.getClassLoader()).getEngineByName("js");
//
//        var path = getDataFolder().getPath();
//        try {
////            getLogger().info("DataFolder = "+path);
//            engine.eval(new java.io.FileReader(path + "/init.js"));
//            getLogger().info("init.js loaded");
////            getLogger().info(Files.readString(Paths.get(path + "\\init.js")));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        registerCommands(new testCmd("jspigot"));
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

    public void writeJsFile() throws IOException {
        Path jspath = Paths.get(System.getProperty("user.dir") + "\\plugins\\JSpigot");
        if (!Files.exists(jspath)) {
            Files.createDirectories(jspath);
        }
        Path initpath = Paths.get(System.getProperty("user.dir") + "\\plugins\\JSpigot\\init.js");
        if (!Files.exists(initpath)) {
            var initfile = getResource("js/init.js");
            Files.write(initpath,initfile.readAllBytes());
        }

        //
        Path listenerpath = Paths.get(System.getProperty("user.dir") + "\\plugins\\JSpigot\\listener.js");
        if (!Files.exists(listenerpath)) {
            var listenerfile = getResource("js/listener.js");

            Files.write(listenerpath, listenerfile.readAllBytes());
        }
        //
        Path commandpath = Paths.get(System.getProperty("user.dir") + "\\plugins\\JSpigot\\command.js");
        if (!Files.exists(commandpath)) {
            var commandfile = getResource("js/command.js");
            Files.write(commandpath, commandfile.readAllBytes());
        }
    }
}
