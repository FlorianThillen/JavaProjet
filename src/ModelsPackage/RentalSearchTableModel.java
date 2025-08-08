package ModelsPackage;
import ModelsPackage.RentalDateSearchModel;

import javax.swing.table.AbstractTableModel;
import java.util.Date;
import java.util.List;

public class RentalSearchTableModel extends AbstractTableModel{
    private final String[] columns = {
            "ID Location", "Date début", "Numéro série", "Date achat", "Marque", "Garantie (mois)", "Station"
    };

    private final List<RentalDateSearchModel> data;

    public RentalSearchTableModel(List<RentalDateSearchModel> data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        RentalDateSearchModel r = data.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> r.getRentalId();
            case 1 -> r.getStartDate();
            case 2 -> r.getBikesSerialNumber();
            case 3 -> r.getBikeBuyingDate();
            case 4 -> r.getBrandName();
            case 5 -> r.getWarrantyDuration();
            case 6 -> r.getStationName();
            default -> null;
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0, 2, 5 -> Integer.class;
            case 1, 3 -> Date.class;
            default -> String.class;
        };
    }
}
