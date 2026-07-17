package com.ScheduleMaker.ScheduleMaker.Helpers;

import com.ScheduleMaker.ScheduleMaker.DataTransferObjects.TopicDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public class AiResponseParserHelper {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private AiResponseParserHelper() {
    }

    public static ArrayList<TopicDTO> parseTopics(String aiResponse) {
        if (aiResponse == null || aiResponse.isBlank()) {
            return new ArrayList<>();
        }

        String jsonPayload = extractJsonPayload(aiResponse);
        if (jsonPayload.isBlank()) {
            return new ArrayList<>();
        }

        try {
            JsonNode root = OBJECT_MAPPER.readTree(jsonPayload);
            JsonNode topicsNode = root.path("topics");
            if (!topicsNode.isArray()) {
                return new ArrayList<>();
            }

            ArrayList<TopicDTO> topics = new ArrayList<>();
            for (JsonNode topicNode : topicsNode) {
                TopicDTO topic = new TopicDTO();
                topic.setOrderIndex(readInt(topicNode, "order_index"));
                topic.setTopicName(readString(topicNode, "name"));
                topic.setMinHours(readDouble(topicNode, "min_hours"));
                topic.setBasePriority(readInt(topicNode, "base_priority"));
                topics.add(topic);
            }
            return topics;
        } catch (JsonProcessingException e) {
            return new ArrayList<>();
        }
    }

    private static String extractJsonPayload(String aiResponse) {
        String trimmed = aiResponse.trim();
        if (trimmed.startsWith("```") && trimmed.contains("```")) {
            int firstFence = trimmed.indexOf("```");
            int secondFence = trimmed.lastIndexOf("```");
            if (secondFence > firstFence) {
                String inside = trimmed.substring(firstFence + 3, secondFence).trim();
                if (inside.startsWith("json")) {
                    inside = inside.substring(4).trim();
                }
                return inside;
            }
        }

        int firstBrace = trimmed.indexOf('{');
        int lastBrace = trimmed.lastIndexOf('}');
        if (firstBrace >= 0 && lastBrace > firstBrace) {
            return trimmed.substring(firstBrace, lastBrace + 1);
        }

        return trimmed;
    }

    private static String readString(JsonNode node, String fieldName) {
        JsonNode value = node.path(fieldName);
        return value.isMissingNode() || value.isNull() ? null : value.asText();
    }

    private static Integer readInt(JsonNode node, String fieldName) {
        JsonNode value = node.path(fieldName);
        return value.isMissingNode() || value.isNull() ? null : value.asInt();
    }

    private static Double readDouble(JsonNode node, String fieldName) {
        JsonNode value = node.path(fieldName);
        return value.isMissingNode() || value.isNull() ? null : value.asDouble();
    }
}
