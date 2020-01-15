package com.epiuse.repository;

import com.epiuse.models.IPayGroup;
import com.epiuse.models.PayGroupBase;
import com.epiuse.models.vantage.PayGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayGroupRepository extends CrudRepository<PayGroup, Long>{
}
