/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajodediploma.views.login;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

/**
 *
 * @author leinier
 */
public class CrearTrabajadorForm extends FormLayout {

    TextField nombre;
    TextField apellidos;
    TextField solapin;
    ComboBox<String> categoria;
    TextField area;

    Button save = new Button("Añadir", VaadinIcon.PLUS.create());
    Button close = new Button("Cancelar", VaadinIcon.ERASER.create());

    public CrearTrabajadorForm() {
        Configuracion();
        add(nombre, apellidos, solapin, categoria, area, createButtonsLayout());
    }

    private void Configuracion() {

        nombre = new TextField();
        nombre.setPlaceholder("Nombre");

        apellidos = new TextField();
        apellidos.setPlaceholder("Apellidos");

        solapin = new TextField();
        solapin.setPlaceholder("Solapín");

        categoria = new ComboBox<>();
        categoria.setPlaceholder("Categoría");
        categoria.setItems("Tabajador", "otras");

        area = new TextField();
        area.setPlaceholder("Área");

    }

      private HorizontalLayout createButtonsLayout() {

        HorizontalLayout buttonlayout = new HorizontalLayout();
        buttonlayout.addClassName("button-layout");

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.addClickShortcut(Key.ENTER);

        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        close.addClickShortcut(Key.ESCAPE);

        buttonlayout.add(save, close);

        return buttonlayout;
    }
}
