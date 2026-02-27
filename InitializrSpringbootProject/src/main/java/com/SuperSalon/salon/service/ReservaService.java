/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.SuperSalon.salon.service;

import com.SuperSalon.salon.domain.Reserva;
import com.SuperSalon.salon.repository.ReservaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservaService {

    // El repositorio es final para asegurar la inmutabilidad
    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @Transactional(readOnly = true)
    public List<Reserva> getReservas() {
        return reservaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Reserva> getReserva(Integer idReserva) {
        return reservaRepository.findById(idReserva);
    }

    @Transactional
    public void save(Reserva reserva) {
        reservaRepository.save(reserva);
    }

    @Transactional
    public void delete(Integer idReserva) {

        // Verifica si la reserva existe antes de intentar eliminarla
        if (!reservaRepository.existsById(idReserva)) {
            throw new IllegalArgumentException(
                    "La reserva con ID " + idReserva + " no existe."
            );
        }

        try {
            reservaRepository.deleteById(idReserva);
        } catch (DataIntegrityViolationException e) {
            // En caso de relaciones o restricciones de BD
            throw new IllegalStateException(
                    "No se puede eliminar la reserva. Tiene datos asociados.",
                    e
            );
        }
    }
}
