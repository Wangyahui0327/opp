package com.example.oopandroidapi;
import android.content.Context;
import android.util.Log;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MunicipalityDataRetriever {

    private static final String BASEURL = "https://pxdata.stat.fi:443/PxWeb/api/v1/en/StatFin/tyokay/statfin_tyokay_pxt_125s.px";
    private static final String BASEURL_TEACHER = "https://pxdata.stat.fi:443/PxWeb/api/v1/en/StatFin/synt/statfin_synt_pxt_12dy.px";
    private static final String BASEURL_TEACHER2 = "https://pxdata.stat.fi:443/PxWeb/api/v1/en/StatFin/tyokay/statfin_tyokay_pxt_125s.px";
    static ObjectMapper objectMapper = new ObjectMapper();

    static HashMap<String, String> municipalityNamesToCodesMap = null;

    /**
     * Get municipality codes, we need to do this only once
     *
     */
    public static HashMap<String,String> getMunicipalityCodesMap() {
        if (municipalityNamesToCodesMap == null) {
            JsonNode areas = readAreaDataFromTheAPIURL(objectMapper);
            municipalityNamesToCodesMap = createMunicipalityNamesToCodesMap(areas);
        }
        return municipalityNamesToCodesMap;
    }
    public WorkData getWorkplaceAndEmploymentData(Context context, String municipalityName){
        String code = getMunicipalityCodesMap().get(municipalityName);
        try {
            // The query for fetching data from a single municipality is stored in query.json
            JsonNode jsonQuery = objectMapper.readTree(context.getResources().openRawResource(R.raw.workplaceselfsufficiency));
            // Let's replace the municipality code in the query with the municipality that the user gave
            // as input
            ((ObjectNode) jsonQuery.findValue("query").get(1).get("selection")).putArray("values").add(code);

            HttpURLConnection con = connectToAPIAndSendPostRequest(objectMapper, jsonQuery,new URL(BASEURL_TEACHER2));

            try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }

                JsonNode workAndEmploymentData = objectMapper.readTree(response.toString());
                WorkData workData = new WorkData();

             //   Log.d("LUTPROJECT",workAndEmploymentData.toString());
                JsonNode value = workAndEmploymentData.get("value");
                Log.d("LUTPROJECT  value",value.get(0).toString());
                workData.setWorkplaceSelfSufficiency(new BigDecimal(value.get(0).toString()).setScale(2));

                return workData;
                //System.out.println(municipalityData.toPrettyString());

                // System.out.println(populations.toPrettyString());
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public String getEmploymentRatesData(Context context, String municipalityName){
        String code = getMunicipalityCodesMap().get(municipalityName);
        try {
            // The query for fetching data from a single municipality is stored in query.json
            JsonNode jsonQuery = objectMapper.readTree(context.getResources().openRawResource(R.raw.test));
            // Let's replace the municipality code in the query with the municipality that the user gave
            // as input
            ((ObjectNode) jsonQuery.findValue("query").get(0).get("selection")).putArray("values").add(code);

            HttpURLConnection con = connectToAPIAndSendPostRequest(objectMapper, jsonQuery,new URL("https://pxdata.stat.fi:443/PxWeb/api/v1/en/StatFin/tyokay/statfin_tyokay_pxt_115x.px"));

            try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }

                JsonNode workAndEmploymentData = objectMapper.readTree(response.toString());
                WorkData workData = new WorkData();

                //   Log.d("LUTPROJECT",workAndEmploymentData.toString());
                JsonNode value = workAndEmploymentData.get("value");
                Log.d("LUTPROJECT  value",value.get(0).toString());

                return value.get(0).toString();
                //System.out.println(municipalityData.toPrettyString());

                // System.out.println(populations.toPrettyString());
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    public ArrayList<PopulationData> getPopulationData(Context context, String municipalityName) {
        //System.out.println(municipalityNamesToCodesMap);

        String code = getMunicipalityCodesMap().get(municipalityName);

        try {
            // The query for fetching data from a single municipality is stored in query.json
            JsonNode jsonQuery = objectMapper.readTree(context.getResources().openRawResource(R.raw.populationquery));
            // Let's replace the municipality code in the query with the municipality that the user gave
            // as input
            ((ObjectNode) jsonQuery.findValue("query").get(0).get("selection")).putArray("values").add(code);


            HttpURLConnection con = connectToAPIAndSendPostRequest(objectMapper, jsonQuery, new URL(BASEURL_TEACHER));


            try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }

                JsonNode municipalityData = objectMapper.readTree(response.toString());

                ArrayList<String> years = new ArrayList<>();
                JsonNode populations = null;

                //System.out.println(municipalityData.toPrettyString() );

                for (JsonNode node : municipalityData.get("dimension").get("Vuosi")
                        .get("category").get("label")) {
                    years.add(node.asText());
                }

                // System.out.println(years);

                populations = municipalityData.get("value");

                ArrayList<PopulationData> populationData = new ArrayList<>();


                for (int i = 0; i < populations.size(); i++) {
                    Integer population = populations.get(i).asInt();
                    populationData.add(new PopulationData(Integer.parseInt(years.get(i)), population));
                }
                return populationData;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    private static HttpURLConnection connectToAPIAndSendPostRequest(ObjectMapper objectMapper, JsonNode jsonQuery, URL url)
            throws MalformedURLException, IOException, ProtocolException, JsonProcessingException {


        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        try (OutputStream os = con.getOutputStream()) {
            byte[] input = objectMapper.writeValueAsBytes(jsonQuery);
            os.write(input, 0, input.length);
        }
        return con;
    }


    /**
     * In this method, we find all the municipality names and codes from the Json and put them into a HashMap,
     * so that we can get search for the municipality code by providing the municipality name
     *
     * @param areas
     * @return HashMap where municipality name is mapped to municipality code
     */
    private static HashMap<String, String> createMunicipalityNamesToCodesMap(JsonNode areas) {
        JsonNode codes = null;
        JsonNode names = null;

        // Let's store the municipality names as keys, and municipality codes as values in a HashMap
        HashMap<String, String> municipalityNamesToCodesMap = new HashMap<>();

        // Here we find the element "variables", and inside it we have the element "text", that has value "Area".
        // Within the same element, we have the keys "values" which contains the municipality codes (e.g. KU123) as a list
        // and "valueTexts" which contains the municipality names (e.g. Lahti) as a list
        if (areas != null) {
            for (JsonNode node : areas.findValue("variables")) {
                if (node.findValue("text").asText().equals("Area")) {
                    codes = node.findValue("values");
                    names = node.findValue("valueTexts");
                }
            }

            // Here we can assume that the size of names and codes are equal, at there are as many municipality codes
            // as there are municipality names
            for (int i = 0; i < names.size(); i++) {
                String name = names.get(i).asText();
                String code = codes.get(i).asText();
                municipalityNamesToCodesMap.put(name, code);

            }
        }

        return municipalityNamesToCodesMap;
    }


    /**
     * Here we read the all the JSON from the URL to a JsonNode
     * <p>
     * How to improve this: instead of fetching the same data all over again when restarting the app, we could store
     * the areas JSON to a file and read it from there. Then we would only need to fetch it once, if the file does
     * not yet exist.
     *
     * @param objectMapper
     * @return JsonNode with municipality data
     */
    private static JsonNode readAreaDataFromTheAPIURL(ObjectMapper objectMapper) {
        JsonNode areas = null;
        try {
            areas = objectMapper.readTree(new URL(BASEURL_TEACHER));


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return areas;
    }


}
