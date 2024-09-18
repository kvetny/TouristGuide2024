// Controller-pakken indeholder klasser, der håndterer HTTP-forespørgelser fra klienter (en browser)
package kea.sofie.touristguide.controller;

import kea.sofie.touristguide.model.TouristAttraction;
import kea.sofie.touristguide.repository.TouristRepository;
import kea.sofie.touristguide.service.TouristService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// Betyder at klassen er en controller, der håndterer HTTP-forespørgelser
@Controller
@RequestMapping("attractions") // definerer basis-URL'en for alle endpoints i denne controller
public class TouristController {

    private final TouristService touristService;
    private final TouristRepository touristRepository;

    // Konstruktør Injection
    public TouristController(TouristService touristService, TouristRepository touristRepository) {
        this.touristService = touristService;
        this.touristRepository = touristRepository;
    }


    // Definerer et HTTP GET-endpoint, der returnerer alle turistattraktioner
    @GetMapping
    public String getTouristAttraction(Model model) {
        List<TouristAttraction> touristAttractions = touristService.getAttractions(); // Hent attraktionerne fra servicen
        // Tilføj attraktionerne til modellen, så Thymeleaf kan få adgang til dem
        model.addAttribute("attractions", touristAttractions);
        // Returnér HTML-siden 'attractionList.html' placeret i 'templates' mappen
        return "attractionList";
    }

    // Nyt endpoint for visning af tags for en specifik attraktion
    @GetMapping("/{name}/tags")
    public String getAttractionTags(@PathVariable String name, Model model) {
        // Hent attraktionens data baseret på dens navn
        TouristAttraction attraction = touristService.getOneAttraction(name);
        // Tilføj attraktion og dens tags til modellen
        model.addAttribute("attraction", attraction);
        model.addAttribute("tags", attraction.getTags()); // Forudsat at attraktionen har en getTags() metode
        // Returnér siden 'tags.html'
        return "tags";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("touristAttraction", new TouristAttraction());
        model.addAttribute("cities", touristRepository.getCities());
        model.addAttribute("availableTags", touristRepository.getTags());
        return "addAttraction";
    }

    @PostMapping("/save")
    public String saveAttraction(@ModelAttribute TouristAttraction touristAttraction) {
        touristService.saveAttraction(touristAttraction);
        return "redirect:/attractions";
    }

    @GetMapping("/edit/{name}")
    public String showEditForm(@PathVariable String name, Model model) {
        TouristAttraction attraction = touristService.getOneAttraction(name);
            model.addAttribute("touristAttraction", attraction);
            model.addAttribute("cities", touristRepository.getCities());
            model.addAttribute("availableTags", touristRepository.getTags());
            return "editAttraction";
    }

    @PostMapping("/update")
    public String updateAttraction(@ModelAttribute TouristAttraction touristAttraction) {
        touristService.updateAttraction(touristAttraction);
        return "redirect:/attractions";
    }

    @PostMapping("/delete")
    public String deleteAttraction(@RequestParam String name) {
        boolean deleted = touristService.deleteAttraction(name);
        if (deleted) {
            return "redirect:/attractions";
        } else {
            return "redirect:/attractions?error=notfound";
        }
    }

}




    /* Definerer et HTTP GET-endpoint, der returnerer en specifik turistattraktion baseret på navn
    @GetMapping("/{name}") // PathVariable '{name}' bliver brugt til at hente variabel fra URL'en
    public ResponseEntity<TouristAttraction> getTouristAttraction(@PathVariable String name) {
        TouristAttraction attraction = touristService.getAttractionByName(name); // Henter attraktionen ved at kalde service-metoden med det ønskede navn
        return new ResponseEntity<TouristAttraction>(attraction, HttpStatus.OK); // Returnerer attraktionen som et HTTP-svar med statuskoden 200 OK
    }


    @PostMapping("/add")
    public ResponseEntity<TouristAttraction> addTouristAttraction(@RequestBody TouristAttraction touristAttraction) {
        TouristAttraction newAttraction = touristService.addAttraction(touristAttraction);
        return new ResponseEntity<>(newAttraction, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<TouristAttraction> updateTouristAttraction(@RequestBody TouristAttraction touristAttraction) {
        TouristAttraction updatedAttraction = touristService.updateAttraction(touristAttraction);
        if (updatedAttraction != null) {
            return new ResponseEntity<>(updatedAttraction, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<TouristAttraction> deleteTouristAttraction(@PathVariable String name) {
        boolean deleted = touristService.deleteAttraction(name);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Hvis sletningen er succesfuld = kode 204
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Hvis attraktionen ikke blev fundet = kode 404
        }
    }*/






