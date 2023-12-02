package com.superngb.cardservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "board-service", url = "${feign_client.board_service.url}")
public interface BoardServiceClient {
    @GetMapping("/boardExists/{id}")
    boolean boardExists(@PathVariable Long id);
}