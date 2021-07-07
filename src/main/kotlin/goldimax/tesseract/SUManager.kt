package goldimax.tesseract

inline class QQUser(val id: Long)
inline class TGUser(val id: Long)

object SUManager {
    fun isSuperuser(user: QQUser) = user.id == UniBot.qqsu
    fun isSuperuser(user: TGUser) = user.id == UniBot.tgsu
}