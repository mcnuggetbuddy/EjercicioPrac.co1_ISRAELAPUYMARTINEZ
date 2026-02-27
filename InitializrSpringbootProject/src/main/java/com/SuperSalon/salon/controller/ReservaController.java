/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.SuperSalon.salon.controller;

import com.SuperSalon.salon.domain.Reserva;
import com.SuperSalon.salon.service.ReservaService;
import com.SuperSalon.salon.service.ServicioService;
import jakarta.validation.Valid;
import java.util.Locale;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/reserva")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;
    private final ServicioService servicioService;
    private final MessageSource messageSource;

    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("reservas", reservaService.getReservas());
        model.addAttribute("servicios", servicioService.getServicios());
        model.addAttribute("reserva", new Reserva());
        model.addAttribute("totalReservas", reservaService.getReservas().size());
        return "/reserva/listado";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Reserva reserva,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("reservas", reservaService.getReservas());
            model.addAttribute("servicios", servicioService.getServicios());
            model.addAttribute("reserva", reserva);
            return "/reserva/listado";
        }

        reservaService.save(reserva);
        redirectAttributes.addFlashAttribute(
                "todoOk",
                messageSource.getMessage("mensaje.creado", null, Locale.getDefault())
        );
        return "redirect:/reserva/listado";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable Integer id,
            Model model,
            RedirectAttributes redirectAttributes) {

        Optional<Reserva> reservaOpt = reservaService.getReserva(id);
        if (reservaOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    "error",
                    messageSource.getMessage("mensaje.error", null, Locale.getDefault())
            );
            return "redirect:/reserva/listado";
        }

        model.addAttribute("reserva", reservaOpt.get());
        model.addAttribute("servicios", servicioService.getServicios());
        return "/reserva/modifica";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Integer id,
            RedirectAttributes redirectAttributes) {

        reservaService.delete(id);
        redirectAttributes.addFlashAttribute(
                "todoOk",
                messageSource.getMessage("mensaje.eliminado", null, Locale.getDefault())
        );
        return "redirect:/reserva/listado";
    }
}
