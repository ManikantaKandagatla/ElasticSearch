package com.mywork.elasticsearch.dao;

import java.io.IOException;
import java.util.*;

import com.sun.org.apache.xerces.internal.xs.StringList;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.transport.TransportRequestOptions;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mywork.elasticsearch.model.SampleData;

import org.springframework.stereotype.Repository;

/**
 * @author Manikanta Kandagatla
 */
@Repository
public class SampleDataDao
{
    public static RestTemplate restTemplate = null;

    public static final String baseUrl = "http://localhost:9300/";

    public static Gson gson;

    public static HttpHeaders headers;

    private final String BASEINDEX = "data";

    private RestHighLevelClient restHighLevelClient;

    private ObjectMapper objectMapper;

    static
    {
        restTemplate = new RestTemplate();
        gson = new Gson();
        headers = new HttpHeaders();

    }

    public SampleDataDao(ObjectMapper objectMapper, RestHighLevelClient restHighLevelClient)
    {
        this.objectMapper = objectMapper;
        this.restHighLevelClient = restHighLevelClient;
    }

    public void pumpData(List<SampleData> SampleDataList)
    {
        for (SampleData sampledata : SampleDataList)
        {
            Map<String, Object> dataMap = objectMapper.convertValue(sampledata, Map.class);
            Map<String, Object> oldData = getBySampleDataId(sampledata.getDataid());
            UpdateResponse response = executeUpdateRequest(BASEINDEX + "_" + sampledata.getType(), sampledata.getType(),
                    sampledata.getDataid(), dataMap);
            if (response != null && oldData != null)
            {
                Map<String, Object> auditdata = getSampleDataAuditbyId(sampledata.getDataid());
                if (auditdata != null)
                {
                    List<Map<String, Object>> audits = (List<Map<String, Object>>) auditdata
                            .get("audits");
                    audits.add(oldData);
                    auditdata.put("audits", audits);
                }
                else
                {
                    auditdata = new HashMap<String, Object>();
                    List<Map<String, Object>> audits = new ArrayList<Map<String, Object>>();
                    audits.add(oldData);
                    auditdata.put("audits", audits);
                }
                executeUpdateRequest(BASEINDEX + "_" + sampledata.getType() + "_audit", sampledata.getType(),
                        sampledata.getDataid(), auditdata);
            }
            System.out.println(dataMap);
        }
    }

    public UpdateResponse executeUpdateRequest(String index, String type, String id,
            Map<String, Object> dataMap)
    {
        UpdateResponse updateResponse = null;
        UpdateRequest updateRequest = new UpdateRequest(index, type, id).upsert(dataMap)
                .doc(dataMap);
        // IndexRequest indexRequest = new IndexRequest(BASEINDEX + "_" +type ,"cta"
        // ,data.getCtaid())
        // .source(dataMap);

        try
        {
            // IndexResponse response = restHighLevelClient.index(indexRequest);
            updateResponse = restHighLevelClient.update(updateRequest);

        }
        catch (ElasticsearchException e)
        {
            e.getDetailedMessage();
        }
        catch (java.io.IOException ex)
        {
            ex.getLocalizedMessage();
        }
        catch (Exception e)
        {
            System.out.println("Exception caused");
        }
        return updateResponse;
    }

    public Map<String, Object> getSampleDataAuditbyId(String dataid)
    {
        Map<String, Object> ctaAudit = null;
        GetRequest getRequest = new GetRequest(BASEINDEX + "_box_audit", "box", dataid);
        try
        {
            GetResponse response = restHighLevelClient.get(getRequest);
            ctaAudit = response.getSource();
        }
        catch (ElasticsearchException e)
        {
            System.out.println(e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return ctaAudit;
    }

    public Map<String, Object> getBySampleDataId(String dataid)
    {
        Map<String, Object> cta = null;
        GetRequest getRequest = new GetRequest(BASEINDEX + "_box", "box", dataid);
        try
        {
            GetResponse response = restHighLevelClient.get(getRequest);
            cta = response.getSource();
        }
        catch (ElasticsearchException e)
        {
            System.out.println(e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return cta;
    }

}
