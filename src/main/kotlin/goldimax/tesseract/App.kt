package goldimax.tesseract

import kotlinx.coroutines.runBlocking
import org.apache.log4j.BasicConfigurator
import shadow.org.apache.logging.log4j.Level
import shadow.org.apache.logging.log4j.core.config.Configurator

fun main() = runBlocking {
    // Load Plugin
    UniBot
    QQOther
    TgOther
    Forward

    UniBot.start()
}

