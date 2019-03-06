package TestNGTests

import DeviceDriverHandler.Base
import DeviceDriverHandler.driver
import UnibetBlogPages.BlogHomePage
import org.testng.annotations.*
import org.apache.commons.io.FileUtils
import org.apache.logging.log4j.LogManager
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.testng.ITestResult
import org.testng.annotations.AfterMethod
import java.io.File


abstract class TestBase : Base() {

    private val logger = LogManager.getLogger(BlogHomePage::class.java)
    @Parameters("url")
    @BeforeSuite
    fun setup(@Optional("https://www.unibet.co.uk/blog")url: String) {
        getDeviceDriver()
        getURL(url)
        val homePage = BlogHomePage(driver)
        homePage.acceptCookie()
    }

    @AfterMethod
    fun screenShot(result: ITestResult) {
        val filePath = System.getProperty("user.dir")
        if (ITestResult.FAILURE == result.status) {
            try {
                val screenShot = driver as TakesScreenshot
                val src = screenShot.getScreenshotAs<File>(OutputType.FILE)
                FileUtils.copyFile(src, File( filePath+"\\screenshots\\"+ result.name + ".png"))
                logger.debug("Successfully captured a screenshot")
            } catch (e: Exception) {
                logger.debug("Exception while taking screenshot " + e.message)
            }
        }
    }

    @AfterSuite(enabled = true)
    fun tearDown() {
        quitDriver()
    }
}

