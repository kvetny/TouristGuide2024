   // Repository-pakken indeholder klasser, der håndterer dataadgang og lagring
   package kea.sofie.touristguide.repository;
   import kea.sofie.touristguide.model.TouristAttraction;
   import org.springframework.stereotype.Repository;
   import java.util.ArrayList;
   import java.util.Iterator;
   import java.util.List;

   // Betyder at denne klasse fungerer som et repository i Spring boot applikationen (Dataadgang og lagring)
   @Repository
   public class TouristRepository {

       // Liste til opbevaring af turistattraktioner
       private final List<TouristAttraction> TouristAttractions = new ArrayList<>(List.of(
               new TouristAttraction("Tivoli", "Et af verdens aeldste forlystelsesparker", "København", List.of("Forlystelser", "Restauranter")),
               new TouristAttraction("Den lille havfrue", "En meget gammel skulptur", "København", List.of("Kunst", "Ved vandet", "Gratis")),
               new TouristAttraction("Zoo", "Mere end 4000 fascinerende dyr", "København", List.of("Dyr", "Børnevenligt")),
               new TouristAttraction("Den Blå Planet", "Oplev verden under overfladen", "København", List.of("Dyr", "Børnevenligt")),
               new TouristAttraction("Amalienborg", "Se hvor de kongelige bor", "København", List.of("Historie", "Kultur", "Gratis")),
               new TouristAttraction("Nationalmuseet", "Se hele Danmarks historie", "København", List.of("Historie", "Kultur")),
               new TouristAttraction("Rundetaarn", "Se hvad Christian IV har bygget", "København", List.of("Historie", "Kultur", "Op i højderne")),
               new TouristAttraction("Djurs Sommerland", "Nordens største sommerland", "Aarhus", List.of("Forlystelser", "Restauranter", "Børnevenligt")),
               new TouristAttraction("ARoS", "Et unikt kunstmuseum", "Aarhus", List.of("Kunst", "Historie")),
               new TouristAttraction("Maximum", "Bowling, legeland, trampolinpark, escape rooms og minigolf", "Aalborg", List.of("Børnevenligt"))
       ));


       // Metode der returnerer hele listen af turistattraktioner
       public List<TouristAttraction> getAttractions() {
           return TouristAttractions;
       }

       // Metode til at hente en specifik attraktion baseret på navnet
       public TouristAttraction getOneAttraction(String name) {
           for (TouristAttraction touristAttraction : TouristAttractions) {
               if (touristAttraction.getName().equals(name)) {
                   return touristAttraction;
               }
           }
           return null;
       }

       public void saveAttraction(TouristAttraction touristAttraction) {
           TouristAttractions.add(touristAttraction);
       }

       public List<String> getCities() {
           return List.of("København", "Aarhus", "Odense", "Aalborg");
       }

       public List<String> getTags() {
           return List.of("Forlystelser", "Restauranter", "Kunst",
                   "Ved vandet", "Gratis", "Dyr", "Børnevenligt",
                   "Historie", "Kultur", "Op i højderne");
       }

       public void updateAttraction(TouristAttraction updatedAttraction) {
           for (int i = 0; i < TouristAttractions.size(); i++) {
               TouristAttraction attraction = TouristAttractions.get(i);
               if (attraction.getName().equals(updatedAttraction.getName())) {
                   TouristAttractions.set(i, updatedAttraction);
                   return;
               }
           }
       }

       public boolean deleteAttraction(String name) {
           Iterator<TouristAttraction> iterator = TouristAttractions.iterator();
           while (iterator.hasNext()) {
               TouristAttraction touristAttraction = iterator.next();
               if (touristAttraction.getName().equals(name)) {
                   iterator.remove();
                   return true;
               }
           }
           return false;
       }
   }
