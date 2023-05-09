package com.brody.forumgabontunisie.repositories;

import com.brody.forumgabontunisie.entities.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InscriptionRepository extends JpaRepository<Inscription, Long> {
    @Query("select i from Inscription i where i.name like :kw or i.firstname like :kw")
    List<Inscription> search(@Param("kw") String keyword);

    @Query("select case when count(i)>0 then true else false END from Inscription i where i.email=?1")
    Boolean verifyIfEmailExists(String email);

    @Query("select case when count(i)>0 then true else false END from Inscription i where i.phone=?1")
    Boolean verifyIfPhoneExists(String phone);
}
