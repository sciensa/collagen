package com.sciensa.collagen;

import java.io.IOException;

import org.mule.api.annotations.Config;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.MetaDataScope;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.display.Text;
import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.param.MetaDataKeyParam;
import org.mule.api.annotations.param.MetaDataKeyParamAffectsType;
import org.mule.api.annotations.param.Optional;

import com.google.gson.Gson;
import com.sciensa.collagen.config.ConnectorConfig;
import com.sciensa.collagen.process.Process;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

import org.json.JSONObject;
import org.json.XML;
import org.mule.api.annotations.MetaDataKeyRetriever;
import org.mule.api.annotations.MetaDataRetriever;
import java.util.List;
import org.mule.common.metadata.MetaDataKey;
import org.mule.common.metadata.MetaData;

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
    public static int PRETTY_PRINT_INDENT_FACTOR = 4;

    /**
     * Custom processor
     *
     * @param payload to be used to generate a conversion.
     * @param template to be used to generate a conversion.
     * @return String transformed by template
     */
    @Processor
    public String processjson(String payload,@Optional String template , @Optional @Text String template_schema) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
    
    	String result = "";
    	Process process = new Process();
    	if(null != template_schema && !"".equals(template_schema)){
    		result = process.processJSON(payload,template_schema);
    		return result;
    	}
    	result = process.processJSON(payload,template);
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
    public Object processjava(@MetaDataKeyParam(affects = MetaDataKeyParamAffectsType.BOTH) String entityType, @Default("#[payload]") Object entityData,@Optional String template , @Optional @Text String template_schema) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
    	
    	if (!(entityData instanceof Object)) {
    		throw new RuntimeException("Entity not recognized");
    	}
    	String result = "";
    	Process process = new Process();
    	Gson gson = new Gson();
    	String json = null;
    	json = gson.toJson(entityData);
    	
    	if(null != template_schema && !"".equals(template_schema)){
        	result = process.processJSON(json,template_schema);
            return result;
    	}
    	result = process.processJSON(json,template);
        return result;
    	   
    }
    /**
     * 
     * @param payload
     * @param template
     * @return String transformed by template
     * @throws TemplateNotFoundException
     * @throws MalformedTemplateNameException
     * @throws ParseException
     * @throws IOException
     * @throws TemplateException
     */
    @Processor
    public String processxml(String payload, @Optional String template , @Optional @Text String template_schema) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
        
    	Process process = new Process();
    	JSONObject xmlJSONObj = XML.toJSONObject(payload);
        String xmlToJson = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
        
        if(null != template_schema && !"".equals(template_schema)){
        	String result = process.processJSON(xmlToJson,template_schema);
            return result;
        }
		String result = process.processJSON(xmlToJson, template);
        return result;
    }

    public ConnectorConfig getConfig() {
        return config;
    }

    public void setConfig(ConnectorConfig config) {
        this.config = config;
    }

	@MetaDataKeyRetriever
	public List<MetaDataKey> getKeys() throws Exception {
		return null;
	}

	@MetaDataRetriever
	public MetaData getMetaData(MetaDataKey key) throws Exception {
		return null;
	}
}