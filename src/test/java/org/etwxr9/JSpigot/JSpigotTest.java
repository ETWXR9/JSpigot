package org.etwxr9.JSpigot;


import javax.script.*;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JSpigotTest {

    @org.junit.jupiter.api.Test
    void onEnable() throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("js");
        var path = System.getProperty("user.dir")+"\\testjs\\";
        try {
            engine.eval(new java.io.FileReader(path+"test.js"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // create an Invocable object by casting the script engine object
        Invocable inv = (Invocable) engine;
        // invoke the function named "hello" with "Scripting!" as the argument
        try {
            inv.invokeFunction("init", "helloW!");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}