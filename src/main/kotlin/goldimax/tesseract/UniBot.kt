package goldimax.tesseract

import com.elbekD.bot.types.Message
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import net.mamoe.mirai.BotFactory
import net.mamoe.mirai.alsoLogin
import java.io.File
import com.elbekD.bot.Bot as tgBot

const val envName = "env"

object UniBot {
    val env = File(envName).readLines()

    val tgToken = env[0]
    val tgsu = env[1].trim().toLong()

    val qq = BotFactory.newBot(env[2].trim().toLong(), env[3])
    val qqsu = env[5].trim().toLong()
    val tg = tgBot.createPolling("", tgToken)
    val tgListener = mutableListOf<suspend (Message) -> Unit>()

    init {
        tg.onMessage { msg -> tgListener.forEach { it(msg) } }
        GlobalScope.launch { tg.start() }
        runBlocking { qq.alsoLogin() }
    }

    suspend fun start() {
        qq.join()
    }
}
