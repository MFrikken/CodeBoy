package com.codeboy.model.weakness;

import com.fasterxml.jackson.databind.JsonNode;
import com.codeboy.model.vulnerability.VulnerabilityModel;
import com.codeboy.utility.JsonParser;

import jakarta.persistence.*;

@Entity
@Table(name = "weaknesses")
public class WeaknessModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vulnerability_id", nullable = false)
    private VulnerabilityModel vulnerability;

    private String type;

    @Column(name = "name")
    private String name;

    @Column(name = "weakness_value")
    private String value;

    private String url;

    public WeaknessModel() {}

    public WeaknessModel(VulnerabilityModel vulnerability) {
        this.vulnerability = vulnerability;
    }

    public WeaknessModel(VulnerabilityModel vulnerability, String type, String name, String value, String url) {
        this.vulnerability = vulnerability;
        this.type = type;
        this.name = name; 
        this.value = value; 
        this.url = url;
    }

    public Integer getId() { return id; }
    public VulnerabilityModel getVulnerability() { return vulnerability; }
    public String getType() { return type; }
    public String getName() { return name; }
    public String getValue() { return value; }
    public String getUrl() { return url; }

    public WeaknessDto toWeaknessDto() {
        return new WeaknessDto(type, name, value, url);
    }

    public static WeaknessModel fromJsonNode(VulnerabilityModel vulnerability, JsonNode jsonNode) {
        WeaknessModel model = new WeaknessModel(vulnerability);
        model.type = jsonNode.get("type").asText();
        model.name = jsonNode.get("name").asText();
        model.value = jsonNode.get("value").asText();
        model.url = jsonNode.has("url") && !jsonNode.get("url").isNull() ? jsonNode.get("url").asText() : null;
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
        result = 31 * result + vulnerability.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + value.hashCode();
        result = 31 * result + url.hashCode();
        return result;
    }
}
