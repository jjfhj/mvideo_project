package com.github.jjfhj.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "file:/tmp/locale.properties",
        "classpath:config/locale.properties"
})
public interface WebDriverConfig extends Config {

    @Key("browser")
    @DefaultValue("chrome")
    String browser();

    @Key("versionBrowser")
    @DefaultValue("91.0")
    String versionBrowser();

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String browserSize();
}
