package trabajodediploma.data.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import trabajodediploma.data.AbstractEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Entity
public class TarjetaPrestamo extends AbstractEntity {

    @EqualsAndHashCode.Include
    @ToString.Include

    @ManyToMany
    @JoinTable(
            name = "tarjetaPrestamo_estudiantes",
            joinColumns = @JoinColumn(name = "tarjetaP_id"),
            inverseJoinColumns = @JoinColumn(name = "estudiante_id"))
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Estudiante> estudiantes;

    @NotNull(message = "El campo no debe estar vacío")
    @JoinColumn(name = "libro_id", nullable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Libro libro;

    @NotNull(message = "El campo no debe estar vacío")
    @Column(name = "fechaPrestamo")
    private LocalDate fechaPrestamo;
    @Column(name = "fechaDevolucion")
    private LocalDate fechaDevolucion;

    @ManyToMany
    @JoinTable(
            name = "tarjetaPrestamo_trabajadores",
            joinColumns = @JoinColumn(name = "tarjetaP_id"),
            inverseJoinColumns = @JoinColumn(name = "trabajador_id"))
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Trabajador> trabajadores;
}
