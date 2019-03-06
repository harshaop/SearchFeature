package UnibetBlogPages


import DeviceDriverHandler.Base
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.pagefactory.AppiumFieldDecorator
import org.apache.logging.log4j.LogManager
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class BlogSearchPage(driver: AndroidDriver<MobileElement>) : Base() {
    private val logger = LogManager.getLogger(BlogSearchPage::class.java)
    val commonHomePage: BlogHomePage = BlogHomePage(driver)

    init {
        PageFactory.initElements(AppiumFieldDecorator(driver), this)
    }

    @FindBy(css = "._3Hr3qONzU09D2EpcszWrzd")
    private val searchInputField: WebElement? = null

    @FindBy(css = "._yufu0rNaN")
    private val searchButton: WebElement? = null

    @FindBy(xpath = "//div[@class='qq-Pyd4MklQfqSfWC1B5j _2wNy0hiyLNvGiweDHpYxzG']")
    private val searchResultContainer: WebElement? = null

    @FindBy(css = "._1p7av0gr")
    private val searchSuggestionContainer: WebElement? = null

    @FindBy(css = "._16ex3ps")
    private val searchSuggestionResults: List<WebElement>? = null

    @FindBy(css = "._1iabadu")
    private val searchSuggestionHighlights: List<WebElement>? = null

    @FindBy(xpath = "//div[@class='sc-bxivhb iztyxF']")
    private val spinner: WebElement? = null

    @FindBy(css = "._19GYT_92JLty2kMUxLkxVO")
    private val clearTextButton: WebElement? = null

    @FindBy(css = "#icon-search]")
    val searchIcon: WebElement? = null

    @FindBy(css = "._3j1YMd3zjL1xNt87XF8hqO")
    val noSearchResultText: WebElement? = null

    @FindBy(css = "._16ex3ps")
    val searchSuggestionsList: List<WebElement>? = null

    @FindBy(xpath = "//div[@class='_2e2psHDN-uGXeIgjRxQTxJ']//*")
    val noSearchResultContents: List<WebElement>? = null

    @FindBy(xpath = "//div[@class='_2yltr7pih7eh3s3dI8W38D fCHCBFtkPyRWGPuYIZgtM']")
    val searchResultsList: List<WebElement>? = null

    @FindBy(xpath = "(//div[@class='_2yltr7pih7eh3s3dI8W38D fCHCBFtkPyRWGPuYIZgtM'])[1]//*")
    val searchResultContentsList: List<WebElement>? = null

    fun enterSearchTerm(searchTerm: String) = searchInputField?.sendKeys(searchTerm)

    fun clickSearch() = searchButton?.click()

    fun isSearchSuggestionsShown(): Boolean {
        waitTillElementNotPresent(spinner!!)
        return isElementPresent(searchSuggestionContainer!!)
    }

    fun isSearchResultShown(): Boolean {
        waitTillElementNotPresent(spinner!!)
        return isElementPresent(searchResultContainer!!)
    }

    fun verifyNoSearchResult(): Boolean {
        return ( noSearchResultContents?.size == 5)
    }

    fun clearSearchTab() {
        if (isElementPresent(clearTextButton!!)) clearTextButton.click()
    }

    fun getTextFromSearchInputField(): String = searchInputField!!.getAttribute("value")

    fun searchSuggestionHighlightedText(): String {
        var searchSuggestionBoldTextList: MutableList<String> = arrayListOf()
        val highlightsTextList: List<WebElement> = searchSuggestionHighlights!!
        for ((index) in highlightsTextList.withIndex())
            searchSuggestionBoldTextList.add(index, highlightsTextList[index].text)

        return searchSuggestionBoldTextList.map { it.toLowerCase() }.toSet().joinToString()
    }

    fun clickOnSearchSuggestion(index: Int = 0) {
        searchSuggestionsList?.get(index)?.click()
    }

    fun clickOnSearchResult(index: Int = 10) {
        searchResultsList?.get(index)?.click()
    }

    fun verifySearchResultContents(): Boolean {
        return isListOfElementsShown(searchResultContentsList!!)
    }
}





