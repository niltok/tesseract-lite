package goldimax.tesseract

import java.io.File

data class Connection(
    val qq: Long,
    val tg: Long,
    var drive: Long = 0
)

object Connections {
    val connect = run {
        val conf =
            File("connections").readLines()
        val raw = conf.filter { it.isNotBlank() }
            .map { it.split(",").map { it.trim().toLong() } }
        raw.map { Connection(it[0], it[1], it[2]) }.toMutableList()
    }
    fun findQQByTG(tg: Long) = connect.find { it.tg == tg }?.qq
    fun findTGByQQ(qq: Long) = connect.find { it.qq == qq }?.tg
    fun drive(tg: Long) {
        connect.forEach {
            if (it.tg == tg) it.drive = 1
        }
        save()
    }
    fun park(tg: Long) {
        connect.forEach {
            if (it.tg == tg) it.drive = 0
        }
        save()
    }
    fun isDrive(tg: Long)  = connect.find { it.tg == tg }?.drive
    fun save() {
        File("connections").writeText(connect.joinToString("\n") { "${it.qq},${it.tg}" })
    }
}
