package com.sperasoft.sie.tools.interest.converters;

import com.sperasoft.sie.tools.interest.ReleaseCreator;
import com.sperasoft.sie.tools.util.DateUtils;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * Created by Ilya K on 7/16/2017.
 */
public class ReleaseCreatorXmlConverter implements Converter {

    @Override
    public void marshal(Object o, HierarchicalStreamWriter writer, MarshallingContext marshallingContext) {
        ReleaseCreator releaseCreator = (ReleaseCreator) o;

        writer.startNode("name");
        writer.setValue(releaseCreator.getName());
        writer.endNode();
        writer.startNode("projectName");
        writer.setValue(releaseCreator.getProjectName());
        writer.endNode();
        writer.startNode("line");
        writer.setValue(releaseCreator.getLine());
        writer.endNode();
        writer.startNode("startTime");
        writer.setValue(DateUtils.convertDate(releaseCreator.getStartTime(), DateUtils.ISO_8601_24H_FULL_DATE_FORMAT));
        writer.endNode();
        writer.startNode("endTime");
        writer.setValue(DateUtils.convertDate(releaseCreator.getEndTime(), DateUtils.ISO_8601_24H_FULL_DATE_FORMAT));
        writer.endNode();

    }

    @Override
    public Object unmarshal(HierarchicalStreamReader hierarchicalStreamReader, UnmarshallingContext unmarshallingContext) {
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    @Override
    public boolean canConvert(Class clazz) {
        return clazz.equals(ReleaseCreator.class);
    }
}
