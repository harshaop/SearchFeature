package TestNGTests

import DeviceDriverHandler.driver
import DeviceDriverHandler.properties
import UnibetBlogPages.BlogSearchPage
import io.appium.java_client.android.nativekey.AndroidKey
import org.testng.Assert.assertEquals
import org.testng.Assert.assertTrue
import org.testng.annotations.Optional
import org.testng.annotations.Parameters

import org.testng.annotations.Test

class BlogSearchPageTests : TestBase() {

    @Parameters("url")
    @Test()
    fun selectAndUnSelectTab(@Optional("https://www.unibet.co.uk/blog")url: String) {
        val searchPage = BlogSearchPage(driver)
        if (!driver.currentUrl.contains("$url/search"))
            getURL("$url/search")
        //assertEquals(driver.title, properties.getProperty("pageTitle"))
        searchPage.commonHomePage.blogTabSelect()
        assertTrue(searchPage.commonHomePage.blogTabExpandedIsTrue())
        searchPage.commonHomePage.blogTabUnSelect()
        assertTrue(searchPage.commonHomePage.blogTabExpandedIsTrue().not())
    }

    @Parameters("url")
    @Test()
    fun noSearchResults(@Optional("https://www.unibet.co.uk/blog")url: String) {
        val searchPage = BlogSearchPage(driver)
        if (!driver.currentUrl.contains("/search"))
            getURL("$url/search")
        searchPage.clearSearchTab()
        assertEquals(searchPage.getTextFromSearchInputField(), "")
        searchPage.clickSearch()
        assertTrue(searchPage.verifyNoSearchResult())
    }

    @Parameters("url", "searchTerm")
    @Test()
    fun searchSuggestions(@Optional("https://www.unibet.co.uk/blog")url: String,
                          @Optional("Football")searchTerm: String ) {
        val searchPage = BlogSearchPage(driver)
        if (!driver.currentUrl.contains("/search"))
            getURL("$url/search")
        searchPage.clearSearchTab()
        searchPage.enterSearchTerm(searchTerm)
        assertTrue(searchPage.isSearchSuggestionsShown())
        assertEquals(searchPage.searchSuggestionHighlightedText(), searchTerm.toLowerCase())
        searchPage.clickOnSearchSuggestion()
        sendAndroidKeyEvent(AndroidKey.BACK)
        assertTrue(driver.currentUrl.contains("/search"))
    }

    @Parameters("url", "searchTerm")
    @Test()
    fun searchResults(@Optional("https://www.unibet.co.uk/blog")url: String,
                      @Optional("Football")searchTerm: String ) {
        val searchPage = BlogSearchPage(driver)
        if (!driver.currentUrl.contains("/search"))
            getURL("$url/search")
        searchPage.clearSearchTab()
        searchPage.enterSearchTerm(searchTerm)
        searchPage.clickSearch()
        assertTrue(searchPage.isSearchResultShown() && searchPage.verifySearchResultContents())

        searchPage.clickOnSearchResult(2)
        sendAndroidKeyEvent(AndroidKey.BACK)
        assertEquals(searchPage.getTextFromSearchInputField() , searchTerm)
    }

}
