package com.sciensa.collagen;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.mule.api.annotations.MetaDataKeyRetriever;
import org.mule.api.annotations.MetaDataRetriever;
import org.mule.api.annotations.components.MetaDataCategory;
import org.mule.common.metadata.DefaultMetaData;
import org.mule.common.metadata.DefaultMetaDataKey;
import org.mule.common.metadata.MetaData;
import org.mule.common.metadata.MetaDataKey;
import org.mule.common.metadata.MetaDataModel;
import org.mule.common.metadata.builder.DefaultMetaDataBuilder;
import org.mule.common.metadata.builder.DynamicObjectBuilder;
import org.mule.common.metadata.datatype.DataType;

/**
 * Category which can differentiate between input or output MetaDataRetriever
 */
@MetaDataCategory
public class DataSenseResolver {

    /**
     * If you have a service that describes the entities, you may want to use
     * that through the connector. Devkit will inject the connector, after
     * initializing it.
     */
    @Inject
    private CollagenConnector connector;
    
    /**
     * Retrieves the list of keys
     */
    @MetaDataKeyRetriever
    public List<MetaDataKey> getMetaDataKeys() throws Exception {
        List<MetaDataKey> keys = new ArrayList<MetaDataKey>();
        return keys;
    }
    
    @MetaDataRetriever
    public MetaData getMetaData(MetaDataKey key) throws Exception {
        DefaultMetaDataBuilder builder = new DefaultMetaDataBuilder();
        
        MetaDataModel model = builder.build();
        MetaData metaData = new DefaultMetaData(model);

        return metaData;
    }

    public CollagenConnector getConnector() {
        return connector;
    }

    public void setConnector(CollagenConnector connector) {
        this.connector = connector;
    }
}
