package DeviceDriverHandler


import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.nativekey.AndroidKey
import io.appium.java_client.android.nativekey.KeyEvent
import org.apache.logging.log4j.LogManager
import org.openqa.selenium.*


import java.io.FileInputStream
import java.io.IOException
import java.util.*

import org.openqa.selenium.remote.DesiredCapabilities
import java.net.URL
import java.util.NoSuchElementException


lateinit var properties: Properties
lateinit var driver: AndroidDriver<MobileElement>
abstract class Base {
    private val logger = LogManager.getLogger(Base::class.java)

    init {
        val filePath = System.getProperty("user.dir")
        val propertiesFilePath = filePath + "\\config.properties"
        try {
            properties = Properties()
            properties.load(FileInputStream(propertiesFilePath))
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun getDeviceDriver() {
        properties.getProperty("deviceType", "")
        val cap = DesiredCapabilities()
        cap.setCapability("platformName", properties.getProperty("deviceType", "Android"))
        cap.setCapability("deviceName", properties.getProperty("deviceName", "Pixel 2"))
        cap.setCapability("udid", properties.getProperty("udid", "FA7AC1A15091"))
        cap.setCapability("browserName", "Chrome")
        cap.setCapability("noReset", "true")
        driver = AndroidDriver(URL(properties.getProperty("url", "http://0.0.0.0:4723/wd/hub")),
            cap)
    }

    fun quitDriver() {
        driver.quit()
    }

    fun getURL(url: String) {
        driver.get(url)
    }

    fun waitTillElementNotPresent(element: WebElement): Boolean {
        return try {
            while (true) {
                try {
                    if (element.isDisplayed) {
                        Thread.sleep(500)
                        logger.debug("Waiting for element to disappear")
                    }
                } catch (exception: NoSuchElementException) {
                    break
                }
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    fun isElementPresent(element: WebElement): Boolean {
        try {
            if (element.isDisplayed) {
                return true
            }
        } catch (e: NoSuchElementException) {
            return false
        }
        return false
    }

    fun isListOfElementsShown(elements: List<WebElement>): Boolean {
        return elements.isNotEmpty()
    }

    fun sendAndroidKeyEvent(key: AndroidKey = AndroidKey.ENTER) {
        driver.pressKey(KeyEvent(key))
    }
}






