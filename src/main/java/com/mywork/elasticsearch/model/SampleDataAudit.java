package com.mywork.elasticsearch.model;

import java.util.List;
import java.util.Map;

public class SampleDataAudit
{

    List<Map<String, Object>> audits;

    public List<Map<String, Object>> getAudits()
    {
        return audits;
    }

    public void setAudits(List<Map<String, Object>> audits)
    {
        this.audits = audits;
    }

}
