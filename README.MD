# JSpigot (WIP)

## 简介
该Spigot插件使用graalvm.js运行javascript脚本
## 使用
1. 初次加载插件之前，请将附带的JSpigot目录一并放入plugins目录内。
2. 插件启动时会自动执行init.js，由该脚本可使用graalvm提供的load()函数进一步执行其他脚本
3. listener.js为事件注册示例，command.js为指令注册示例