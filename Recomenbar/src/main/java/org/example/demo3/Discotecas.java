package org.example.demo3;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class Discotecas {
    private final StringProperty nombre = new SimpleStringProperty();
    private final StringProperty direccion = new SimpleStringProperty();
    private final StringProperty tipoMusica = new SimpleStringProperty();
    private final StringProperty presupuesto = new SimpleStringProperty();
    private final StringProperty experiencia = new SimpleStringProperty();

    public Discotecas(String nombre, String direccion, String tipoMusica, String presupuesto, String experiencia) {
        this.nombre.set(nombre);
        this.direccion.set(direccion);
        this.tipoMusica.set(tipoMusica);
        this.presupuesto.set(presupuesto);
        this.experiencia.set(experiencia);
    }

    public ObservableValue<String> nombreProperty() {
        return nombre;
    }

    public ObservableValue<String> direccionProperty() {
        return direccion;
    }

    public ObservableValue<String> tipoMusicaProperty() {
        return tipoMusica;
    }

    public ObservableValue<String> presupuestoProperty() {
        return presupuesto;
    }

    public ObservableValue<String> experienciaProperty() {
        return experiencia;
    }
}
