package bg.softuni.milionrecepti.repository;

import bg.softuni.milionrecepti.model.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long>  {
}
