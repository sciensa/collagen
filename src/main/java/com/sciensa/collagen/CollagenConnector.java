package com.sciensa.collagen;

import java.util.Map;
import org.mule.api.annotations.Config;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.param.MetaDataKeyParam;
import org.mule.api.annotations.MetaDataScope;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.param.Default;

import com.sciensa.collagen.config.ConnectorConfig;

@Connector(name="collagen", friendlyName="Collagen")
@MetaDataScope( DataSenseResolver.class )
public class CollagenConnector {

    @Config
    ConnectorConfig config;

    /**
     * Custom processor
     *
     * @param friend Name to be used to generate a greeting message.
     * @return A greeting message
     */
    @Processor
    public String greet(String friend) {
        /*
         * MESSAGE PROCESSOR CODE GOES HERE
         */
        return config.getGreeting() + " " + friend + ". " + config.getReply();
    }

    /**
     * DataSense processor

     * @param key Key to be used to populate the entity
     * @param entity Map that represents the entity
     * @return Some string
     */
    @Processor
    public Map<String,Object> addEntity( @MetaDataKeyParam String key, @Default("#[payload]") Map<String,Object> entity) {
        /*
         * USE THE KEY AND THE MAP TO DO SOMETHING
         */
        return entity;
    }

    public ConnectorConfig getConfig() {
        return config;
    }

    public void setConfig(ConnectorConfig config) {
        this.config = config;
    }

}