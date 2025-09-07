package com.example.serwis_pdf.component;

import com.example.serwis_pdf.model.PersonData;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class OpenAiClient {
    @Value("${openai.api-key}")
    private String apiKey;
    private final String endpoint = "https://api.openai.com/v1/chat/completions";
    private final RestTemplate restTemplate = new RestTemplate();

    public <T> List<T> extractData(String text, String prompt, Class<T> clazz) {
        Map<String, Object> request = new HashMap<>();
        request.put("model", "gpt-4o-mini");
        List<Map<String, String>> messages = List.of(
                Map.of(
                        "role", "system",
                        "content", prompt
                ),
                Map.of("role", "user", "content", text)
        );
        request.put("messages", messages);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(endpoint, entity, Map.class);
        List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");
        String content = (String) ((Map<String, Object>) choices.get(0).get("message")).get("content");
        log.info(content);
        if (content == null || content.isEmpty()) {
            throw new RuntimeException("No content returned from OpenAI API");
        }
        if (!content.startsWith("{") && !content.startsWith("[") || !content.endsWith("}") && !content.endsWith("]")) {
            throw new RuntimeException("Invalid JSON format returned from OpenAI API: " + content);
        }
        List<T> dataList = new java.util.ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(content);
            if (jsonNode.isArray()) {
                for (JsonNode node : jsonNode) {
                    T data = objectMapper.treeToValue(node, clazz);
                    dataList.add(data);
                }
            } else if (jsonNode.isObject()) {
                T data = objectMapper.treeToValue(jsonNode, clazz);
                dataList.add(data);
            } else {
                throw new RuntimeException("Nieoczekiwany format JSON z OpenAI API: " + content);
            }
        } catch (Exception e) {
            log.error("Błąd podczas parsowania odpowiedzi OpenAI: ", e);
        }
        log.info(dataList.toString());
        return dataList;
    }
}
