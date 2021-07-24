package ru.otus.amezgin.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.SubscribableChannel;

@Configuration
public class ChannelConfig {
    Logger logger = LoggerFactory.getLogger(ChannelConfig.class);

    @Bean
    public PollableChannel itemsChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean
    public SubscribableChannel centralChannel() {
        SubscribableChannel channel = MessageChannels.publishSubscribe().get();

        channel.subscribe(msg -> {
            logger.info("Были обнаружены повстанцы на планете - " + msg.getPayload());
        });

        return channel;
    }

    @Bean
    public SubscribableChannel reportChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(100).maxMessagesPerPoll(2).get();
    }

    @Bean
    public IntegrationFlow cafeFlow() {
        return IntegrationFlows.from("itemsChannel")
                .split()
                .channel("centralChannel")
                .handle("centralServiceImpl", "sendPurpose")
                .aggregate()
                .channel("reportChannel")
                .get();
    }
}
