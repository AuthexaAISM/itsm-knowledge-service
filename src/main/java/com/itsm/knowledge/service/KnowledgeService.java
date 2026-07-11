package com.itsm.knowledge.service;

import com.itsm.knowledge.domain.KnowledgeArticle;
import com.itsm.knowledge.domain.KnowledgeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class KnowledgeService {

    private final KnowledgeRepository knowledgeRepository;

    @Transactional
    public KnowledgeArticle createArticle(String tenantId, KnowledgeArticle article) {
        article.setTenantId(tenantId);
        return knowledgeRepository.save(article);
    }

    public List<KnowledgeArticle> getAllArticles(String tenantId) {
        return knowledgeRepository.findByTenantId(tenantId);
    }

    public Optional<KnowledgeArticle> getArticle(String id, String tenantId) {
        return knowledgeRepository.findByIdAndTenantId(id, tenantId);
    }

    public List<KnowledgeArticle> searchArticles(String tenantId, String query) {
        return knowledgeRepository.findByTenantIdAndTitleContainingIgnoreCase(tenantId, query);
    }

    @Transactional
    public KnowledgeArticle publishArticle(String id, String tenantId) {
        KnowledgeArticle article = getArticle(id, tenantId)
                .orElseThrow(() -> new RuntimeException("Article not found"));
        article.setStatus("PUBLISHED");
        return knowledgeRepository.save(article);
    }
}
