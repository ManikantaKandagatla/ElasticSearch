package com.mywork.elasticsearch.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mywork.elasticsearch.dao.SampleDataDao;
import com.mywork.elasticsearch.model.SampleData;

/**
 * @author Manikanta Kandagatla
 */
@RestController
@RequestMapping("/sampledata")
public class SampleDataController
{
    private SampleDataDao sampleDataDao;

    public SampleDataController(SampleDataDao ctaDao)
    {
        this.sampleDataDao = ctaDao;
    }

    /*
     * @PostMapping public void run() {
     * System.out.println("-------- PostgreSQL " +
     * "JDBC Connection Testing ------------"); try {
     * 
     * Class.forName("org.postgresql.Driver");
     * 
     * } catch (ClassNotFoundException e) {
     * 
     * System.out.println( "Where is your PostgreSQL JDBC Driver? " +
     * "Include in your library path!"); e.printStackTrace(); return;
     * 
     * }
     * 
     * System.out.println("PostgreSQL JDBC Driver Registered!");
     * 
     * Connection connection = null;
     * 
     * try {
     * 
     * connection = DriverManager.getConnection(
     * "jdbc:postgresql://junit.cx9sqcdf0xx8.us-east-1.rds.amazonaws.com/demo_9e895ca5c079411281fdd7a5be3e43b0",
     * "devuser", "FC235761-DDE2-41B9-A33E-210F783A21C1");
     * 
     * } catch (SQLException e) {
     * 
     * System.out.println("Connection Failed! Check output console");
     * e.printStackTrace(); return;
     * 
     * }
     * 
     * if (connection != null) {
     * System.out.println("You made it, take control your database now!");
     * List<CallToAction> sessionsData = CTAService.getSessions();
     * System.out.println(sessionsData); sessionsDao.pumpData1(sessionsData); }
     * else { System.out.println("Failed to make connection!"); } }
     */

    @PostMapping
    public void dumpData(@RequestBody SampleData sampledata)
    {
        sampleDataDao.pumpData(Arrays.asList(sampledata));
    }

    @GetMapping(value = "/{dataid}")
    public Map<String, Object> getSampleData(@PathVariable String dataid)
    {
        return sampleDataDao.getBySampleDataId(dataid);
    }
}
