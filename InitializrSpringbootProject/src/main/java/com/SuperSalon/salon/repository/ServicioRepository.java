/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.SuperSalon.salon.repository;

import com.SuperSalon.salon.domain.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author israelapuy
 */
@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Integer>{
    
}
