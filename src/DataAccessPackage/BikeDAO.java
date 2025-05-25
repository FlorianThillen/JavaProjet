package DataAccessPackage;

import ExceptionsPackage.DataAccessException;
import ModelsPackage.BikeModel;
import ModelsPackage.BrandModel;
import ModelsPackage.LocalityModel;
import ModelsPackage.StationModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class BikeDAO {
    private final Connection connection;

    public BikeDAO(){
        this.connection = SingletonConnection.getInstance();
    }

    // cascade pour update et suppr ? a cause des tables repair & rental

    // ================ operation CRUD - Read ================
    public List<BikeModel> findAll() throws DataAccessException {
        List<BikeModel> result = new ArrayList<>();

        String query = "SELECT  b.serial_number, b.is_electric, b.buying_date, b.battery_level, b.nb_kilometer," +
                "br.name AS brand_name, br.waranty_duration," +
                "s.station_number,s.name as station_name, s.street, s.street_number " +
                "FROM bike b " +
                "JOIN brand br ON b.brand_name = br.name " +
                "JOIN station s ON b.station_id = s.station_number";

        try( PreparedStatement stmt = connection.prepareStatement(query) ){
            ResultSet rs = stmt.executeQuery();


            while (rs.next()){

                //public StationModel(int stationNumber, String name, String street, int streetNumber, LocalityModel locality)
                StationModel station = new StationModel(
                        rs.getInt("station_number"),
                        rs.getString("station_name"), //+
                        rs.getString("street"),
                        rs.getInt("street_number"),
                        null
                );

                BrandModel brand = new BrandModel(
                        rs.getString("brand_name"),
                        rs.getInt("waranty_duration")
                );

                //public BikeModel(int serialNumber, boolean isElectric, Date buyingDate, int batteryLevel, int nbKilometers, BrandModel brand,StationModel station)
                result.add(new BikeModel(
                        rs.getInt("serial_number"),
                        rs.getBoolean("is_electric"),
                        rs.getDate("buying_date"),
                        rs.getObject("battery_level") != null ? rs.getInt("battery_level") : 0,
                        rs.getObject("nb_kilometer") != null ? rs.getInt("nb_kilometer") : 0,
                        brand,
                        station
                ));
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new DataAccessException("Erreur lors du chargement des vélos (findall)",e);
        }

        return result;
    }

    // ================ operation CRUD - Create ================
    public void insert(BikeModel bikeModel) throws DataAccessException {
        String query = "INSERT INTO bike (serial_number,is_electric,buying_date,battery_level,nb_kilometer,station_id,brand_name ) " +
                "VALUES (?,?,?,?,?,?,?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1, bikeModel.getSerialNumber());
            stmt.setBoolean(2, bikeModel.isElectric());
            stmt.setDate(3, bikeModel.getBuyingDate());

            if (bikeModel.getBatteryLevel() > 0){
                stmt.setInt(4, bikeModel.getBatteryLevel());
            } else {
                stmt.setNull(4, Types.INTEGER);
            }
            if (bikeModel.getNbKilometers() > 0){
                stmt.setInt(5, bikeModel.getNbKilometers());
            } else {
                stmt.setNull(5, Types.INTEGER);
            }

            stmt.setInt(6,bikeModel.getStation().getStationNumber());
            stmt.setString(7,bikeModel.getBrand().getName());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Erreur lors de l'insertion du vélo");
        }
    }

    // ================ operation CRUD - Update ================

    public void update(BikeModel bikeModel, int originalSerialNumber)throws DataAccessException {
        String query ="UPDATE bike " +
                "SET serial_number = ?, is_electric = ?, buying_date = ?, battery_level = ?, nb_kilometer = ?,station_id = ?,brand_name = ? " +
                "WHERE serial_number = ?";
        try(PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1,bikeModel.getSerialNumber());
            stmt.setBoolean(2,bikeModel.isElectric());
            stmt.setDate(3,bikeModel.getBuyingDate());

            if (bikeModel.getBatteryLevel() > 0) {
                stmt.setInt(4, bikeModel.getBatteryLevel());
            } else {
                stmt.setNull(4, Types.INTEGER);
            }

            if (bikeModel.getNbKilometers() > 0) {
                stmt.setInt(5, bikeModel.getNbKilometers());
            } else {
                stmt.setNull(5, Types.INTEGER);
            }

            stmt.setInt(6,bikeModel.getStation().getStationNumber());
            stmt.setString(7,bikeModel.getBrand().getName());

            stmt.setInt(8,originalSerialNumber); // dans le cas où on change l id

            stmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
            throw new DataAccessException("Erreur lors de la MAJ du vélo",e);
        }

    }

    // ================ operation CRUD - Delete ================
    public void deleteBySerialNumber(int serialNumber)throws DataAccessException {
        String query = "DELETE FROM bike WHERE serial_number = ?";

        try(PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1,serialNumber);
            stmt.executeUpdate();
        }catch (SQLException e){
            throw new DataAccessException("Erreur lors de la suppresion du vélo",e);
        }

    }

    public ArrayList<BikeModel> getBikesFromBrand(String brandName) throws DataAccessException {
        ArrayList<BikeModel> bikes = new ArrayList<>();
        String query = """
                SELECT * FROM bike b
                JOIN station s ON b.station_id = s.station_number
                WHERE b.brand_name = ?;
                """;
        try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, brandName);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                LocalityModel locality = new LocalityModel(
                        rs.getInt(12),
                        rs.getString(13)
                );

                StationModel station = new StationModel(
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getInt(11),
                        locality
                );

                bikes.add(new BikeModel(
                        rs.getInt(1),
                        rs.getBoolean(2),
                        rs.getDate(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        null,
                        station
                ));
            }
        } catch (SQLException e) {
            String error_message = String.format("Erreur lors du chargement des vélos de la marque %s", brandName);
            throw new DataAccessException(error_message, e);
        }
        return bikes;
    }

    //brand
    public ArrayList<BikeModel> getBikesFromStation(StationModel station) throws DataAccessException {
        ArrayList<BikeModel> bikes = new ArrayList<>();

        String query = """
                SELECT * FROM bike b
                WHERE b.station_id = ?;
                """;
        try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, station.getStationNumber());

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                bikes.add(new BikeModel(
                        rs.getInt(1),
                        rs.getBoolean(2),
                        rs.getDate(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        null,
                        station

                ));
            }
        } catch (SQLException e) {
            String error_message = String.format("Erreur lors du chargement des velos de la station %s", station.getName());
            throw new DataAccessException(error_message, e);
        }
        return bikes;
    }
}
