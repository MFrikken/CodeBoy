package com.sage.model.weakness;

import java.io.Serializable;

import com.sage.model.vulnerability.VulnerabilityModel;
import com.sage.utility.JsonParser;

/**
 * Represents a unique identifier of a vulnerability in the sast-report-file.
 * 
 * @author MFrinken
 */
public class WeaknessDto {
   private final String type; // cwe, owasp, ...
   private final String name; // identifying name
   private final String value; // unique value (part of name)
   private final String url; // reference to online documentation

   public WeaknessDto(String type, String name, String value, String url) {
      this.type = type;
      this.name = name;
      this.value = value;
      this.url = url;
   }

   public static WeaknessDto factory() {
      return null;
   }

   public String getType() {
      return type;
   }

   public String getName() {
      return name;
   }

   public String getValue() {
      return value;
   }

   public String getUrl() {
      return url;
   }

   public WeaknessModel toModel(VulnerabilityModel vulnerabilityModel) {
      return new WeaknessModel(vulnerabilityModel, type, name, value, url);
   }

   @Override
   public String toString() {
      return JsonParser.asJsonString(this);
   }

   @Override
   public boolean equals(Object o) {
      if (o == this)
         return true;

      if (!(o instanceof WeaknessDto))
         return false;

      WeaknessDto weaknessDto = (WeaknessDto) o;
      return weaknessDto.getValue().equals(this.value);
   }

   @Override
   public int hashCode() {
      int result = value.hashCode();
      result = 31 * result + type.hashCode();
      result = 31 * result + name.hashCode();
      result = 31 * result + url.hashCode();
      return result;
   }
}
