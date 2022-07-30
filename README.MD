# JSpigot (WIP)

## 简介
该Spigot插件使用Nashorn运行javascript脚本
## 使用
1. 初次加载插件时，会生成JSpigot目录，并在该目录下生成init.js listener.js command.js三个脚本文件
2. 插件启动时会自动执行init.js，由该脚本可使用Nashorn提供的load()函数进一步执行其他脚本
3. listener.js为事件注册示例，command.js为指令注册示例
