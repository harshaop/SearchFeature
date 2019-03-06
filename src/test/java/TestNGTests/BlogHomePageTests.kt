package TestNGTests

import DeviceDriverHandler.driver
import DeviceDriverHandler.properties
import UnibetBlogPages.BlogHomePage
import UnibetBlogPages.BlogSearchPage
import org.testng.Assert
import org.testng.annotations.Optional
import org.testng.annotations.Parameters
import org.testng.annotations.Test

class BlogHomePageTests : TestBase() {

    @Parameters("url")
    @Test()
    fun selectAndUnSelectTab(@Optional("https://www.unibet.co.uk/blog") url: String) {
        val homePage = BlogHomePage(driver)
        Assert.assertTrue(driver.currentUrl.contains(url))
        homePage.blogTabSelect()
        Assert.assertTrue(homePage.blogTabExpandedIsTrue())
        homePage.blogTabUnSelect()
        Assert.assertTrue(homePage.blogTabExpandedIsTrue().not())
    }

    @Parameters("url", "searchTerm")
    @Test()
    fun enterSearchTermInBlogTab(
        @Optional("https://www.unibet.co.uk/blog") url: String,
        @Optional("Football") searchTerm: String
    ) {
        val homePage = BlogHomePage(driver)
        homePage.blogTabSelect()
        Assert.assertTrue(homePage.blogTabExpandedIsTrue())
        homePage.enterSearchTermAndSend(searchTerm)
        Assert.assertTrue(driver.currentUrl.contains("$url/search/$searchTerm"))
        val searchPage = BlogSearchPage(driver)
        Assert.assertTrue(searchPage.isSearchResultShown())
    }
}