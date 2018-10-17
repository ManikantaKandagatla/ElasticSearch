package com.mywork.elasticsearch.model;

/**
 * @author Manikanta Kandagatla
 */
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SampleData
{
    public SampleData()
    {
        // TODO Auto-generated constructor stub
    }

    private String dataid;

    private String name;

    private String type;

    private int cost;
    
    private String modifiedby;

    private String description;

    public String getDataid()
    {
        return dataid;
    }

    public void setDataid(String dataid)
    {
        this.dataid = dataid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    
    public int getCost()
    {
        return cost;
    }

    public void setCost(int cost)
    {
        this.cost = cost;
    }

    public String getModifiedby()
    {
        return modifiedby;
    }

    public void setModifiedby(String modifiedby)
    {
        this.modifiedby = modifiedby;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @Override
    public String toString()
    {
        return "SampleData {" + "name='" + name + '\'' + ", type='" + type + '\'' + ", status='"+"', modifiedby='" + modifiedby + '\'' + ", description='"
                + description + '\'' + '}';
    }
}
