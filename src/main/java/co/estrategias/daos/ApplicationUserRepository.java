package co.estrategias.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import co.estrategias.entities.BsUsuario;

public interface ApplicationUserRepository extends JpaRepository<BsUsuario, Long> {
    public BsUsuario findByUsualogi(String username);
}