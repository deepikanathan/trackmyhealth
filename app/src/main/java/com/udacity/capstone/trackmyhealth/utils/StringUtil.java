package com.udacity.capstone.trackmyhealth.utils;

import android.util.Base64;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udacity.capstone.trackmyhealth.database.HealthData;

import java.io.IOException;

public class StringUtil {

    //  Parse using Jackson
    //  Reference - https://www.concretepage.com/jackson-api/read-write-json-using-jackson-objectmapper-jsonparser-jsongenerator-example
    public static String toBase64String(HealthData healthData) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return Base64.encodeToString(mapper.writeValueAsBytes(healthData), 0);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //  Parse using Jackson
    //  Reference - https://www.concretepage.com/jackson-api/read-write-json-using-jackson-objectmapper-jsonparser-jsongenerator-example
    public static HealthData fromBase64(String encoded) {
        if (!"".equals(encoded)) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                byte[] decode = Base64.decode(encoded, 0);
                return mapper.readValue(decode, HealthData.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
