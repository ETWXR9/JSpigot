const init = {}
init.jsPath = Java.type("java.lang.System").getProperty("user.dir") + "\\plugins\\JSpigot\\";
init.loadJs = function (fileName) {
    load(init.jsPath + fileName)
}


//Examples of registering events and commands
init.loadJs("listener.js");
listener.registerEvent(Java.type("org.bukkit.event.player.PlayerLoginEvent"), function (event) {
    print("玩家 " + event.getPlayer().getName() + " 登入")
})
init.loadJs("command.js");
command.register("testcmd", function (sender, commandLabel, args) {
    sender.sendMessage("执行指令成功");
})
