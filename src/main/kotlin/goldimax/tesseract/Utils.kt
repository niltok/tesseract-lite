package goldimax.tesseract

import com.elbekD.bot.Bot
import com.elbekD.bot.http.await
import com.elbekD.bot.types.Message
import net.mamoe.mirai.contact.Member
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.event.events.MessageEvent
import net.mamoe.mirai.message.data.*
import net.mamoe.mirai.message.data.Message as QMsg

fun String.or(string: String) = if (isNullOrBlank()) string else this

fun <T> checkNull(x: T?, msg: () -> Any) =
    check(x == null, msg)

fun QMsg.plainText() = this.toMessageChain().filterIsInstance<PlainText>().joinToString { it.content }

suspend fun GroupMessageEvent.reply(msg: QMsg) =
     group.sendMessage(msg)

suspend fun GroupMessageEvent.reply(msg: String) =
    group.sendMessage(msg)

suspend fun GroupMessageEvent.quoteReply(msg: QMsg) =
    group.sendMessage(QuoteReply(source) + msg)

suspend fun GroupMessageEvent.quoteReply(msg: String) =
    quoteReply(msg.toPlainText())

suspend fun MessageEvent.reply(msg: QMsg) =
    if (this is GroupMessageEvent) group.sendMessage(msg)
    else sender.sendMessage(msg)

suspend fun MessageEvent.reply(msg: String) =
    reply(msg.toPlainText())

suspend fun MessageEvent.quoteReply(msg: QMsg) =
    if (this is GroupMessageEvent) group.sendMessage(QuoteReply(source) + msg)
    else sender.sendMessage(QuoteReply(source) + msg)

suspend fun MessageEvent.quoteReply(msg: String) =
    quoteReply(msg.toPlainText())

// qq
suspend inline fun MessageEvent.error(after: () -> Unit) {
    try {
        after()
    } catch (e: Exception) {
        quoteReply(e.localizedMessage)
    }
}

suspend inline fun GroupMessageEvent.error(after: () -> Unit) {
    try {
        after()
    } catch (e: Exception) {
        quoteReply(e.localizedMessage)
    }
}

fun MessageEvent.testSu() =
    check(SUManager.isSuperuser(QQUser(sender.id))) { "Sorry, you are not superuser." }

fun testSu(msg: Message) =
    check(SUManager.isSuperuser(TGUser(msg.from!!.id.toLong()))) { "Sorry, you are not superuser." }

fun Member.displayName() =
    when {
        nameCard.isNotEmpty() -> nameCard
        nick.isNotEmpty() -> nick
        else -> id.toString()
    }

suspend fun tgFileUrl(fileID: String) =
    "https://api.telegram.org/file/bot${UniBot.tgToken}/${
    UniBot.tg.getFile(fileID).await().file_path}"

// tg
fun Message.displayName() =
    from?.run {
        "$first_name ${last_name.orEmpty()}"
    }.orEmpty()

inline fun Bot.error(id: Long, from: Int?, after: () -> Unit) = try {
    after()
} catch (e: Exception) {
    sendMessage(id, e.localizedMessage, replyTo = from)
}

inline fun Bot.error(msg: Message, after: () -> Unit) =
    error(msg.chat.id, msg.message_id) { after() }

infix fun MessageSource.eq(ms: MessageSource) =
    ids.contentEquals(ms.ids) &&
            fromId == ms.fromId &&
            targetId == ms.targetId
