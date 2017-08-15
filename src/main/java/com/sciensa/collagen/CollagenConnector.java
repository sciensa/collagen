package com.sciensa.collagen;

import java.io.IOException;

import org.mule.api.annotations.Config;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.MetaDataScope;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.param.MetaDataKeyParam;
import org.mule.api.annotations.param.MetaDataKeyParamAffectsType;

import com.google.gson.Gson;
import com.sciensa.collagen.config.ConnectorConfig;
import com.sciensa.collagen.process.Process;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

/**
 * 
 * @author Douglas Pimentel Rodrigues @Sciensa
 * Configuration class for connector collagen
 */
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
     * @return String transformed by template
     */
    @Processor
    public String process(String payload, String template) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
        /*
         * MESSAGE PROCESSOR CODE GOES HERE
         */
    	Process process = new Process();
		String result = process.processJSON(payload,template);
        return result;
    }
    /**
     * 
     * @param entityType
     * @param entityData
     * @param template
     * @return String transformed by template
     * @throws TemplateNotFoundException
     * @throws MalformedTemplateNameException
     * @throws ParseException
     * @throws IOException
     * @throws TemplateException
     */
    @Processor
    public Object processjava(@MetaDataKeyParam(affects = MetaDataKeyParamAffectsType.BOTH) String entityType, @Default("#[payload]") Object entityData, String template) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
    	
    	if (!(entityData instanceof Object)) {
    		throw new RuntimeException("Entity not recognized");
    	}
    	Process process = new Process();
    	Gson gson = new Gson();
    	String json = gson.toJson(entityData);
    	String result = process.processJSON(json,template);
        return result;
    	   
    }

    public ConnectorConfig getConfig() {
        return config;
    }

    public void setConfig(ConnectorConfig config) {
        this.config = config;
    }

}