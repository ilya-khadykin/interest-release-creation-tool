package com.sperasoft.sie.tools.interest;

import static java.lang.System.out;

import com.sperasoft.sie.tools.util.DateUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.ZonedDateTime;

import static java.lang.System.out;

/**
 * Created by Ilya K on 7/17/2017.
 */
public class UiPageCreatorTest {

    private static UiPageCreator uiPageCreator;


    private static final String NAME = "npmt-events";
    private static final String X_AXIS = "Line";
    private static final String Y_AXIS = "testScriptName";
    private static final String VIEW_NAME = "npmt-events";
    private static final String RELEASE_NAME = "Release API Test";
    private static final String TITLE = "npmt-events uiPage Test API";
    private static final ZonedDateTime DATE = ZonedDateTime.now();

    private static final String INTEREST_BASE_URL = "http://qa.interest.spb.sperasoft.com";
    private static final String API_ENDPOINT = "/interest/api/collector/ui-page";

    @BeforeClass
    public static void setUp() {
        uiPageCreator = new UiPageCreator(INTEREST_BASE_URL);
        uiPageCreator.setName(NAME);
        uiPageCreator.setxAxis(X_AXIS);
        uiPageCreator.setyAxis(Y_AXIS);
        uiPageCreator.setViewName(VIEW_NAME);
        uiPageCreator.setReleaseName(RELEASE_NAME);
        uiPageCreator.setTitle(TITLE);
        uiPageCreator.setStartTime(DATE);
    }

    @AfterClass
    public static void tearDown() {
        uiPageCreator = null;
    }

    @Test
    public void testGeneratedInterestApiEndpoint() {
        out.println(uiPageCreator.getApiUrl());
        Assert.assertTrue(uiPageCreator.getApiUrl().equals(INTEREST_BASE_URL + API_ENDPOINT));
    }

    @Test
    public void testXmlConversion() {

        String xmlRepresentation = uiPageCreator.toXml();
        out.println(xmlRepresentation);
        xmlRepresentation = xmlRepresentation.replaceAll("\\s+", "");

        String expectedXml = String.format(
                "<uiPage>\n" +
                "  <name>%s</name>\n" +
                "  <xAxis>%s</xAxis>\n" +
                "  <yAxis>%s</yAxis>\n" +
                "  <viewName>%s</viewName>\n" +
                "  <releaseName>%s</releaseName>\n" +
                "  <startTime>%s</startTime>\n" +
                "  <title>%s</title>\n" +
                "</uiPage>", NAME, X_AXIS, Y_AXIS, VIEW_NAME, RELEASE_NAME,
                DateUtils.convertDate(DATE, DateUtils.ISO_8601_24H_FULL_DATE_FORMAT),
                TITLE
         ).replaceAll("\\s+", "");

        Assert.assertTrue(expectedXml.equals(xmlRepresentation));
    }
}
