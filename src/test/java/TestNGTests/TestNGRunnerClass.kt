package TestNGTests

import org.testng.TestNG

class TestNGRunnerClass {
    fun main(args: Array<String>) {
        val testNG = TestNG()
        val classes = arrayOf<Class<*>>(BlogSearchPageTests::class.java)
        testNG.setTestClasses(classes)
        testNG.run()
    }
}
