package com.sciensa.collagen;

import java.io.IOException;

import org.mule.api.annotations.Config;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.MetaDataScope;
import org.mule.api.annotations.Processor;

import com.sciensa.collagen.config.ConnectorConfig;
import com.sciensa.collagen.process.Process;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@Connector(name="collagen", friendlyName="Collagen")
@MetaDataScope( DataSenseResolver.class )
public class CollagenConnector {

    @Config
    ConnectorConfig config;

    /**
     * Custom processor
     *
     * @param payload to be used to generate a conversion.
     * @param template to be used to generate a conversion.
     * @return Json converted
     */
    @Processor
    public String collagen(String payload, String template) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
        /*
         * MESSAGE PROCESSOR CODE GOES HERE
         */
    	Process process = new Process();
		String result = process.processJSON(payload,template);
        return result;
    }

    public ConnectorConfig getConfig() {
        return config;
    }

    public void setConfig(ConnectorConfig config) {
        this.config = config;
    }

}