//Examples of registering commands
const command = {};
command.Plugin = Java.type("org.etwxr9.JSpigot.JSpigot").getInstance();
command.JsCommandType = Java.type("org.etwxr9.JSpigot.JsCommand");
command.register = function (
    cmdName,
    onCommand = function (sender, commandLabel, arguments) {
        sender.sendMessage("This command does not do anything!");
    }
) {
    if (cmdName == null || cmdName == "") {
        return;
    }
    //create Class extend JsCommandType
    const newCmd = Java.extend(this.JsCommandType, {
        run: function (sender, commandLabel, arguments) {
            if (!(sender instanceof Java.type("org.bukkit.entity.Player"))) {
                sender.sendMessage("The Console can't run this command!");
                return;
            }
            const player = sender;
            onCommand(player, commandLabel, arguments)
        }
    }
    )
    // newCmd.newCmd = function () {Java.super(newCmd).CComand(cmdName)};
    this.Plugin.registerCommands(new newCmd(cmdName));
}