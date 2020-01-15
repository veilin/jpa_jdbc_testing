package com.epiuse.repository;

import com.epiuse.models.IPayGroup;
import com.epiuse.models.PayGroupBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayGroupRepository<R extends PayGroupBase> extends JpaRepository<R, Long> {

}
