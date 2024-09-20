package kea.sofie.touristguide.service;

import kea.sofie.touristguide.model.TouristAttraction;
import kea.sofie.touristguide.repository.TouristRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TouristService {

    private final TouristRepository touristRepository;

    public TouristService(TouristRepository touristRepository) {
        this.touristRepository = touristRepository;
    }

    // Metode til at hente alle turistattraktioner ved at kalde repository-metoden
    public List<TouristAttraction> getAttractions() {
        return touristRepository.getAttractions();
    }

    // Metode til at hente en attraktion baseret på navn ved at kalde repository-metoden
    public TouristAttraction getOneAttraction(String name) {
        return touristRepository.getOneAttraction(name);
    }

    public void saveAttraction(TouristAttraction touristAttraction) {
        touristRepository.saveAttraction(touristAttraction);
    }

    public void updateAttraction(TouristAttraction updatedAttraction) {
        touristRepository.updateAttraction(updatedAttraction);
    }

    public boolean deleteAttraction(String name) {
        return touristRepository.deleteAttraction(name);
    }







}


