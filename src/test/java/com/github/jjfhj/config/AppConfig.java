package com.github.jjfhj.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "file:/tmp/app.properties",
        "classpath:config/app.properties"
})
public interface AppConfig extends Config {

//    @Key("appURL")
    String appURL();

//    @Key("appForBusinessURL")
    String appForBusinessURL();
}
