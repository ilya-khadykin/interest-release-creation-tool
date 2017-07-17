package com.sperasoft.sie.tools.interest;

import com.sperasoft.sie.tools.interest.converters.ReleaseCreatorXmlConverter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Created by Ilya K on 7/16/2017.
 */
public class ReleaseCreator {
    /*
       PUT interest/api/collector/release/{name}
       Parameters:
         + {name} - *name* in URL and in payload xml should match

       Body:
<?xml version="1.0" encoding="UTF-8" standalone="yes" ?>
<release>
  <name>Release API Test IK</name>
  <projectName>NPMT</projectName>
  <line>Q1</line>
  <startTime>2017-02-01T08:00:00.000Z</startTime>
  <endTime>2019-12-30T21:00:00.000Z</endTime>
</release>

       Notes:
         + %20 - encoding for space in URL
     */

    private String interestBaseUrl;
    private static final String API_ENDPOINT = "/interest/api/collector/release/";

    private String name;
    private String projectName;
    private String line;
    private ZonedDateTime startTime;
    private ZonedDateTime endTime;

    public ReleaseCreator(String baseUrl) {
        this.interestBaseUrl = baseUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public ZonedDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(ZonedDateTime startTime) {
        this.startTime = startTime;
    }

    public ZonedDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(ZonedDateTime endTime) {
        this.endTime = endTime;
    }

    public String toXml() {
        XStream xStream = new XStream(new DomDriver());
        xStream.registerConverter(new ReleaseCreatorXmlConverter());
        xStream.alias("release", ReleaseCreator.class);
        return xStream.toXML(this);
    }

    public String getApiUrl() {
        return this.interestBaseUrl + ReleaseCreator.API_ENDPOINT + name.replace(" ", "%20");
    }
}
