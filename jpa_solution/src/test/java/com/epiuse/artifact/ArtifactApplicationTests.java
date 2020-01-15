package com.epiuse.artifact;

import com.epiuse.datasource.DBDetails;
import com.epiuse.datasource.DbContextHolder;
import com.epiuse.models.PayGroupBase;
import com.epiuse.models.vantage.PayGroup;
import com.epiuse.repository.PayGroupRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@SpringBootTest
@Configuration
@EnableJpaRepositories(basePackages = "com.epiuse.repository")
@EnableAutoConfiguration
class ArtifactApplicationTests {

    @Autowired
    PayGroupRepository payGroupRepository;

    @Autowired
    private PlatformTransactionManager transactionManager; // Hierdie is belangrik

    @Test
    @Transactional
    public void testTenantDataVantage() {
        // Lookup and set database details from ClientId
        DbContextHolder.setDbType(new DBDetails("com.epiuse.models.vantage", "vantage"));

        payGroupRepository.save(new PayGroup(1, "vantage-11"));
        payGroupRepository.save(new PayGroup(2, "vantage-12"));
        payGroupRepository.save(new PayGroup(3, "vantage-13"));

        Assert.isTrue(payGroupRepository.findById(1l).get().getName().equals("vantage-11"), "Not found");
    }

    @Test
    @Transactional
    public void testTenantDataEnterprise() {
        // Lookup and set database details from ClientId
        DbContextHolder.setDbType(new DBDetails("com.epiuse.models.ev6", "enterprise"));

        payGroupRepository.save(new PayGroup(1, "enterprise-21"));
        payGroupRepository.save(new PayGroup(2, "enterprise-22"));
        payGroupRepository.save(new PayGroup(3, "enterprise-23"));

        Assert.isTrue(payGroupRepository.findById(1l).get().getName().equals("enterprise-21"), "Not found");
    }

//
//    @Test
//    @Transactional
//    public void testTenantDataBoth() {
//        DbContextHolder.setDbType(new DBDetails("com.epiuse.models.vantage", "vantage"));
//
//        payGroupRepository.save(new PayGroup(1, "vantage-11"));
//        payGroupRepository.save(new PayGroup(2, "vantage-12"));
//        payGroupRepository.save(new PayGroup(3, "vantage-13"));
//
//        Assert.isTrue(payGroupRepository.findById(1l).get().getName().equals("vantage-11"), "Not found");
//
//        payGroupRepository.save(new PayGroup(1, "enterprise-21"));
//        payGroupRepository.save(new PayGroup(2, "enterprise-22"));
//        payGroupRepository.save(new PayGroup(3, "enterprise-23"));
//
//        Assert.isTrue(payGroupRepository.findById(1l).get().getName().equals("enterprise-21"), "Not found");
//    }

}
