package com.bezkoder.spring.security.jwt.repository;

import com.bezkoder.spring.security.jwt.models.ReceiveResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ReceiveResultsRepository extends JpaRepository<ReceiveResult, Integer> {

    Optional<ReceiveResult> findByIdAndIsDelete(Integer id, Integer isDelete);

    Boolean existsByName(String name);

    List<ReceiveResult> findAllByIsDelete(Integer isDelete);

    List<ReceiveResult> findAllByIsDeleteAndIsActive(Integer isDelete, Integer isActive);
}
