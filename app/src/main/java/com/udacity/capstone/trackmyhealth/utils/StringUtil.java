package com.udacity.capstone.trackmyhealth.utils;

import android.media.MediaCodecInfo;
import android.util.Base64;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udacity.capstone.trackmyhealth.database.Medication;

import java.io.IOException;

public class StringUtil {

    //  Parse using Jackson
    //  Reference - https://www.concretepage.com/jackson-api/read-write-json-using-jackson-objectmapper-jsonparser-jsongenerator-example
    public static String toBase64String(Medication medication) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return Base64.encodeToString(mapper.writeValueAsBytes(medication), 0);
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
    public static Medication fromBase64(String encoded) {
        if (!"".equals(encoded)) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                return mapper.readValue(Base64.decode(encoded, 0), Medication.class);
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
