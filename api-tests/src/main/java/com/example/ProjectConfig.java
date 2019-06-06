package com.example;

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.Sources;

//Can use multiple files
@Sources({"classpath:config.properties"})
public interface ProjectConfig extends Config {

    String apiPath();

}
