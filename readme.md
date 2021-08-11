# Copy. I am online.

[彩虹桥](https://github.com/niltok/tesseract)
的无阿里云依赖裁切版本。
只保留了无需状态的 TG 转发和一言功能。

**因为是裁切版本所以部分功能并未测试，如有问题请提Issue**

### 使用

- 在 Telegram 找 BotFather 机器人选择关闭 TG 机器人的 Group Privacy Mode 
- 从 Release 页面下载最新版 Jar
- 上传到服务器上
- 在服务器上安装 Java 运行时环境
- 在 Jar 同目录下建立 `env` 文件
   - 第一行为 TG 机器人的 Token
   - 第二行为 TG 机器人管理员的 ID（可暂时填个 0 ，等上线后通过 `/rainbow` 指令查询自己的 ID ）
   - 第三行为 QQ 机器人的 QQ 号
   - 第四行为 QQ 机器人的密码
   - 第五行为 QQ 机器人管理员
- `java -Dmirai.slider.captcha.supported -jar tesseract.jar`
- 如果有则按照 [教程](https://github.com/project-mirai/mirai-login-solver-selenium) 手工处理验证码

### QQ 指令

- `rainbow` 查询 Bot 状态
- `plz disconnect` 清除本群的所有转发连结
- `mention all` 当彩虹桥是群管理员时发送 @全体成员
- `一言` 数据来自 [一言](https://hitokoto.cn/)
- `kiss me` （彩蛋

### TG 指令

注：指令后都不能加 @<Bot名字>

- `/rainbow` 查询 Bot 状态
- `/connect <qq群号>` 把本群和群号为 `<qq群号>` 的 QQ 群连结
- `/disconnect` 清除本群所有连结
- `/hitokoto` 一言
- `/drive` 暂停本群转发
- `/park` 恢复本群转发
- `/is_drive`
