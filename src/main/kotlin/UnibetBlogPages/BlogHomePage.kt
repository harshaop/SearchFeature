package UnibetBlogPages


import DeviceDriverHandler.Base
import io.appium.java_client.MobileElement

import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.pagefactory.AppiumFieldDecorator
import org.apache.logging.log4j.LogManager
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

open class BlogHomePage(driver: AndroidDriver<MobileElement>) :Base() {
    private val logger = LogManager.getLogger(BlogHomePage::class.java)

    init {
        PageFactory.initElements(AppiumFieldDecorator(driver), this)
    }

    @FindBy(id = "CybotCookiebotDialogBodyButtonAccept")
    private val acceptCookie: WebElement? = null

    @FindBy(css = "._2-3NoMrrCx3SZdpMb5m6iF")
    private val blogTab: WebElement? = null

    @FindBy(xpath = "//div[@class='A_aSfFe9DgOoNMeA0FBso']")
    private val blogTabClose: WebElement? = null

    @FindBy(xpath = "//div[@class='rI41o9BAj8cGhB9n0sKTJ _3G4qdcm5JUCGl4NVY7xrv0']")
    private val blogTabList: List<WebElement>? = null

    @FindBy(xpath = "//div[@class='_2zBdlixhoC1aMeu3UOVfQF']")
    private val blogTabText: WebElement? = null

    @FindBy(xpath = "//input[@class='_2wd9gLhmkYlXBxVhBXNH6i']")
    private val searchInputField: WebElement? = null

    /*@AndroidFindBy(uiAutomator = "new UiScrollable(new UiSelector()).scrollIntoView(text(\"India/English\"))")
    private val country: AndroidElement? = null*/


    fun acceptCookie() = acceptCookie?.click()
    fun blogTabSelect() = blogTab?.click()
    fun blogTabUnSelect() = blogTabClose?.click()
    fun blogTabText(): String = blogTabText?.text?.toString().toString()

    fun clickSearchInputField() = searchInputField?.click()
    fun enterSearchTerm(SearchTerm: String) = searchInputField?.sendKeys(SearchTerm)


    fun enterSearchTermAndSend(SearchTerm: String) {
        clickSearchInputField()
        enterSearchTerm(SearchTerm)
        sendAndroidKeyEvent()
    }

    fun blogTabExpandedIsTrue(): Boolean = blogTabList?.get(3)?.isDisplayed!!


}


