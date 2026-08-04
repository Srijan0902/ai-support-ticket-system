package com.srijan.supportticketsystem.ai;

import org.springframework.stereotype.Service;

@Service
public class GeminiServiceImpl implements GeminiService {

    private final GeminiConfig geminiConfig;

    public GeminiServiceImpl(GeminiConfig geminiConfig) {
        this.geminiConfig = geminiConfig;
    }

    @Override
    public String classifyTicket(String description) {

        // Abhi temporary response
        return "Category: GENERAL, Priority: MEDIUM";
    }
}