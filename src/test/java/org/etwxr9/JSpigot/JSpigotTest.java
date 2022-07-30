package org.etwxr9.JSpigot;


import com.google.common.io.Resources;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.junit.jupiter.api.Test;

import javax.script.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JSpigotTest {

    @org.junit.jupiter.api.Test
    void onEnable() throws ScriptException {
        Context jsContext = Context.newBuilder("js")
                .allowAllAccess(true)
                .build();
        try {
            var initjs = Resources.getResource("js/init.js");
            System.out.println(initjs.toString());
            var file = new File("E:\\Work\\SpigotDev\\PluginDev\\JSpigot\\src\\main\\resources\\js\\init.js");
            jsContext.eval(Source.newBuilder("js", file).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void writeJsFile() {
        try {
            var initjs = Resources.getResource("js\\init.js");
            var jsStr = Resources.toString(initjs, Charset.forName("gbk"));
            System.out.println(jsStr);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}