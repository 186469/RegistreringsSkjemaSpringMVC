package no.hvl.dat108.oblig4.controller;

import jakarta.validation.Valid;
import no.hvl.dat108.oblig4.model.Deltager;
import no.hvl.dat108.oblig4.service.DeltagerService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PaameldingController {

    private final DeltagerService service;

    public PaameldingController(DeltagerService service) {
        this.service = service;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Trim -> "" blir null og trigger @NotBlank, enklere validering
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping({"/", "/paamelding"})
    public String visSkjema(Model model) {
        if (!model.containsAttribute("skjema")) {
            model.addAttribute("skjema", new Deltager());
        }
        return "paamelding";
    }

    @PostMapping("/paamelding")
    public String behandle(
            @Valid @ModelAttribute("skjema") Deltager skjema,   // NB: BindingResult MÅ komme rett etter
            BindingResult br,
            RedirectAttributes ra) {

        // Egen domeneregel: passord == repetertPassord
        if (!skjema.passordMatcher()) {
            br.rejectValue("repetertPassord", "passord.ulik", "Passordene er ikke like");
        }

        // Mobil må være unik
        if (service.finnesMobil(skjema.getTlfNummer())) {
            br.rejectValue("tlfNummer", "mobil.duplikat", "Mobilnummeret er allerede påmeldt");
        }

        if (br.hasErrors()) {
            System.out.println("VALIDERINGSFEIL: " + br.getAllErrors());
            ra.addFlashAttribute("org.springframework.validation.BindingResult.skjema", br);
            ra.addFlashAttribute("skjema", skjema);
            return "redirect:/paamelding";
        }

        boolean ok = service.leggTil(skjema);
        System.out.println(ok ? "✅ NY DELTAGER LAGT TIL: " + skjema.getFornavn()
                              : "❌ leggTil returnerte false (sjekk service/valideringslogikk)");

        // Verdier til kvitteringssiden
        ra.addFlashAttribute("fornavn", skjema.getFornavn());
        ra.addFlashAttribute("etternavn", skjema.getEtternavn());
        ra.addFlashAttribute("mobil", skjema.getTlfNummer());
        ra.addFlashAttribute("kjonn", skjema.getKjonn());

        return "redirect:/paameldt";
    }

    @GetMapping("/paameldt")
    public String paameldt() {
        return "paameldt";
    }

    @GetMapping("/deltagerliste")
    public String deltagerliste(Model model) {
        model.addAttribute("deltagere", service.hentAlleSortert());
        return "deltagerliste";
    }
}
