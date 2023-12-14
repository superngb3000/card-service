package com.superngb.cardservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "task-service", url = "${feign_client.task_service.url}")
public interface TaskServiceClient {
    @DeleteMapping("/deleteByCard/{id}")
    ResponseEntity<?> deleteTasksByCard(@PathVariable Long id);
}
