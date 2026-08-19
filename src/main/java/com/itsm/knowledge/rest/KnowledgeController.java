package com.itsm.knowledge.rest;

import com.itsm.knowledge.domain.KnowledgeArticle;
import com.itsm.knowledge.service.KnowledgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tenants/{tenantId}/knowledge/articles")
@RequiredArgsConstructor
public class KnowledgeController {

    private final KnowledgeService knowledgeService;

    @PostMapping
    public ResponseEntity<KnowledgeArticle> createArticle(
            @PathVariable String tenantId,
            @RequestBody KnowledgeArticle article) {
        return ResponseEntity.ok(knowledgeService.createArticle(tenantId, article));
    }

    @GetMapping
    public ResponseEntity<List<KnowledgeArticle>> getAllArticles(@PathVariable String tenantId) {
        return ResponseEntity.ok(knowledgeService.getAllArticles(tenantId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<KnowledgeArticle>> searchArticles(
            @PathVariable String tenantId,
            @RequestParam String q) {
        return ResponseEntity.ok(knowledgeService.searchArticles(tenantId, q));
    }

    @GetMapping("/{id}")
    public ResponseEntity<KnowledgeArticle> getArticle(
            @PathVariable String tenantId,
            @PathVariable String id) {
        return knowledgeService.getArticle(id, tenantId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/publish")
    public ResponseEntity<KnowledgeArticle> publishArticle(
            @PathVariable String tenantId,
            @PathVariable String id) {
        return ResponseEntity.ok(knowledgeService.publishArticle(id, tenantId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<KnowledgeArticle> updateArticle(
            @PathVariable String tenantId,
            @PathVariable String id,
            @RequestBody KnowledgeArticle update) {
        return ResponseEntity.ok(knowledgeService.updateArticle(id, tenantId, update));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(
            @PathVariable String tenantId,
            @PathVariable String id) {
        knowledgeService.deleteArticle(id, tenantId);
        return ResponseEntity.noContent().build();
    }
}
