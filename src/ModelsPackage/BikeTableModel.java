package ModelsPackage;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.sql.Date;

public class BikeTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Numéro", "Électrique", "Date achat", "Batterie", "KM", "Marque", "Station"};
    private List<BikeModel> bikes;

    public BikeTableModel(List<BikeModel> bikes) {
        this.bikes = bikes;
    }

    @Override
    public int getRowCount() {
        return bikes.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        BikeModel b = bikes.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> b.getSerialNumber();
            case 1 -> b.isElectric();
            case 2 -> b.getBuyingDate();
            case 3 -> b.getBatteryLevel();
            case 4 -> b.getNbKilometers();
            case 5 -> b.getBrand().getName();
            case 6 -> b.getStation().getName();
            default -> null;
        };
    }
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) { // ? pour retourner le type adequat
        return switch (columnIndex) {
            case 0, 3, 4 -> Integer.class;   // int aligné à droite
            case 1 -> Boolean.class;        // bool case à cocher
            case 2 -> Date.class;           // dates
            case 5, 6 -> String.class;
            default -> Object.class;
        };
    }

    public void setBikes(List<BikeModel> bikes) {
        this.bikes = bikes;
        fireTableDataChanged();
    }

    public BikeModel getBikeAt(int rowIndex) {
        return bikes.get(rowIndex);
    }
}
