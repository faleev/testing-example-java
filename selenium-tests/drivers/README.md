Custom Drivers Collection
=========================

This directory should contains custom Selenium/Webdriver drivers for running local tests in the different browsers, e.g.
Google Chrome. By default, this directory is empty due to preventing commits of the binary data to the VCS. All needed
drivers could be downloaded from Internet easily.

Google Chrome driver
--------------------

Google Chrome Selenium driver could be find [here](http://chromedriver.storage.googleapis.com/index.html).
Additional information about using this driver could be find [here](https://code.google.com/p/selenium/wiki/ChromeDriver).
To use Chome driver you should to set appropriate system property:

    System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");

Custom Firefox binary
---------------------

Selenium/Webdriver gives us an opportunity to run automated tests in the different versions of the Firefox browser.
To run automated tests in the Firefox browser's version which is different to the installed in the system you should to
download appropriate binary file from the official Mozilla [repository](http://ftp.mozilla.org/pub/mozilla.org/firefox/releases/)
and specify binary file in the next manner (for Mac OS Firefox implementation):

    class SomeClass {

    protected WebDriver driver;

        private void startCustomFirefoxDriver() {
            FirefoxBinary binary = new FirefoxBinary(new File("drivers/Firefox.app/Contents/MacOS/firefox-bin"));
            FirefoxProfile profile = new FirefoxProfile();
            this.driver = new FirefoxDriver(binary, profile);
        }
    }

This approach also verified on the Linux environments (particularly on Ubuntu).