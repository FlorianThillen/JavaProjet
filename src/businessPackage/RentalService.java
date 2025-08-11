package BusinessPackage;

import DataAccessPackage.RentalDAO;
import ExceptionsPackage.DataAccessException;
import ModelsPackage.BikeModel;
import ModelsPackage.RentalModel;
import ModelsPackage.SubscriptionModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentalService {
    private final RentalDAO rentalDAO;

    public RentalService(){
        this.rentalDAO = new RentalDAO();
    }

    public List<RentalModel> getAllRentals() throws DataAccessException {
        return rentalDAO.getAllRentals();
    }

    public RentalModel getRentalById(int id) throws DataAccessException {
        return rentalDAO.getRentalById(id);
    }

    public void insertRental(RentalModel rental) throws DataAccessException {
        List<String> errors = validateRentalData(rental, true);
        if (!errors.isEmpty()) {
            throw new DataAccessException(String.join("\n", errors));
        }

        if (rentalDAO.existsRentalId(rental.getRentalId())) {
            throw new DataAccessException("L'ID " + rental.getRentalId() + " est déjà utilisé.");
        }

        if (!rentalDAO.existsBike(rental.getBike().getSerialNumber())) {
            throw new DataAccessException("Aucun vélo trouvé avec l'ID " + rental.getBike().getSerialNumber() + ".");
        }
        if (!rentalDAO.existsSubscription(rental.getSubscription().getCardNumber())) {
            throw new DataAccessException("Aucun abonnement trouvé avec l'ID " + rental.getSubscription().getCardNumber() + ".");
        }

        rentalDAO.insertRental(rental);
    }

    public void updateRental(RentalModel rental) throws DataAccessException {
        List<String> errors = validateRentalData(rental, false);
        if (!errors.isEmpty()) {
            throw new DataAccessException(String.join("\n", errors));
        }

        if (!rentalDAO.existsRentalId(rental.getRentalId())) {
            throw new DataAccessException("Aucune location trouvée pour l'ID " + rental.getRentalId() + ".");
        }

        if (!rentalDAO.existsBike(rental.getBike().getSerialNumber())) {
            throw new DataAccessException("Aucun vélo trouvé avec l'ID " + rental.getBike().getSerialNumber() + ".");
        }
        if (!rentalDAO.existsSubscription(rental.getSubscription().getCardNumber())) {
            throw new DataAccessException("Aucun abonnement trouvé avec l'ID " + rental.getSubscription().getCardNumber() + ".");
        }

        rentalDAO.updateRental(rental);
    }

    public void deleteRental(int id) throws DataAccessException {
        if (id <= 0) {
            throw new DataAccessException("ID invalide (entier > 0).");
        }
        if (!rentalDAO.existsRentalId(id)) {
            throw new DataAccessException("Aucune location trouvée pour l'ID " + id + ".");
        }
        rentalDAO.deleteRental(id);
    }

    private List<String> validateRentalData(RentalModel r, boolean forCreate) {
        List<String> errors = new ArrayList<>();

        // ID > 0
        if (r.getRentalId() <= 0) {
            errors.add("• ID : doit être un entier strictement positif.");
        }

        // start_date obligatoire
        LocalDate start = r.getStartDate();
        if (start == null) {
            errors.add("• Date début : obligatoire.");
        }

        // return_date facultative, mais si renseignée : >= start
        LocalDate ret = r.getReturnDate();
        if (ret != null && start != null && ret.isBefore(start)) {
            errors.add("• Date retour : doit être postérieure ou égale à la date de début.");
        }

        // commentaire facultatif, longueur max 255
        String comment = r.getComment();
        if (comment != null && comment.length() > 255) {
            errors.add("• Commentaire : 255 caractères maximum.");
        }

        // FK obligatoires
        BikeModel bike = r.getBike();
        SubscriptionModel sub = r.getSubscription();
        if (bike == null || bike.getSerialNumber() <= 0) {
            errors.add("• ID Vélo : entier strictement positif obligatoire.");
        }
        if (sub == null || sub.getCardNumber() <= 0) {
            errors.add("• ID Abonnement : entier strictement positif obligatoire.");
        }

        return errors;
    }

    public List<Integer> getAllBikeIds() throws DataAccessException {
        return rentalDAO.getAllBikeIds();
    }

    public List<Integer> getAllSubscriptionIds() throws DataAccessException {
        return rentalDAO.getAllSubscriptionIds();
    }

}
