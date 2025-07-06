package com.sage.model.weakness;

import com.fasterxml.jackson.databind.JsonNode;
import com.sage.utility.JsonParser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "weaknesses")
public class WeaknessModel {
    @Id
    private Integer id;

    private Integer vulnerabilityId;
    private String type;

    @Column(name = "name")
    private String name;

    @Column(name = "weakness_value")
    private String value;

    private String url;

    public WeaknessModel() {}

    public WeaknessModel(Integer id, Integer vulnerabilityId) {
        this.id = id;
        this.vulnerabilityId = vulnerabilityId;
    }

    public WeaknessModel(Integer id, Integer vulnerabilityId, String type, String name, String value, String url) {
        this. id = id;
        this.vulnerabilityId = vulnerabilityId;
        this.type = type;
        this.name = name; 
        this.value = value; 
        this.url = url;
    }

    public Integer getId() { return id; }
    public Integer getVulnerabilityId() { return vulnerabilityId; }
    public String getType() { return type; }
    public String getName() { return name; }
    public String getValue() { return value; }
    public String getUrl() { return url; }

    public WeaknessDto toWeaknessDto() {
        return new WeaknessDto(type, name, value, url);
    }

    public static WeaknessModel fromJsonNode(Integer id, Integer vulnerabilityId, JsonNode jsonNode) {
        WeaknessModel model = new WeaknessModel(id, vulnerabilityId);
        model.type = jsonNode.get("type").asText();
        model.name = jsonNode.get("name").asText();
        model.value = jsonNode.get("value").asText();
        model.url = jsonNode.get("url").asText();
        return model;
    }

    @Override
    public String toString() {
        return JsonParser.asJsonString(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        
        if (!(o instanceof WeaknessModel))
            return false;

        WeaknessModel weaknessModel = (WeaknessModel) o;
        return weaknessModel.getId() == this.id;
    }

    @Override 
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + vulnerabilityId.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + value.hashCode();
        result = 31 * result + url.hashCode();
        return result;
    }
}
