package com.sperasoft.sie.tools.interest;

import static java.lang.System.out;

import com.sperasoft.sie.tools.util.DateUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.ZonedDateTime;

/**
 * Created by Ilya K on 7/17/2017.
 */
public class UiPageTest {

    private static UiPage uiPage;


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
        uiPage = new UiPage(INTEREST_BASE_URL);
        uiPage.setName(NAME);
        uiPage.setxAxis(X_AXIS);
        uiPage.setyAxis(Y_AXIS);
        uiPage.setViewName(VIEW_NAME);
        uiPage.setReleaseName(RELEASE_NAME);
        uiPage.setTitle(TITLE);
        uiPage.setStartTime(DATE);
    }

    @AfterClass
    public static void tearDown() {
        uiPage = null;
    }

    @Test
    public void testGeneratedInterestApiEndpoint() {
        out.println(uiPage.getApiUrl());
        Assert.assertTrue(uiPage.getApiUrl().equals(INTEREST_BASE_URL + API_ENDPOINT));
    }

    @Test
    public void testXmlConversion() {

        String xmlRepresentation = uiPage.toXml();
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
