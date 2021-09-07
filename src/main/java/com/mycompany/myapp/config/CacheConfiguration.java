package com.mycompany.myapp.config;

import java.time.Duration;
import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;
import org.hibernate.cache.jcache.ConfigSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.*;
import tech.jhipster.config.JHipsterProperties;
import tech.jhipster.config.cache.PrefixedKeyGenerator;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration =
            Eh107Configuration.fromEhcacheCacheConfiguration(
                CacheConfigurationBuilder
                    .newCacheConfigurationBuilder(Object.class, Object.class, ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                    .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                    .build()
            );
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.mycompany.myapp.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.mycompany.myapp.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.mycompany.myapp.domain.User.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Authority.class.getName());
            createCache(cm, com.mycompany.myapp.domain.User.class.getName() + ".authorities");
            createCache(cm, com.mycompany.myapp.domain.PersistentToken.class.getName());
            createCache(cm, com.mycompany.myapp.domain.User.class.getName() + ".persistentTokens");
            createCache(cm, com.mycompany.myapp.domain.TenantType.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Tenant.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Tenant.class.getName() + ".tenantProperties");
            createCache(cm, com.mycompany.myapp.domain.Tenant.class.getName() + ".tenantUsers");
            createCache(cm, com.mycompany.myapp.domain.Tenant.class.getName() + ".workQueueTenants");
            createCache(cm, com.mycompany.myapp.domain.URLType.class.getName());
            createCache(cm, com.mycompany.myapp.domain.URL.class.getName());
            createCache(cm, com.mycompany.myapp.domain.TenantPropertyKey.class.getName());
            createCache(cm, com.mycompany.myapp.domain.TenantPropertyKey.class.getName() + ".tenantProperties");
            createCache(cm, com.mycompany.myapp.domain.TenantProperty.class.getName());
            createCache(cm, com.mycompany.myapp.domain.LanUser.class.getName());
            createCache(cm, com.mycompany.myapp.domain.LanUser.class.getName() + ".tenantUsers");
            createCache(cm, com.mycompany.myapp.domain.TenantUser.class.getName());
            createCache(cm, com.mycompany.myapp.domain.TenantUser.class.getName() + ".workQueueTenantUsers");
            createCache(cm, com.mycompany.myapp.domain.WorkTemplate.class.getName());
            createCache(cm, com.mycompany.myapp.domain.WorkTemplate.class.getName() + ".workTemplateItems");
            createCache(cm, com.mycompany.myapp.domain.WorkTemplateItem.class.getName());
            createCache(cm, com.mycompany.myapp.domain.WorkTemplateItem.class.getName() + ".workTemplateItemPreReqs");
            createCache(cm, com.mycompany.myapp.domain.WorkTemplateItemPreReq.class.getName());
            createCache(cm, com.mycompany.myapp.domain.WorkQueueTenant.class.getName());
            createCache(cm, com.mycompany.myapp.domain.WorkQueueTenant.class.getName() + ".workQueueTenantData");
            createCache(cm, com.mycompany.myapp.domain.WorkQueueTenant.class.getName() + ".workQueueTenantPreReqs");
            createCache(cm, com.mycompany.myapp.domain.WorkQueueTenantData.class.getName());
            createCache(cm, com.mycompany.myapp.domain.WorkQueueTenantPreReq.class.getName());
            createCache(cm, com.mycompany.myapp.domain.WorkQueueTenantUser.class.getName());
            createCache(cm, com.mycompany.myapp.domain.WorkQueueTenantUser.class.getName() + ".workQueueTenantUserData");
            createCache(cm, com.mycompany.myapp.domain.WorkQueueTenantUser.class.getName() + ".workQueueTenantUserPreReqs");
            createCache(cm, com.mycompany.myapp.domain.WorkQueueTenantUserData.class.getName());
            createCache(cm, com.mycompany.myapp.domain.WorkQueueTenantUserPreReq.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cache.clear();
        } else {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
