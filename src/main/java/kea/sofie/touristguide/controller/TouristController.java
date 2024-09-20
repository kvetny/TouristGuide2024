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
        TouristAttraction attraction = touristService.getOneAttraction(name);
        model.addAttribute("attraction", attraction);  // Tilføjer hele attraktionen, ikke kun tags
        // Returnér siden 'tags.html'
        return "tags";
    }


    // Metode til at tilføje en ny attraktion
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("touristAttraction", new TouristAttraction());
        model.addAttribute("cities", touristRepository.getCities());
        model.addAttribute("availableTags", touristRepository.getTags());
        return "addAttraction";
    }

    // Metode til at gemme
    @PostMapping("/save")
    public String saveAttraction(@ModelAttribute TouristAttraction touristAttraction) {
        touristService.saveAttraction(touristAttraction);
        return "redirect:/attractions";
    }

    // Metode til at redigere i en attraktion
    @GetMapping("/edit/{name}")
    public String showEditForm(@PathVariable String name, Model model) {
        TouristAttraction attraction = touristService.getOneAttraction(name);
        model.addAttribute("touristAttraction", attraction);
        model.addAttribute("cities", touristRepository.getCities());
        model.addAttribute("availableTags", touristRepository.getTags());
        return "editAttraction";
    }

    // Metode til at opdatere en ændret attraktion
    @PostMapping("/update")
    public String updateAttraction(@ModelAttribute TouristAttraction touristAttraction) {
        touristService.updateAttraction(touristAttraction);
        return "redirect:/attractions";
    }

    // Metode til at slette en attraktion
    @PostMapping("/delete")
    public String deleteAttraction(@RequestParam String name, Model model) {
        boolean deleted = touristService.deleteAttraction(name);
        if (deleted) {
            return "redirect:/attractions";
        } else {
            model.addAttribute("errorMessage", "Kunne ikke slette attraktionen.");
            return "attractionList"; // Viser listen med en fejlbesked
        }
    }
}






