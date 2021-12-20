package com.rachid.parrainage.repository;

import com.rachid.parrainage.domain.Avatar;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Avatar entity.
 */
@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    @Query(
        value = "select distinct avatar from Avatar avatar left join fetch avatar.users",
        countQuery = "select count(distinct avatar) from Avatar avatar"
    )
    Page<Avatar> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct avatar from Avatar avatar left join fetch avatar.users")
    List<Avatar> findAllWithEagerRelationships();

    @Query("select avatar from Avatar avatar left join fetch avatar.users where avatar.id =:id")
    Optional<Avatar> findOneWithEagerRelationships(@Param("id") Long id);
}
