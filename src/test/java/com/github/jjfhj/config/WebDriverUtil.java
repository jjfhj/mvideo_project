package com.github.jjfhj.config;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import static java.lang.String.format;

public class WebDriverUtil {

    private static final WebDriverConfig WEB_DRIVER_CONFIG = ConfigFactory.create(WebDriverConfig.class, System.getProperties());
    private static final CredentialsConfig CREDENTIALS_CONFIG = ConfigFactory.create(CredentialsConfig.class, System.getProperties());
    private static final AppConfig APP_CONFIG = ConfigFactory.create(AppConfig.class, System.getProperties());
    private static final String LOGIN = CREDENTIALS_CONFIG.login();
    private static final String PASSWORD = CREDENTIALS_CONFIG.password();
    private static final String SELENOID_URL = System.getProperty("remoteURL");
    private static final String REMOTE_URL = format("https://%s:%s@%s", LOGIN, PASSWORD, SELENOID_URL);
    public static final String VIDEO_STORAGE_URL = WEB_DRIVER_CONFIG.videoStorageURL();
    public static final String APP_URL = APP_CONFIG.appURL();
    public static final String APP_FOR_BUSINESS_URL = APP_CONFIG.appForBusinessURL();

    public static void configure() {
        Configuration.browser = WEB_DRIVER_CONFIG.browser();
        Configuration.browserVersion = WEB_DRIVER_CONFIG.versionBrowser();
        Configuration.browserSize = WEB_DRIVER_CONFIG.browserSize();

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
            Configuration.remote = REMOTE_URL;
            // Configuration.remote = selenoidURL;
        }

        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        Configuration.browserCapabilities = capabilities;
/*        Configuration.timeout = 10000;
        Configuration.pageLoadTimeout = 10000;*/
    }
}
