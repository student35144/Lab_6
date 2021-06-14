package pl.lublin.wsei.java.cwiczenia;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ExportFilter {
    public TextField txtRok;
    public TextField txtKraj;
    public TextField txtDziedzina;
    public Label lblWynik;
    InfoNoblisciList noblisciList;
    public ArrayList<String> filteredNoblisci = new ArrayList<String>();

    public void setNoblisci(InfoNoblisciList daneNoblistow) {
        this.noblisciList = daneNoblistow;
    }

    public void Save(ActionEvent actionEvent) {
        try {
            FileWriter myWriter1 = new FileWriter("nobel_filtered.csv");
            myWriter1.write("");
            myWriter1.close();
            String rok = txtRok.getText().toLowerCase();
            String kraj = txtKraj.getText().toLowerCase();
            String dziedzina = txtDziedzina.getText().toLowerCase();
            ArrayList<Noblista> filteredNoblisciRok = new ArrayList<>();
            for(Noblista nb : noblisciList.noblisci) {
                if (rok.equals(""))
                    filteredNoblisciRok.add(nb);
                else if (nb.Year.toLowerCase().contains(rok)) {
                    filteredNoblisciRok.add(nb);
                }
            }
            filteredNoblisci.clear();
            ArrayList<Noblista> filteredNoblisciKraj = new ArrayList<>();
            for(Noblista nb : filteredNoblisciRok) {
                if (kraj.equals(""))
                    filteredNoblisciKraj.add(nb);
                else if (nb.Country.toLowerCase().contains(kraj)) {
                    filteredNoblisciKraj.add(nb);
                }
            }
            for(Noblista nb : filteredNoblisciKraj) {
                if (dziedzina.equals(""))
                    filteredNoblisci.add(nb.DataRow);
                else if (nb.Category.toLowerCase().contains(dziedzina)) {
                    filteredNoblisci.add(nb.DataRow);
                }
            }
            FileWriter myWriter = new FileWriter("nobel_filtered.csv");
            for(int i = 0; i<filteredNoblisci.size(); i++) myWriter.append(filteredNoblisci.get(i));
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
            if(filteredNoblisci.size()==1)lblWynik.setText("Znaleziono "+ filteredNoblisci.size() + " noblistę");
            else lblWynik.setText("Znaleziono " + filteredNoblisci.size() + " noblistów");
            filteredNoblisci.clear();
        } catch (IOException exception) {
            System.out.println("An error occurred.");
            exception.printStackTrace();
        }
    }
}