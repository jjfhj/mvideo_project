package com.github.jjfhj.config;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import static java.lang.String.format;

public class WebDriverUtil {

    private static final WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());
    private static CredentialsConfig credentials = ConfigFactory.create(CredentialsConfig.class);
    private static String login = credentials.login();
    private static String password = credentials.password();
    private static String selenoidURL = System.getProperty("remoteURL");
    private static String remoteURL = format("https://%s:%s@%s", login, password, selenoidURL);

    public static void configure() {
        Configuration.browser = config.browser();
        Configuration.browserVersion = config.versionBrowser();
        Configuration.browserSize = config.browserSize();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.addArguments("--no-sandbox");
        // chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--enable-automation");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-gpu");

        if (!System.getProperty("remoteURL").equals("")) {
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.remote = remoteURL;
            // Configuration.remote = selenoidURL;
        }

        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        Configuration.browserCapabilities = capabilities;
/*        Configuration.timeout = 10000;
        Configuration.pageLoadTimeout = 10000;*/
    }
}
