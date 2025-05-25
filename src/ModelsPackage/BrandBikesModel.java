package ModelsPackage;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class BrandBikesModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<BikeModel> contents;

    public BrandBikesModel(ArrayList<BikeModel> bikes)
    {
        columnNames = new ArrayList<>();
        columnNames.add("Serial Number");
        columnNames.add("Is Electric");
        columnNames.add("Station");
        columnNames.add("Street");
        columnNames.add("Street Number");
        columnNames.add("Postal Code");
        columnNames.add("Locality");

        contents = bikes;
    }

    public void setData(ArrayList<BikeModel> bikes) {
        contents = bikes;
        fireTableDataChanged();
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public int getRowCount() {
        return contents.size();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        BikeModel bike = contents.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> bike.getSerialNumber();
            case 1 -> bike.isElectric();
            case 2 -> bike.getStation().getName();
            case 3 -> bike.getStation().getStreet();
            case 4 -> bike.getStation().getStreetNumber();
            case 5 -> bike.getStation().getLocality().getPostalCode();
            case 6 -> bike.getStation().getLocality().getName();
            default -> null;
        };
    }
}
