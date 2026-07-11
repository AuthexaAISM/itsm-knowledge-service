package com.itsm.knowledge.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import java.time.LocalDateTime;

@Entity
@Table(name = "knowledge_articles")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FilterDef(name = "tenantFilter", parameters = {@ParamDef(name = "tenantId", type = String.class)})
@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
public class KnowledgeArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "tenant_id", nullable = false)
    private String tenantId;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String tags; // Comma separated

    private String category;

    private String status; // DRAFT, REVIEW, PUBLISHED, ARCHIVED

    private String authorId;

    private Integer views;

    private Integer helpfulCount;

    private Integer notHelpfulCount;

    private String version;

    private String relatedIncidentId; // Used when auto-drafting from resolution

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = "DRAFT";
        }
        if (this.views == null) this.views = 0;
        if (this.helpfulCount == null) this.helpfulCount = 0;
        if (this.notHelpfulCount == null) this.notHelpfulCount = 0;
        if (this.version == null) this.version = "1.0";
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
