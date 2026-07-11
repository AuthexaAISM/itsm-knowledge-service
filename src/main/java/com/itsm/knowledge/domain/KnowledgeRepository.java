package com.itsm.knowledge.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KnowledgeRepository extends JpaRepository<KnowledgeArticle, String> {
    List<KnowledgeArticle> findByTenantId(String tenantId);
    Optional<KnowledgeArticle> findByIdAndTenantId(String id, String tenantId);
    List<KnowledgeArticle> findByTenantIdAndTitleContainingIgnoreCase(String tenantId, String title);
}
