package com.sperasoft.sie.tools.interest.converters;

import com.sperasoft.sie.tools.interest.View;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * Created by Ilya K on 7/16/2017.
 */
public class ViewXmlConverter implements Converter {

    @Override
    public void marshal(Object o, HierarchicalStreamWriter writer, MarshallingContext marshallingContext) {
        View view = (View) o;

        writer.startNode("name");
        writer.setValue(view.getName());
        writer.endNode();
        writer.startNode("parameters");
        view.getParameters().forEach(parameter -> {
            writer.startNode("parameter");
            parameter.forEach((k, v) -> {
                writer.startNode("name");
                writer.setValue(k);
                writer.endNode();
                writer.startNode("value");
                writer.setValue(v);
                writer.endNode();
            });
            writer.endNode();
        });
        writer.endNode();
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader hierarchicalStreamReader, UnmarshallingContext unmarshallingContext) {
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    @Override
    public boolean canConvert(Class aClass) {
        return aClass.equals(View.class);
    }
}
