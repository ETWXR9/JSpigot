const init = {}
init.jsPath = Java.type("java.lang.System").getProperty("user.dir") + "\\plugins\\JSpigot\\";
init.loadJs = function (fileName) {
    load(init.jsPath + fileName)
}


//Examples of registering events and commands
init.loadJs("listener.js");
listener.registerEvent(Java.type("org.bukkit.event.player.PlayerLoginEvent"), function (event) {
    print("��� " + event.getPlayer().getName() + " ����")
})
init.loadJs("command.js");
command.register("testcmd", function (sender, commandLabel, args) {
    sender.sendMessage("ִ��ָ��ɹ�");
})
