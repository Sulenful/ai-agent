package com.lingfeng.test.spring.ai;

import com.alibaba.fastjson.JSON;
import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.client.transport.ServerParameters;
import io.modelcontextprotocol.client.transport.StdioClientTransport;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lingfeng Su
 * @date 2026/1/20
 * @description
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class FlowAgentMCPTest {

    @Test
    public void test() {
        OpenAiChatModel chatModel = OpenAiChatModel.builder()
                .openAiApi(OpenAiApi.builder()
                        .baseUrl("https://apis.itedus.cn")
                        .apiKey("sk-BdtBBcPi6FjhU7aBBfFdC9B137834115B54a48Ba2d9021C4")
                        .completionsPath("v1/chat/completions")
                        .embeddingsPath("v1/embeddings")
                        .build())
                .defaultOptions(OpenAiChatOptions.builder()
                        .model("gpt-4.1")
                        .toolCallbacks(new SyncMcpToolCallbackProvider(stdioMcpClientElasticsearch()).getToolCallbacks())
                        .build())
                .build();
        ChatResponse call = chatModel.call(Prompt.builder().messages(new UserMessage("有哪些工具可以使用")).build());
        log.info("测试结果:{}", JSON.toJSONString(call.getResult()));
    }

    public McpSyncClient stdioMcpClientElasticsearch() {
        Map<String, String> env = new HashMap<>();
        env.put("ES_HOST", "http://115.190.141.182:9200");
        env.put("ES_API_KEY", "none");
        var stdioParams = ServerParameters.builder("npx")
                .args("-y", "@awesome-ai/elasticsearch-mcp")
                .env(env)
                .build();
        var mcpClient = McpClient.sync(new StdioClientTransport(stdioParams))
                .requestTimeout(Duration.ofSeconds(100)).build();
        var init = mcpClient.initialize();
        System.out.println("Stdio MCP Initialized: " + init);
        return mcpClient;
    }
}
