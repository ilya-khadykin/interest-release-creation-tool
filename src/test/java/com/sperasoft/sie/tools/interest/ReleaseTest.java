package com.sperasoft.sie.tools.interest;

import static java.lang.System.out;

import com.sperasoft.sie.tools.util.DateUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.ZonedDateTime;

/**
 * Created by Ilya K on 7/16/2017.
 */
public class ReleaseTest {

    private static Release release;
    private static final String NAME = "Release API Test IK";
    private static final String PROJECT_NAME = "NPMT";
    private static final String LINE = "Q1";
    private static final ZonedDateTime DATE = ZonedDateTime.now();

    private static final String INTEREST_BASE_URL = "http://qa.interest.spb.sperasoft.com";
    private static final String API_ENDPOINT = "/interest/api/collector/release/" + NAME.replace(" ", "%20");

    @BeforeClass
    public static void setUp() {
        release = new Release(INTEREST_BASE_URL);
        release.setName(NAME);
        release.setProjectName(PROJECT_NAME);
        release.setLine(LINE);
        release.setStartTime(DATE);
        release.setEndTime(DATE);
    }

    @AfterClass
    public static void tearDown() {
        release = null;
    }

    @Test
    public void testGeneratedInterestApiEndpoint() {
        out.println(release.getApiUrl());
        Assert.assertTrue(release.getApiUrl().equals(INTEREST_BASE_URL + API_ENDPOINT));
    }

    @Test
    public void testXmlConversion() {

        String xmlRepresentation = release.toXml();
        out.println(xmlRepresentation);
        xmlRepresentation = xmlRepresentation.replaceAll("\\s+", "");

        String expectedXml = String.format(
                "<release>\n" +
                "  <name>%s</name>\n" +
                "  <projectName>%s</projectName>\n" +
                "  <line>%s</line>\n" +
                "  <startTime>%s</startTime>\n" +
                "  <endTime>%s</endTime>\n" +
                "</release>", NAME, PROJECT_NAME, LINE,
                DateUtils.convertDate(DATE, DateUtils.ISO_8601_24H_FULL_DATE_FORMAT),
                DateUtils.convertDate(DATE, DateUtils.ISO_8601_24H_FULL_DATE_FORMAT))
                .replaceAll("\\s+", "");

        Assert.assertTrue(expectedXml.equals(xmlRepresentation));
    }
}
