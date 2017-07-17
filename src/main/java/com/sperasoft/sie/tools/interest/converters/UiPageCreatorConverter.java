package com.sperasoft.sie.tools.interest.converters;

import com.sperasoft.sie.tools.interest.UiPageCreator;
import com.sperasoft.sie.tools.util.DateUtils;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * Created by Ilya K on 7/17/2017.
 */
public class UiPageCreatorConverter implements Converter {

    @Override
    public void marshal(Object o, HierarchicalStreamWriter writer, MarshallingContext marshallingContext) {
        UiPageCreator uiPageCreator = (UiPageCreator) o;

        writer.startNode("name");
        writer.setValue(uiPageCreator.getName());
        writer.endNode();
        writer.startNode("xAxis");
        writer.setValue(uiPageCreator.getxAxis());
        writer.endNode();
        writer.startNode("yAxis");
        writer.setValue(uiPageCreator.getyAxis());
        writer.endNode();
        writer.startNode("viewName");
        writer.setValue(uiPageCreator.getViewName());
        writer.endNode();
        writer.startNode("releaseName");
        writer.setValue(uiPageCreator.getReleaseName());
        writer.endNode();
        writer.startNode("startTime");
        writer.setValue(DateUtils.convertDate(uiPageCreator.getStartTime(), DateUtils.ISO_8601_24H_FULL_DATE_FORMAT));
        writer.endNode();
        writer.startNode("title");
        writer.setValue(uiPageCreator.getTitle());
        writer.endNode();
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader hierarchicalStreamReader, UnmarshallingContext unmarshallingContext) {
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    @Override
    public boolean canConvert(Class aClass) {
        return aClass.equals(UiPageCreator.class);
    }
}
