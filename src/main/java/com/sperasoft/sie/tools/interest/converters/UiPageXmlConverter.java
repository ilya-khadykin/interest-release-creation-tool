package com.sperasoft.sie.tools.interest.converters;

import com.sperasoft.sie.tools.interest.UiPage;
import com.sperasoft.sie.tools.util.DateUtils;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * Created by Ilya K on 7/17/2017.
 */
public class UiPageXmlConverter implements Converter {

    @Override
    public void marshal(Object o, HierarchicalStreamWriter writer, MarshallingContext marshallingContext) {
        UiPage uiPage = (UiPage) o;

        writer.startNode("name");
        writer.setValue(uiPage.getName());
        writer.endNode();
        writer.startNode("xAxis");
        writer.setValue(uiPage.getxAxis());
        writer.endNode();
        writer.startNode("yAxis");
        writer.setValue(uiPage.getyAxis());
        writer.endNode();
        writer.startNode("viewName");
        writer.setValue(uiPage.getViewName());
        writer.endNode();
        writer.startNode("releaseName");
        writer.setValue(uiPage.getReleaseName());
        writer.endNode();
        writer.startNode("startTime");
        writer.setValue(DateUtils.convertDate(uiPage.getStartTime(), DateUtils.ISO_8601_24H_FULL_DATE_FORMAT));
        writer.endNode();
        writer.startNode("title");
        writer.setValue(uiPage.getTitle());
        writer.endNode();
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader hierarchicalStreamReader, UnmarshallingContext unmarshallingContext) {
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    @Override
    public boolean canConvert(Class aClass) {
        return aClass.equals(UiPage.class);
    }
}
