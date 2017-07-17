package com.sperasoft.sie.tools.interest;

import static java.lang.System.out;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

import static java.lang.System.out;

/**
 * Created by Ilya K on 7/16/2017.
 */
public class ViewCreatorTest {

    private static ViewCreator viewCreator;

    private static final String NAME = "npmt-events";
    private static final List<Map<String, String>> PARAMETERS =
            Arrays.asList(
                    Collections.singletonMap("Line", "q1"),
                    Collections.singletonMap("testSuiteName", "bi-tests/npmt-events"));

    private static final String INTEREST_BASE_URL = "http://qa.interest.spb.sperasoft.com";
    private static final String API_ENDPOINT = "/interest/api/collector/gui/view";

    @BeforeClass
    public static void setUp() {
        viewCreator = new ViewCreator(INTEREST_BASE_URL);
        viewCreator.setName(NAME);
        viewCreator.setParameters(PARAMETERS);
    }

    @AfterClass
    public static void tearDown() {
        viewCreator = null;
    }

    @Test
    public void testGeneratedInterestApiEndpoint() {
        out.println(viewCreator.getApiUrl());
        Assert.assertTrue(viewCreator.getApiUrl().equals(INTEREST_BASE_URL + API_ENDPOINT));
    }

    @Test
    public void testXmlConversion() {

        String xmlRepresentation = viewCreator.toXml();
        out.println(xmlRepresentation);

        Assert.assertTrue(xmlRepresentation.contains("<viewElement>"));
        Assert.assertTrue(xmlRepresentation.contains("</viewElement>"));
        Assert.assertTrue(xmlRepresentation.contains("<parameters>"));
        Assert.assertTrue(xmlRepresentation.contains("</parameters>"));
        PARAMETERS.forEach(param -> {
            param.forEach((k,v) -> {
                Assert.assertTrue(String.format("XML should contain parameter with both key '%s' ('<name>') and value '%s' ('<value>)", k, v),
                        xmlRepresentation.contains(String.format("<name>%s</name", k)));
                Assert.assertTrue(String.format("XML should contain parameter with both key '%s' ('<name>') and value '%s' ('<value>)", k, v),
                        xmlRepresentation.contains(String.format("<value>%s</value", v)));
            });
        });
    }
}