//Examples of registering events
listener = {};
listener.PluginManager = Java.type("org.bukkit.Bukkit").getPluginManager();
listener.ListenerType = Java.extend(Java.type("org.bukkit.event.Listener"), {});
listener.Listener = new listener.ListenerType();
//<eventClass,[handler]>
listener.EventHandlerMap = new Map();
//Executor: Find handler in EventHandlerMap and call it
listener.ExecutorType = Java.extend(Java.type("org.bukkit.plugin.EventExecutor"), {
    execute: function (l, e) {
        if (e == null) { return; }
        listener.EventHandlerMap.forEach((handlerArray, eventClass) => {
            if (e.getClass().equals(eventClass)) { handlerArray.forEach(f => f(e)); }
        });
    }
});

listener.Executor = new listener.ExecutorType();
listener.registerEvent = function (eventType, handler) {
    let eventClass = eventType.class;
    //Register event if it has not been register
    if (!listener.EventHandlerMap.has(eventClass)) {
        listener.EventHandlerMap.set(eventClass, [])
        listener.PluginManager.registerEvent(eventClass,
            listener.Listener, Java.type("org.bukkit.event.EventPriority").NORMAL,
            listener.Executor, listener.PluginManager.getPlugin("JSpigot"));
    }
    listener.EventHandlerMap.get(eventClass).push(handler)

};
print("listener.js loaded")





