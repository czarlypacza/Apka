package com.example.serwis_pdf.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

@Component
public class PromptLoader {
    @Value("classpath:prompts.yml")
    private Resource promptsResource;

    public String getPrompt(String key) {
        try (InputStream is = promptsResource.getInputStream()) {
            Yaml yaml = new Yaml();
            Map<String, Object> data = yaml.load(is);
            Object prompt = data.get(key);
            return prompt != null ? prompt.toString() : null;
        } catch (Exception e) {
            throw new RuntimeException("Nie można wczytać promptu: " + key, e);
        }
    }
}

