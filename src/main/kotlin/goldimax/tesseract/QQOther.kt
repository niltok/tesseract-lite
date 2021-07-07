package goldimax.tesseract

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.jcabi.manifests.Manifests
import net.mamoe.mirai.event.subscribeGroupMessages
import net.mamoe.mirai.event.subscribeMessages
import net.mamoe.mirai.message.data.*
import java.net.URL

object QQOther {
    init {
        val qq = UniBot.qq
        qq.eventChannel.subscribeMessages {
            case("rainbow") {
                val info = """
                Copy. I am online.
                Superuser is ${UniBot.qqsu}.
                Build: ${Manifests.read("Version")}.
            """.trimIndent()
                quoteReply(info)
            }
            case("一言") {
                error {
                    val json = Parser.default().parse(
                        URL("https://v1.hitokoto.cn/")
                            .openStream()
                    ) as JsonObject
                    reply("「${
                        json.string("hitokoto")}」 —— ${
                        json.string("from")}")
                }
            }
            case("kiss me") quoteReply (Face(Face.亲亲))
            case("mention all") reply (AtAll)
            case("reboot!!") {
                error {
                    testSu()
                    reply("Trying to reboot...")
                    Runtime.getRuntime().exec(
                        "systemctl restart rainbow.service")
                }
            }
            startsWith("") {
                if (message.plainText().trim() == "full content")
                    message[QuoteReply]?.source?.originalMessage?.let {
                        quoteReply(it.contentToString())
                    }
                message[RichMessage]?.content?.let {
                    val json = Parser.default().parse(it.byteInputStream()) as JsonObject
                    json.obj("meta") ?.values ?.map { it as JsonObject }
                        ?.joinToString("\n") { it.string("qqdocurl") ?: "" }
                        ?.let { quoteReply(it) }
                }
            }
        }
        qq.eventChannel.subscribeGroupMessages {
            case("plz disconnect") {
                error {
                    testSu()

                    Connections.connect.removeIf { it.qq == source.group.id }
                    Connections.save()

                    reply("Done.")
                }
            }
        }
    }
}