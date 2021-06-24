package com.kel3.yfaexpress.controller.restapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kel3.yfaexpress.model.dto.CostDto;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/cost")
public class ApiCost {

    @GetMapping("/{asal}/{tujuan}/{berat}")
    public List<CostDto> cost(@PathVariable String asal, @PathVariable String tujuan, @PathVariable String berat) throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "origin=" + asal + "&destination=" + tujuan + "&weight=" + berat + "&courier=jne");
        Request request = new Request.Builder()
                .url("https://api.rajaongkir.com/starter/cost")
                .post(body)
                .addHeader("key", "94680de3d5d88d1707618bef65fb29b7")
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build();

        Response response = client.newCall(request).execute();
        String jsonData = response.body().string();
        JSONObject jsonObject = new JSONObject(jsonData).getJSONObject("rajaongkir");
        JSONArray jsonResults = jsonObject.getJSONArray("results");
        JSONObject jsonParentCost = jsonResults.getJSONObject(0);
        String jsonCost = jsonParentCost.getJSONArray("costs").toString();
        ObjectMapper mapper = new ObjectMapper();
        List<CostDto> costDtoList = mapper.readValue(jsonCost, new TypeReference<List<CostDto>>(){});
        return costDtoList;
    }


}
