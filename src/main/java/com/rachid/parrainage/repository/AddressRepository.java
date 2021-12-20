package com.rachid.parrainage.repository;

import com.rachid.parrainage.domain.Address;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Address entity.
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query(
        value = "select distinct address from Address address left join fetch address.users",
        countQuery = "select count(distinct address) from Address address"
    )
    Page<Address> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct address from Address address left join fetch address.users")
    List<Address> findAllWithEagerRelationships();

    @Query("select address from Address address left join fetch address.users where address.id =:id")
    Optional<Address> findOneWithEagerRelationships(@Param("id") Long id);
}
