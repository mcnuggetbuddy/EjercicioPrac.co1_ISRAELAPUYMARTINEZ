/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.SuperSalon.salon.repository;

import com.SuperSalon.salon.domain.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author israelapuy
 */

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
}
